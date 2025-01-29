package com.koronaTech.controller;

import com.koronaTech.model.Employee;
import com.koronaTech.model.Manager;
import com.koronaTech.model.Worker;
import com.koronaTech.model.WorkerHandler;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Slf4j
@UtilityClass
public class FileController {

    public static WorkerHandler<Worker> readFile(String flePath) throws IOException {
        WorkerHandler<Worker> workerHandler = new WorkerHandler<>();
        checkFile(flePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(flePath))) {
            String line;
            while ((line = reader.readLine()) != null)
                try {
                    workerHandler.addWorker(getWorker(line.split(",")));
                } catch (IllegalArgumentException e) {
                    log.warn(e.toString());
                }
        }
        return workerHandler;
    }

    private static Worker getWorker(String[] parts) throws IllegalArgumentException {
        try {
            switch (parts[0].trim()) {
                case "Employee" -> {
                    return new Employee(
                            Integer.parseInt(parts[1].trim()),
                            parts[2].trim(),
                            Double.parseDouble(parts[3].trim()),
                            Integer.parseInt(parts[4].trim()));
                }

                case "Manager" -> {
                    return new Manager(
                            Integer.parseInt(parts[1].trim()),
                            parts[2].trim(),
                            Double.parseDouble(parts[3].trim()),
                            parts[4].trim());
                }

                default -> throw new IllegalArgumentException("Нет такого типа работника " + parts[0].trim());
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат " + Arrays.toString(parts));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Недостаточно информации для " + Arrays.toString(parts));
        }
    }

    private static void checkFile(String filePath) {
        if (!filePath.endsWith(".txt"))
            throw new IllegalArgumentException("Неверный формат файла");
    }

    public static void writeFile(String data, String filePath) throws IOException, IllegalArgumentException {
        checkFile(filePath);
        Files.write(Path.of(filePath), data.getBytes());
        log.info("Запись успешно завершена");
    }
}
