package org.example;

import java.util.LinkedList;
import java.util.Queue;

class TaskManager {
    private Queue<Long> tasks = new LinkedList<>();
    private boolean closed = false;
    public synchronized void addTask(long task) {
            tasks.add(task);
            notify();
    }

    public synchronized long getTask() throws InterruptedException {
        while (tasks.isEmpty() && !closed) {
            wait();
        }
        if (!tasks.isEmpty()) {
            return tasks.remove();
        } else {
            return -1;
        }
    }

    public synchronized void close() {
        closed = true;
        notifyAll();
    }
}