package com.koronaTech;

import com.koronaTech.model.Worker;
import com.koronaTech.model.WorkerHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;


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
        FileController.logger.info(workerHandler.getReport());
        assertEquals(41, workerHandler.size());
    }

    @Test
    void sortByNameOrderAskTest() {
        workerHandler.sortByName(true);
        assertEquals(3600.25, workerHandler.getWorker(0).getSalary());
    }

    @Test
    void sortByNameOrderDeskTest() {
        workerHandler.sortByName(false);
        assertEquals(3100.25, workerHandler.getWorker(0).getSalary());
    }

    @Test
    void sortBySalaryOrderAskTest() {
        workerHandler.sortBySalary(true);
        assertEquals(-100800.0, workerHandler.getWorker(0).getSalary());
    }

    @Test
    void sortBySalaryOrderDeskTest() {
        workerHandler.sortBySalary(false);
        assertEquals(6200.0, workerHandler.getWorker(0).getSalary());
    }

    @Test
    void wrongFileTest() {
        assertThrowsExactly(FileNotFoundException.class,
                () -> FileController.readFile("null.txt"));
    }

    @Test
    void writeFileTest() {
        try {
            FileController.writeFile(workerHandler.getReport(), "out.txt");
            WorkerHandler<Worker> workerHandlerOut = FileController.readFile("out.txt");
            workerHandler.sortByName(true);
            workerHandlerOut.sortByName(true);
            assertEquals(workerHandler.getReport(), workerHandlerOut.getReport());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void wrongFileInputFormatTest() {
        assertThrowsExactly(IllegalArgumentException.class,
                () -> FileController.readFile("out.jpeg"));
    }

    @Test
    void wrongFileOutputFormatTest() {
        assertThrowsExactly(IllegalArgumentException.class,
                () -> FileController.writeFile(workerHandler.toString(), "out.jpeg"));
    }
}