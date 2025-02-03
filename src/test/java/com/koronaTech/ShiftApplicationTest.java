package com.koronaTech;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShiftApplicationTest {
    @Autowired
    private ShiftApplication shiftApplication;

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
    void setUp() {
        AppConfig appConfig = shiftApplication.getAppConfig();
        appConfig.setSortField(null);
        appConfig.setSortOrder(null);
        appConfig.setOutputType("console");
        appConfig.setOutputPath(null);
        appConfig.setInputPath(null);
    }

    @ParameterizedTest
    @MethodSource("validArgsFactory")
    void checkValidArgsTest(String args) {
        shiftApplication.validateArgs(args.split(" "));
        assertEquals("in.txt", shiftApplication.getAppConfig().getInputPath());
        assertNotNull(shiftApplication.getAppConfig().getSortField());
        assertNotNull(shiftApplication.getAppConfig().getSortOrder());
        assertNotNull(shiftApplication.getAppConfig().getOutputType());
        if (shiftApplication.getAppConfig().getOutputType().equals("file"))
            assertEquals("out.txt", shiftApplication.getAppConfig().getOutputPath());
    }


    @ParameterizedTest
    @MethodSource("invalidArgsFactory")
    void invalidArgsTest(String args) {
        assertThrowsExactly(IllegalArgumentException.class, () -> shiftApplication.validateArgs(args.split(" ")));
    }
}