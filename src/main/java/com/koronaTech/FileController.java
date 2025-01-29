package com.koronaTech;

import com.koronaTech.model.Employee;
import com.koronaTech.model.Manager;
import com.koronaTech.model.Worker;
import com.koronaTech.model.WorkerHandler;
import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.*;

@UtilityClass
public class FileController {
    public static final Logger logger = Logger.getLogger(FileController.class.getName());

    static {
        relog();
    }

    public static void relog() {
        try {
            for (Handler handler : logger.getHandlers()) {
                handler.close();
                logger.removeHandler(handler);
            }

            Files.deleteIfExists(Paths.get("Log.log"));

            FileHandler fileHandler = new FileHandler("Log.log", false);
            fileHandler.setFormatter(new SimpleFormatter());

            logger.addHandler(fileHandler);
            logger.setLevel(Level.FINE);

            logger.info("Лог-файл был очищен и переинициализирован.");
        } catch (Exception e) {
            logger.severe("Произошла ошибка при конфигурации логгера " + e);
        }
    }

    public static WorkerHandler<Worker> readFile(String flePath) throws IOException, IllegalArgumentException {
        WorkerHandler<Worker> workerHandler = new WorkerHandler<>();
        checkFile(flePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(flePath))) {
            String line;
            while ((line = reader.readLine()) != null)
                try {
                    workerHandler.addWorker(getWorker(line.split(",")));
                } catch (IllegalArgumentException e) {
                    logger.warning(e.toString());
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
        logger.info("Запись успешно завершена");
    }
}
