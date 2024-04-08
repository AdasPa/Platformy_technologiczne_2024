package org.example;

import java.util.*;

public class MageRepository {
    private Collection<Mage> collection;

    public MageRepository() {
        this.collection = new ArrayList<Mage>();
    }

    public Optional<Mage> find(String name) {
        for (Mage m : collection) {
            if (m.getName().equals(name)) {
                return Optional.of(m);
            }
        }
        return Optional.empty();
    }

    public void delete(String name) {
        Optional<Mage> existingMage = find(name);
        if (existingMage.isPresent()) {
            collection.remove(existingMage.get());
        } else {
            throw new IllegalArgumentException("Mage with name " + name + " does not exist");
        }
    }


    public void save(Mage mage) {
        Optional<Mage> existingMage = find(mage.getName());
        if (existingMage.isPresent()) {
            throw new IllegalArgumentException("Mage with name " + mage.getName() + " already exists.");
        } else {
            collection.add(mage);
        }
    }

}



