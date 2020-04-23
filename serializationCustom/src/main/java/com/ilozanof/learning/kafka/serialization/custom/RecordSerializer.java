package com.ilozanof.learning.kafka.serialization.custom;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

/**
 * @author i.fernandez@nchain.com
 * @date 2020-04-23 11:45
 */
public class RecordSerializer implements Serializer<Record> {

    @Override
    public byte[] serialize(String arg0, Record arg1) {
        byte[] result = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            result = objectMapper.writeValueAsString(arg1).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
