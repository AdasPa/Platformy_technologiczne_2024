package org.example;

import java.util.Comparator;

public class MageComparator implements Comparator<Mage> {
    @Override
    public int compare(Mage mage1, Mage mage2) {
        //power, level, name

        if (mage1 == null && mage2 == null) {
            return 0;
        } else if (mage1 == null) {
            return -1;
        } else if (mage2 == null) {
            return 1;
        }

        int powerComparison = Double.compare(mage1.getPower(), mage2.getPower());
        if (powerComparison != 0) {
            return powerComparison;
        }

        int levelComparison = Integer.compare(mage1.getLevel(), mage2.getLevel());
        if (levelComparison != 0) {
            return levelComparison;
        }

        return mage1.getName().compareTo(mage2.getName());
    }
}

