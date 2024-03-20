package org.example;

import java.util.*;

public class Mage implements Comparable<Mage> {
    private String name;
    private int level;
    private double power;
    private Set<Mage> apprentices;

    public Mage(String name, int level, double power, Set<Mage> apprentices) {
        this.name = name;
        this.level = level;
        this.power = power;
        this.apprentices = apprentices;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public double getPower() {
        return power;
    }

    public Set<Mage> getApprentices() {
        return apprentices;
    }


    @Override
    public int compareTo(Mage other) {
        // name, level, power

        if (this == other) return 0;
        int result = this.name.compareTo(other.name);

        if (result != 0) return result;
        result = Integer.compare(this.level, other.level);

        if (result != 0) return result;
        return Double.compare(this.power, other.power);
    }

    @Override
    public String toString() {
        return "Mage{name='" + name + "', level=" + level + ", power=" + power + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mage mage = (Mage) o;
        return level == mage.level &&
                Double.compare(mage.power, power) == 0 &&
                Objects.equals(name, mage.name) &&
                Objects.equals(apprentices, mage.apprentices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level, power, apprentices);
    }
}
