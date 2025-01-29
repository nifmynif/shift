package com.koronaTech;

import com.koronaTech.model.Employee;
import com.koronaTech.model.Manager;
import com.koronaTech.model.Worker;
import com.koronaTech.model.WorkerHandler;
import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

@UtilityClass
public class FileController {

    public static WorkerHandler<Worker> readFile(String url) {
        WorkerHandler<Worker> workerHandler = new WorkerHandler<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(url))) {
            String line;
            while ((line = reader.readLine()) != null)
                try {
                    workerHandler.addWorker(getWorker(line.split(",")));
                } catch (IllegalArgumentException e) {
                    System.out.println(e);
                }

        } catch (IOException e) {
            System.out.println(e.getMessage());
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
}
