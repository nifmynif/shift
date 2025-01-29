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
}