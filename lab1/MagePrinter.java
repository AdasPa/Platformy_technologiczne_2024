package org.example;

import java.util.*;

public class MagePrinter {
    public static void printSet(Set<Mage> mageSet) {
        Map<Mage, Integer> subtreeCounts = new HashMap<>();
        Set<Mage> printedMages = new HashSet<>();
        for (Mage mage : mageSet) {
            int count = countSubtree(mage);
            subtreeCounts.put(mage, count);
        }

        List<Map.Entry<Mage, Integer>> sortedMages = new ArrayList<>(subtreeCounts.entrySet());
        sortedMages.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for (Map.Entry<Mage, Integer> entry : sortedMages) {
            printMage(entry.getKey(), 0, printedMages);
        }
    }

    private static int countSubtree(Mage mage) {
        int count = 0;
        Set<Mage> visited = new HashSet<>();
        Stack<Mage> stack = new Stack<>();
        stack.push(mage);
        visited.add(mage);

        while (!stack.isEmpty()) {
            Mage current = stack.pop();
            count++;
            for (Mage apprentice : current.getApprentices()) {
                if (!visited.contains(apprentice)) {
                    stack.push(apprentice);
                    visited.add(apprentice);
                }
            }
        }

        return count;
    }

    private static void printMage(Mage mage, int depth, Set<Mage> printedMages) {
        if(!printedMages.contains(mage)) {
            for (int i = 0; i < depth; i++) {
                System.out.print("-");
            }
            System.out.println(mage);
            printedMages.add(mage);
            for (Mage apprentice : mage.getApprentices()) {
                printMage(apprentice, depth + 1, printedMages);
            }
        }
    }
}
