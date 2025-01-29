package com.koronaTech.controller;

import com.koronaTech.model.Worker;
import com.koronaTech.model.WorkerHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.koronaTech.AppProperty.*;

@Slf4j
public class AppController {
    public static void run() {
        try {
            WorkerHandler<Worker> workerHandler = FileController.readFile(inputPath);
            switch (sortField) {
                case "name" -> workerHandler.sortByName(sortOrder.equals("asc"));
                case "salary" -> workerHandler.sortBySalary(sortOrder.equals("asc"));
            }
            switch (outputType) {
                case "file" -> FileController.writeFile(workerHandler.getReport(), outputPath);
                case "console" -> log.info(workerHandler.getReport());
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
