package org.example;

import jakarta.persistence.*;

@Entity
public class Mage {
    @Id
    private String name;
    private int level;

    @ManyToOne
    private Tower tower;

    public Mage() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Tower getTower() {
        return tower;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }

    public void printInfo() {
        System.out.println("Mage: " + getName());
        System.out.println("Level: " + getLevel());
        System.out.println("Tower: " + getTower().getName());

    }
}
