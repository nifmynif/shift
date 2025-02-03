package com.koronaTech.controller;

import com.koronaTech.model.Worker;
import com.koronaTech.model.WorkerHandler;
import com.koronaTech.services.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;


class FileServiceTest {
    private static WorkerHandler<Worker> workerHandler;

    @BeforeEach
    public void init() {
        try {
            workerHandler = FileService.readFile("src/test/resources/in.txt");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void readFileTest() {
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
                () -> FileService.readFile("null.txt"));
    }

    @Test
    void writeFileTest() {
        try {
            FileService.writeFile(workerHandler.getReport(), "out.txt");
            WorkerHandler<Worker> workerHandlerOut = FileService.readFile("out.txt");
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
                () -> FileService.readFile("out.jpeg"));
    }

    @Test
    void wrongFileOutputFormatTest() {
        assertThrowsExactly(IllegalArgumentException.class,
                () -> FileService.writeFile(workerHandler.toString(), "out.jpeg"));
    }
}