package com.crossal.helpers;

import java.io.File;

public class FileReaderFactory {
    public JsonFileReader getJsonObjReader(File file) {
        return new JsonFileReader(new FileStreamFactory(), file);
    }
}
