package com.koronaTech;

import static com.koronaTech.AppProperty.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }

    public static void checkArgs(String[] args) {
        for (String arg : args) {
            if (arg.startsWith("--sort=")) {
                sortField = arg.substring("--sort=".length());
            } else if (arg.startsWith("-s=")) {
                sortField = arg.substring("-s=".length());
            } else if (arg.startsWith("--order=")) {
                sortOrder = arg.substring("--order=".length());
            } else if (arg.startsWith("-o=")) {
                sortOrder = arg.substring("-o=".length());
            } else if (arg.equals("--output=console")) {
                outputType = "console";
            } else if (arg.equals("--output=file")) {
                outputType = "file";
            } else if (arg.startsWith("--path=")) {
                outputPath = arg.substring("--path=".length());
            } else {
                throw new IllegalArgumentException("Ошибка: Неверный аргумент - " + arg);
            }
        }
        if (sortField == null && sortOrder != null) {
            throw new IllegalArgumentException("Ошибка: Порядок сортировки указан без поля сортировки (--sort).");
        }
        if (sortField != null && !sortField.equals("name") && !sortField.equals("salary")) {
            throw new IllegalArgumentException("Ошибка: Неверное поле сортировки. Доступны: name, salary.");
        }
        if (sortOrder != null && !sortOrder.equals("asc") && !sortOrder.equals("desc")) {
            throw new IllegalArgumentException("Ошибка: Неверный порядок сортировки. Доступны: asc, desc.");
        }
        if (outputType.equals("file") && outputPath == null) {
            throw new IllegalArgumentException("Ошибка: Вывод в файл (--output=file) требует указания пути (--path=<путь>).");
        }
        if (outputPath != null && !outputType.equals("file")) {
            throw new IllegalArgumentException("Ошибка: Указан путь (--path), но не задан вывод в файл (--output=file).");
        }
    }
}