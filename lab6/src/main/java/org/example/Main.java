package org.example;

import org.apache.commons.lang3.tuple.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.*;

import static java.lang.StringTemplate.STR;

public class Main {
    public static void main(String[] args) {
        Path inputDirectory = Path.of("C:\\Users\\adas\\Desktop\\studia\\sem_4\\technologicznosc\\lab6\\src\\main\\resources\\images");
        Path outputDirectory = Path.of("C:\\Users\\adas\\Desktop\\studia\\sem_4\\technologicznosc\\lab6\\src\\main\\resources\\results");
        List<Path> files;
        try {
            Stream<Path> stream = Files.list(inputDirectory);
            files = stream.toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ForkJoinPool customThreadPool = new ForkJoinPool(5);

        long time = System.currentTimeMillis();

        try {
            customThreadPool.submit(() -> {
                try {
                    files.parallelStream()
                            .map(path -> {
                                try {
                                    BufferedImage image = ImageIO.read(path.toFile());
                                    return Pair.of(path.getFileName().toString(), image);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    return null;
                                }
                            })
                            .map(pair -> {
                                BufferedImage original = pair.getRight();
                                BufferedImage transformedImage = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());

                                for (int i = 0; i < original.getWidth(); i++) {
                                    for (int j = 0; j < original.getHeight(); j++) {
                                        int rgb = original.getRGB(i, j);
                                        Color color = new Color(rgb);

                                        int red = 255-color.getRed();
                                        int green = 255-color.getGreen();
                                        int blue = 255- color.getBlue();

                                        Color outColor = new Color(red, green, blue);
                                        transformedImage.setRGB(i, j, outColor.getRGB());
                                    }
                                }

                                return Pair.of(pair.getLeft(), transformedImage);
                            })
                            .forEach(pair -> {
                                try {
                                    File outputFile = new File(STR."\{outputDirectory}/\{pair.getLeft()}");
                                    ImageIO.write(pair.getRight(), "jpg", outputFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).get();

            System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + "s");
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
