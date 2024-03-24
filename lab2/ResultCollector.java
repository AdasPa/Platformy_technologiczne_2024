package org.example;

import java.util.LinkedList;
import java.util.Queue;

class ResultCollector {
    private Queue<Long> results = new LinkedList<>();
    public synchronized void addResult(long result) {
        results.add(result);
    }
    public synchronized void displayResults() {
        System.out.println("Wyniki obliczeń:");
        while (!results.isEmpty()) {
            System.out.println(results.poll());
        }
    }
}