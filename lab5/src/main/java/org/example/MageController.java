package org.example;

import java.util.*;

public class MageController {

    private MageRepository repository;

    public MageController(MageRepository repository) {
        this.repository = repository;
    }

    public String find(String name) {
        Optional<Mage> mage = repository.find(name);
        if (mage.isPresent()) {
            mage.get().printInfo();
            return mage.get().getName();
        } else {
            return "not found";
        }
    }

    public String delete(String name) {
        try {
            repository.delete(name);
            return "done";
        } catch (IllegalArgumentException e) {
            return "not found";
        }
    }


    public String save(String name, int level) {
        try {
            Mage mage = new Mage(name, level);
            repository.save(mage);
            return "done";
        } catch (IllegalArgumentException e) {
            return "bad request";
        }
    }
}

