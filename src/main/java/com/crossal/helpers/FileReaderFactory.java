package com.crossal.helpers;

import java.io.File;

public class FileReaderFactory {
    public JsonFileReader getJsonFileReader(File file) {
        return new JsonFileReader(file);
    }
}
