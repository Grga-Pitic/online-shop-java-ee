package service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Writer;

public class JsonService {
    public static void sendJson(Writer writer, Object object)
            throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, object);

    }
}
