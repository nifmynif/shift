package com.koronaTech;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AppProperty {
    public static String sortField;
    public static String sortOrder;
    public static String outputType;
    public static String outputPath;
    public static String inputPath;

    static {
        init();
    }

    public static void init() {
        sortField = null;
        sortOrder = null;
        outputType = "console";
        outputPath = null;
        inputPath = null;
    }
}
