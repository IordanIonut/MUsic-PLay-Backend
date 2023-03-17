package com.example.MUsicPLay;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;

@Converter
public class JsonNodeConverter implements AttributeConverter<JsonNode, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(JsonNode attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonNode convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, JsonNode.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}