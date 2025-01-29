package com.koronaTech;

public class AppProperty {
    public static String sortField;
    public static String sortOrder;
    public static String outputType;
    public static String outputPath;

    static {
        init();
    }

    public static void init() {
        sortField = null;
        sortOrder = null;
        outputType = "console";
        outputPath = null;
    }
}
