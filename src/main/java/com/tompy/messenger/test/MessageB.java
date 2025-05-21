package com.tompy.messenger.test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tompy.messenger.Message;

public class MessageB implements Message {
    @JsonIgnore
    private static final String TYPE = "mB";
    @JsonProperty
    private final String id;
    @JsonProperty
    private final Integer age;


    public MessageB() {
        this.id = "";
        this.age = 0;
    }

    private MessageB(Builder builder) {
        this.id = builder.id;
        this.age = builder.age;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static MessageB fromJson(String message) {
        ObjectMapper mapper = new ObjectMapper();
        MessageB value = null;
        try {
            value = mapper.readValue(message, MessageB.class);
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

    public Integer getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ID: " + id).append(System.lineSeparator()).append("AGE: " + age);

        return sb.toString();
    }

    public static final class Builder {
        private String id;
        private Integer age;
        private String message;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public MessageB build() {
            return new MessageB(this);
        }
    }
}
