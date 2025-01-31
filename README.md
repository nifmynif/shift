# ШИФТ

## Java
Java 17
## Сборка
Maven
## Сторонние библиотеки
```bash
         <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.10.2</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.10.2</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.36</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>2.0.9</version>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.4.12</version>
        </dependency>
```
## Запуск
Добавлена новая команда для выбора начального файла. Если она пустая то выбирается файл по умолчанию in.txt
```bash
--input=
```
В папке ~\out\artifacts\shift_jar есть jar файл и bat файл для его запуска с настройками вывода в консоль упорядоченного по имени в прямом порядке и запись в файл упорядоченного по  зарплате в обратном порядке
```bash
java -Dfile.encoding=UTF-8 -jar shift.jar --sort=name --order=asc --output=file --path=out.txt
java -Dfile.encoding=UTF-8 -jar shift.jar --input=in.txt --sort=salary --order=desc
```
Строки
```bash
chcp 65001
-Dfile.encoding=UTF-8
```
Предназначены для корректного вывода в консоль
