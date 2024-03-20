package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Mage mage1 = new Mage("Merlin", 20, 100.0, new HashSet<>());
        Mage mage2 = new Mage("Gandalf", 25, 110.0, new HashSet<>());
        Mage mage3 = new Mage("Dumbledore", 30, 120.0, new HashSet<>());
        Mage mage4 = new Mage("Saruman", 28, 105.0, new HashSet<>());
        Mage mage5 = new Mage("Harry", 18, 90.0, new HashSet<>());
        Mage mage6 = new Mage("Hermione", 19, 95.0, new HashSet<>());
        Mage mage7 = new Mage("Gandalf Junior", 15, 80.0, new HashSet<>());
        Mage mage8 = new Mage("Apprentice 1", 10, 70.0, new HashSet<>());
        Mage mage9 = new Mage("Apprentice 2", 12, 75.0, new HashSet<>());
        Mage mage10 = new Mage("Apprentice 3", 8, 65.0, new HashSet<>());
        Mage mage11 = new Mage("Ron", 3, 100.0, new HashSet<>());

        mage1.getApprentices().add(mage8);
        mage1.getApprentices().add(mage9);
        mage2.getApprentices().add(mage10);
        mage3.getApprentices().add(mage5);
        mage3.getApprentices().add(mage6);
        mage6.getApprentices().add(mage11);
        
        Set<Mage> mages = new HashSet<>();
        mages.add(mage1);
        mages.add(mage2);
        mages.add(mage3);
        mages.add(mage4);
        mages.add(mage5);
        mages.add(mage6);
        mages.add(mage7);
        mages.add(mage8);
        mages.add(mage9);
        mages.add(mage10);
        mages.add(mage11);

        MagePrinter.printSet(mages);
        System.out.println();
        MageStatistics.generateSubtreeCount(mages,0); // no sorting
        System.out.println();
        MageStatistics.generateSubtreeCount(mages,1); // sorting: name, level, power
        System.out.println();
        MageStatistics.generateSubtreeCount(mages,2); // sorting: power, level, name
    }
}

