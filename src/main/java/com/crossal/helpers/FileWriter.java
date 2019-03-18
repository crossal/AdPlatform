package com.crossal.helpers;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;

@Service
public class FileWriter {
    public void writeToFile(String fileName, String content) throws IOException {
        try (java.io.FileWriter fWriter = new java.io.FileWriter(fileName);
            BufferedWriter writer = new BufferedWriter(fWriter)) {
            writer.write(content);
        }
    }
}
