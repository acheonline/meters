package ru.achernyavskiy0n.util;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONUtil {

    @SneakyThrows
    public static String readFileAsString(String file) {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}
