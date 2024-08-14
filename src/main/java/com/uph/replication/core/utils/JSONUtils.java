package com.uph.replication.core.utils;

import ch.qos.logback.core.net.ObjectWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class JSONUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String objectToJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            log.info("Error parse object to json ", e);
            return null;
        }
    }

}
