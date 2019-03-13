package com.crossal.helpers;

import java.io.File;

public class FileObjReaderFactory {
    public FileObjReader getJsonFileReader(File file) {
        return new JsonFileObjReader(file);
    }
}
