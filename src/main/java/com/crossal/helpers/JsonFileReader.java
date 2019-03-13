package com.crossal.helpers;

import com.crossal.bid.model.SeatBidResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.Files;

public class JsonFileReader<T> implements Closeable {

    private static final Logger logger = Logger.getLogger(JsonFileReader.class);

    private FileStreamFactory fileStreamFactory;
    private File file;
    private BufferedReader reader;

    public JsonFileReader(FileStreamFactory fileStreamFactory, File file) {
        this.fileStreamFactory = fileStreamFactory;
        this.file = file;
    }

//    private void openFile() throws IOException {
//        if (reader == null) {
//            reader = fileStreamFactory.getReader(file);
//        }
//    }

    public T readObj(Class<T> clazz) throws IOException {
//        String x = file.getAbsolutePath();
//        String y = file.getCanonicalPath();
//        String z = file.getPath();
//        Boolean a = file.exists();
//
//        File f2 = new File("C:/Users/Alan/workspace/AdPlatform/src/main/resources/bid-response.json");
//        Boolean a2 = f2.exists();
//        File f3 = new File("C:Users/Alan/workspace/AdPlatform/src/main/resources/bid-response.json");
//        Boolean a3 = f3.exists();
//        File f4 = new File("/C/Users/Alan/workspace/AdPlatform/src/main/resources/bid-response.json");
//        Boolean a4 = f4.exists();
//        InputStream inJson = clazz.getResourceAsStream(file.getAbsolutePath());
//        T obj = new ObjectMapper().readValue(inJson, clazz);


        String content = new String(Files.readAllBytes(file.toPath()));
        content = content.replaceAll("\\\\n", "\n");
        content = content.replaceAll("\\\\\\\\", "\\\\");
        T obj = new ObjectMapper().readValue(content, clazz);

        return obj;


//        File f = new File("file.json");
//        if (f.exists()){
//            InputStream is = new FileInputStream("file.json");
//            String jsonTxt = IOUtils.toString(is, "UTF-8");
//            System.out.println(jsonTxt);
//            JSONObject json = new JSONObject(jsonTxt);
//            String a = json.getString("1000");
//            System.out.println(a);
//        }
//
//
//
//        openFile();
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        String line = reader.readLine();
//
//        while (line != null) {
//            try {
//                T newObj = mapper.readValue(line, clazz);
//                return newObj;
//            } catch (Exception e) {
//                logger.info("A obj of type " + clazz + " was unparseable: " + e.getMessage());
//            }
//            line = reader.readLine();
//        }
//
//        return null;
    }

    @Override
    public void close() throws IOException {
        if (reader != null) {
            reader.close();
        }
    }
}
