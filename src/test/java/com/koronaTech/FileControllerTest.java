package com.koronaTech;

import com.koronaTech.model.Worker;
import com.koronaTech.model.WorkerHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class FileControllerTest {
    private static WorkerHandler<Worker> workerHandler;

    @BeforeEach
    public void init() {
        try {
            workerHandler = FileController.readFile("in.txt");
            FileController.relog();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void readFileTest() {
        FileController.logger.info(workerHandler.toString());
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

    @Test
    void wrongFileTest() {
        Assertions.assertThrowsExactly(FileNotFoundException.class,
                () -> FileController.readFile("null.txt"));
    }

    @Test
    void writeFileTest() {
        try {
            FileController.writeFile(workerHandler.toString(), "out.txt");
            WorkerHandler<Worker> workerHandlerOut = FileController.readFile("out.txt");
            workerHandler.sortByName(true);
            workerHandlerOut.sortByName(true);
            Assertions.assertEquals(workerHandler.toString(), workerHandlerOut.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void wrongFileInputFormatTest() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class,
                () -> FileController.readFile("out.jpeg"));
    }

    @Test
    void wrongFileOutputFormatTest() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class,
                () -> FileController.writeFile(workerHandler.toString(), "out.jpeg"));
    }
}