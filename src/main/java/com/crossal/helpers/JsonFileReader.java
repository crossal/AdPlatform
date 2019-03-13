package com.crossal.helpers;

import com.crossal.bid.model.SeatBidResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.Files;

public class JsonFileReader<T> {

    private static final Logger logger = Logger.getLogger(JsonFileReader.class);

    private File file;

    public JsonFileReader(File file) {
        this.file = file;
    }

    public T readObj(Class<T> clazz) throws IOException {

        String content = new String(Files.readAllBytes(file.toPath()));
        T obj = new ObjectMapper().readValue(content, clazz);

        return obj;
    }
}
