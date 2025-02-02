package com.koronaTech;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    static Stream<Arguments> validArgsFactory() {
        return Stream.of(
                Arguments.of("--sort=name --order=asc --output=file --path=out.txt"),
                Arguments.of("--input=in.txt -s=salary -o=desc --output=console")
        );
    }

    static Stream<Arguments> invalidArgsFactory() {
        return Stream.of(
                Arguments.of("--get=k"),
                Arguments.of("--sort=namep"),
                Arguments.of("--order=asc"),
                Arguments.of("--sort=name --order=ascd"),
                Arguments.of("--output=file"),
                Arguments.of("--path=out.txt"),
                Arguments.of("--path=out.txt"),
                Arguments.of("---output=file ---path=out.txt")
        );
    }

    @BeforeEach
    public void init() {
        AppProperty.init();
    }

    @ParameterizedTest
    @MethodSource("validArgsFactory")
    void checkValidArgsTest(String args) {
        Main.checkArgs(args.split(" "));
        assertEquals("in.txt", AppProperty.inputPath);
        assertNotNull(AppProperty.sortField);
        assertNotNull(AppProperty.sortOrder);
        assertNotNull(AppProperty.outputType);
        if (AppProperty.outputType.equals("file"))
            assertEquals("out.txt", AppProperty.outputPath);
    }


    @ParameterizedTest
    @MethodSource("invalidArgsFactory")
    void invalidArgsTest(String args) {
        assertThrowsExactly(IllegalArgumentException.class, () -> Main.checkArgs(args.split(" ")));
    }
}