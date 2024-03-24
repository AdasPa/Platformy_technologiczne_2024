package org.example;

class Calculator implements Runnable {
    private TaskManager taskManager;
    private ResultCollector resultCollector;

    public Calculator(TaskManager taskManager, ResultCollector resultCollector) {
        this.taskManager = taskManager;
        this.resultCollector = resultCollector;
    }

    @Override
    public void run() {
        while (true) {
            try {
                long task = taskManager.getTask();
                Thread.sleep(5000);
                System.out.println("\nObliczono " + task);
                resultCollector.addResult(task);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}