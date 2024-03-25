package org.example;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Tower {
    @Id
    private String name;
    private int height;

    @OneToMany(mappedBy = "tower", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mage> mages = new ArrayList<>();

    public Tower() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Mage> getMages() {
        return mages;
    }
    public void printInfo() {
        System.out.println("Tower: " + getName());
        System.out.println("Height: " + getHeight());
    }

    public void printMages() {
        System.out.println("Mages in the tower:");
        for (Mage mage : getMages()) {
            System.out.println("Name: " + mage.getName() + ", Level: " + mage.getLevel());
        }
    }

    public void removeMage(Mage mage){
        mages.remove(mage);
    }

}
