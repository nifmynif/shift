package com.koronaTech;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class AppConfig {
    private String sortField;
    private String sortOrder;
    private String outputType = "console";
    private String outputPath;
    private String inputPath = "in.txt";
}
