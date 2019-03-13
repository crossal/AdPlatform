package com.crossal.helpers;

import java.io.IOException;

public interface FileObjReader<T> {
    T readObj(Class<T> clazz) throws IOException;
}
