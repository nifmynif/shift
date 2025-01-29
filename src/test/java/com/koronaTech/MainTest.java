package com.koronaTech;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @BeforeEach
    public void init() {
        AppProperty.init();
    }

    @Test
    void checkArgsTest() {
        String[] args = {"--sort=name", "--order=asc", "--output=file", "--path=out.txt"};
        Main.checkArgs(args);
        assertEquals("name", AppProperty.sortField);
        assertEquals("asc", AppProperty.sortOrder);
        assertEquals("file", AppProperty.outputType);
        assertEquals("out.txt", AppProperty.outputPath);
    }

    @Test
    void checkShortArgsTest() {
        String[] args = {"-s=salary", "-o=desc", "--output=console"};
        Main.checkArgs(args);
        assertEquals("salary", AppProperty.sortField);
        assertEquals("desc", AppProperty.sortOrder);
        assertEquals("console", AppProperty.outputType);
        assertNull(AppProperty.outputPath);
    }

    @Test
    void wrongArgsTest() {
        String[] args = {"--get=k"};
        assertThrowsExactly(IllegalArgumentException.class, () -> Main.checkArgs(args));
    }

    @Test
    void wrongSortArgsTest() {
        String[] args = {"--sort=namep"};
        assertThrowsExactly(IllegalArgumentException.class, () -> Main.checkArgs(args));
    }

    @Test
    void nullSortArgsTest() {
        String[] args = {"--order=asc"};
        assertThrowsExactly(IllegalArgumentException.class, () -> Main.checkArgs(args));
    }

    @Test
    void wrongOrderArgsTest() {
        String[] args = {"--sort=name", "--order=ascd"};
        assertThrowsExactly(IllegalArgumentException.class, () -> Main.checkArgs(args));
    }

    @Test
    void nullPathArgsTest() {
        String[] args = {"--output=file"};
        assertThrowsExactly(IllegalArgumentException.class, () -> Main.checkArgs(args));
    }

    @Test
    void nullOutputTypeArgsTest() {
        String[] args = {"--path=out.txt"};
        assertThrowsExactly(IllegalArgumentException.class, () -> Main.checkArgs(args));
    }
}