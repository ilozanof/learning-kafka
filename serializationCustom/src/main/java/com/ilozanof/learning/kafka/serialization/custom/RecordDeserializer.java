package com.ilozanof.learning.kafka.serialization.custom;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * @author i.fernandez@nchain.com
 * @date 2020-04-23 11:49
 */
public class RecordDeserializer implements Deserializer<Record> {
    @Override
    public Record deserialize(String arg0, byte[] arg1) {
        ObjectMapper objectMapper = new ObjectMapper();
        Record result = null;
        try {
            result = objectMapper.readValue(arg1, Record.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
