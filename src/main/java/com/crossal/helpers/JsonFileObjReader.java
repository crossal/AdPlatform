package com.crossal.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.Files;

public class JsonFileObjReader<T> implements FileObjReader<T> {

    private static final Logger logger = Logger.getLogger(JsonFileObjReader.class);

    private File file;

    public JsonFileObjReader(File file) {
        this.file = file;
    }

    public T readObj(Class<T> clazz) throws IOException {

        String content = new String(Files.readAllBytes(file.toPath()));
        T obj = new ObjectMapper().readValue(content, clazz);

        return obj;
    }
}
