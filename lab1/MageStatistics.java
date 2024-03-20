package org.example;

import java.util.*;

public class MageStatistics {
    public static void generateSubtreeCount(Set<Mage> mageSet, int sortingMode) {
        Map<Mage, Integer> subtreeCount = new HashMap<>();

        for (Mage mage : mageSet) {
            int count = countSubtree(mage);
            subtreeCount.put(mage, count);
        }

        printStatistics(subtreeCount, sortingMode);
    }

    private static int countSubtree(Mage mage) {
        int count = mage.getApprentices().size();
        for (Mage apprentice : mage.getApprentices()) {
            count += countSubtree(apprentice);
        }
        return count;
    }

    public static void printStatistics(Map<Mage, Integer> subtreeCount, int sortingMode) {
        Map<Mage, Integer> sortedSubtreeCount;

        if (sortingMode == 1) { // sorting mode
            sortedSubtreeCount = new TreeMap<>(subtreeCount);
        } else if (sortingMode == 2) { // alternative sorting mode
            sortedSubtreeCount = new TreeMap<>(new MageComparator());
            sortedSubtreeCount.putAll(subtreeCount);
        } else { // no sorting mode
            sortedSubtreeCount = new HashMap<>(subtreeCount);
        }

        System.out.println("Statystyki: ");
        for (Map.Entry<Mage, Integer> entry : sortedSubtreeCount.entrySet()) {
            System.out.println(entry.getKey().toString() + " -> " + entry.getValue());
        }
    }
}
