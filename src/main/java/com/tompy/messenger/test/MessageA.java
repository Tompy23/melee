package com.tompy.messenger.test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tompy.messenger.Message;

public class MessageA implements Message {
    @JsonIgnore
    private static final String TYPE = "mA";
    @JsonProperty
    private final String id;
    @JsonProperty
    private final String name;

    public MessageA() {
        this.id = "";
        this.name = "";
    }

    private MessageA(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static MessageA fromJson(String message) {
        ObjectMapper mapper = new ObjectMapper();
        MessageA value = null;
        try {
            value = mapper.readValue(message, MessageA.class);
        } catch (JsonProcessingException jpe) {
            jpe.printStackTrace();
        }
        return value;
    }

    @Override
    public String getJson() {
        ObjectMapper mapper = new ObjectMapper();
        String returnValue = null;
        try {
            returnValue = mapper.writeValueAsString(this);
        } catch (JsonProcessingException jpe) {
            jpe.printStackTrace();
        }

        return returnValue;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ID: " + id).append(System.lineSeparator()).append("NAME: " + name);

        return sb.toString();
    }

    public static final class Builder {
        private String id;
        private String name;
        private String message;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public MessageA build() {
            return new MessageA(this);
        }
    }
}
