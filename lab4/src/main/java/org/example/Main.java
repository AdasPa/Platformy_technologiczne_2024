package org.example;


import jakarta.persistence.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mages-and-towers");
        EntityManager em = emf.createEntityManager();

        Tower tower1 = new Tower();
        tower1.setName("Tower1");
        tower1.setHeight(100);

        Tower tower2 = new Tower();
        tower2.setName("Tower2");
        tower2.setHeight(200);

        Mage mage1 = new Mage();
        mage1.setName("Mage1");
        mage1.setLevel(50);
        mage1.setTower(tower1);

        Mage mage2 = new Mage();
        mage2.setName("Mage2");
        mage2.setLevel(70);
        mage2.setTower(tower1);

        Mage mage3 = new Mage();
        mage3.setName("Mage3");
        mage3.setLevel(10);
        mage3.setTower(tower1);

        Mage mage4 = new Mage();
        mage4.setName("Mage4");
        mage4.setLevel(100);
        mage4.setTower(tower2);

        Mage mage5 = new Mage();
        mage5.setName("Mage5");
        mage5.setLevel(108);
        mage5.setTower(tower2);

        tower1.getMages().add(mage1);
        tower1.getMages().add(mage2);
        tower1.getMages().add(mage3);
        tower2.getMages().add(mage4);
        tower2.getMages().add(mage5);

        mage1.setTower(tower1);
        mage2.setTower(tower1);
        mage3.setTower(tower1);
        mage4.setTower(tower2);
        mage5.setTower(tower2);



        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(tower1);
        em.persist(tower2);
        em.persist(mage1);
        em.persist(mage2);
        em.persist(mage3);
        em.persist(mage4);
        em.persist(mage5);
        tx.commit();


        tx.begin();
        List<Tower> towers = em.createQuery("SELECT t FROM Tower t", Tower.class).getResultList();

        for (Tower t: towers) {
            t.printInfo();
            t.printMages();
            System.out.println();
        }

        List<Mage> mages = em.createQuery("SELECT m FROM Mage m", Mage.class).getResultList();

        for (Mage m: mages) {
            m.printInfo();
            System.out.println();
        }

        List<Mage> mages1 = em.createQuery("SELECT m FROM Mage m WHERE m.level > :level", Mage.class).setParameter("level", 10).getResultList();
        for (Mage mage : mages1) {
            System.out.println("Mage: " + mage.getName() + ", Level: " + mage.getLevel());
        }

        mage4.getTower().removeMage(mage4);
        em.remove(mage4);
        System.out.println("Deleted mage 4");
        tx.commit();

        tx.begin();
        mages1 = em.createQuery("SELECT m FROM Mage m WHERE m.level > :level", Mage.class).setParameter("level", 10).getResultList();

        for (Mage mage : mages1) {
            System.out.println("Mage: " + mage.getName() + ", Level: " + mage.getLevel());
        }


        em.remove(tower2);
        System.out.println("Deleted tower 2");
        tx.commit();

        towers = em.createQuery("SELECT t FROM Tower t", Tower.class).getResultList();

        for (Tower t: towers) {
            t.printInfo();
            t.printMages();
            System.out.println();
        }

        mages1 = em.createQuery("SELECT m FROM Mage m WHERE m.level > :level", Mage.class).setParameter("level", 10).getResultList();

        for (Mage mage : mages1) {
            System.out.println("Mage: " + mage.getName() + ", Level: " + mage.getLevel());
        }

        em.close();
        emf.close();
    }
}
