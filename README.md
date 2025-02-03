# ШИФТ

## Java

Java 17

## Сборка

Maven 4.0.0

## Сторонние библиотеки

```bash
            spring-boot-starter
            version: 3.4.2
            
            lombok
            version: 1.18.30
            
            spring-boot-starter-test
            version: 3.4.2
            
            junit-jupiter
            version: 5.10.2
```

## Запуск

Добавлена новая команда для выбора начального файла. Если она пустая то выбирается файл по умолчанию in.txt

```bash
--input=
```

В папке ~\out\artifacts\shift_jar есть jar файл и bat файл для его запуска с настройками вывода в консоль упорядоченного
по имени в прямом порядке и запись в файл упорядоченного по зарплате в обратном порядке

```bash
java -jar shift.jar --sort=name --order=asc --output=file --path=out.txt
java -jar shift.jar --input=in.txt --sort=salary --order=desc
```

Строка

```bash
chcp 65001
```

Предназначены для корректного вывода в консоль
