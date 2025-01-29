package com.koronaTech;

import com.koronaTech.model.Worker;
import com.koronaTech.model.WorkerHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileControllerTest {
    private static WorkerHandler<Worker> workerHandler;

    @BeforeEach
    public void init() {
        workerHandler = FileController.readFile("in.txt");
        FileController.relog();
    }

    @Test
    void readFileTest() {
        System.out.println(workerHandler.toString());
        Assertions.assertEquals(41, workerHandler.size());
    }

    @Test
    void sortByNameOrderAskTest() {
        workerHandler.sortByName(true);
        Assertions.assertEquals(3600.25, workerHandler.getWorker(0).getSalary());
    }

    @Test
    void sortByNameOrderDeskTest() {
        workerHandler.sortByName(false);
        Assertions.assertEquals(3100.25, workerHandler.getWorker(0).getSalary());
    }

    @Test
    void sortBySalaryOrderAskTest() {
        workerHandler.sortBySalary(true);
        Assertions.assertEquals(-100800.0, workerHandler.getWorker(0).getSalary());
    }

    @Test
    void sortBySalaryOrderDeskTest() {
        workerHandler.sortBySalary(false);
        Assertions.assertEquals(6200.0, workerHandler.getWorker(0).getSalary());
    }
}