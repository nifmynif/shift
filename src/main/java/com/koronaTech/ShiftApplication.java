package com.koronaTech;

import com.koronaTech.services.WorkerService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
@Getter
public class ShiftApplication {
    private final WorkerService workerService;
    private final AppConfig appConfig;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ShiftApplication.class, args);
        ShiftApplication app = context.getBean(ShiftApplication.class);
        app.validateArgs(args);
        app.workerService.processData();
    }

    public void validateArgs(String... args) {
        for (String arg : args) {
            if (arg.startsWith("--sort=")) {
                appConfig.setSortField(arg.substring("--sort=".length()));
            } else if (arg.startsWith("-s=")) {
                appConfig.setSortField(arg.substring("-s=".length()));
            } else if (arg.startsWith("--order=")) {
                appConfig.setSortOrder(arg.substring("--order=".length()));
            } else if (arg.startsWith("-o=")) {
                appConfig.setSortOrder(arg.substring("-o=".length()));
            } else if (arg.equals("--output=console")) {
                appConfig.setOutputType("console");
            } else if (arg.equals("--output=file")) {
                appConfig.setOutputType("file");
            } else if (arg.startsWith("--path=")) {
                appConfig.setOutputPath(arg.substring("--path=".length()));
            } else if (arg.startsWith("--input=")) {
                appConfig.setInputPath(arg.substring("--input=".length()));
            } else {
                throw new IllegalArgumentException("Ошибка: Неверный аргумент - " + arg);
            }
        }

        if (appConfig.getSortField() == null && appConfig.getSortOrder() != null) {
            throw new IllegalArgumentException(
                    "Ошибка: Порядок сортировки указан без поля сортировки (--sort).");
        }

        if (appConfig.getSortField() != null &&
                !appConfig.getSortField().equals("name") &&
                !appConfig.getSortField().equals("salary")) {
            throw new IllegalArgumentException(
                    "Ошибка: Неверное поле сортировки. Доступны: name, salary.");
        }

        if (appConfig.getSortOrder() != null &&
                !appConfig.getSortOrder().equals("asc") &&
                !appConfig.getSortOrder().equals("desc")) {
            throw new IllegalArgumentException(
                    "Ошибка: Неверный порядок сортировки. Доступны: asc, desc.");
        }

        if (appConfig.getOutputType().equals("file") && appConfig.getOutputPath() == null) {
            throw new IllegalArgumentException(
                    "Ошибка: Вывод в файл (--output=file) требует указания пути (--path=<путь>).");
        }

        if (appConfig.getOutputPath() != null && !appConfig.getOutputType().equals("file")) {
            throw new IllegalArgumentException(
                    "Ошибка: Указан путь (--path), но не задан вывод в файл (--output=file).");
        }

        if (appConfig.getInputPath() == null) {
            appConfig.setInputPath("in.txt");
        }

        log.debug("Аргументы успешно обработаны: sortField={}, sortOrder={}, outputType={}, outputPath={}, inputPath={}",
                appConfig.getSortField(),
                appConfig.getSortOrder(),
                appConfig.getOutputType(),
                appConfig.getOutputPath(),
                appConfig.getInputPath());
    }
}
