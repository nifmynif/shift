package com.koronaTech.services;

import com.koronaTech.AppConfig;
import com.koronaTech.model.Worker;
import com.koronaTech.model.WorkerHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@AllArgsConstructor
@Service
@Slf4j
public class WorkerService {
    private final AppConfig appConfig;

    public void processData() {
        try {
            WorkerHandler<Worker> workerHandler = FileService.readFile(appConfig.getInputPath());

            if ("name".equals(appConfig.getSortField())) {
                workerHandler.sortByName("asc".equals(appConfig.getSortOrder()));
            } else if ("salary".equals(appConfig.getSortField())) {
                workerHandler.sortBySalary("asc".equals(appConfig.getSortOrder()));
            }

            String report = workerHandler.getReport();
            if ("file".equals(appConfig.getOutputType())) {
                FileService.writeFile(report, appConfig.getOutputPath());
            } else {
                log.info(report);
            }

        } catch (IOException e) {
            log.error("Error processing file: {}", e.getMessage());
        }
    }
}
