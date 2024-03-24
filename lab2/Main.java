package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj liczbę wątków:");
        int numThreads = scanner.nextInt();

        TaskManager taskManager = new TaskManager();
        ResultCollector resultCollector = new ResultCollector();

        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(new Calculator(taskManager, resultCollector));
            threads[i].start();
        }

        while (true) {
            System.out.println("Podaj liczbę do sprawdzenia (0 aby zakończyć):");
            long number = scanner.nextLong();
            if (number == 0) {
                taskManager.close();
                break;
            }
            taskManager.addTask(number);
        };
        for(Thread thread : threads)
        {
            thread.interrupt();
        }
        System.out.println("Finished all threads!");
        resultCollector.displayResults();
    }
}





