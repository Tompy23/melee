package com.tompy.messenger;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface Message {
    /**
     * Return the JSON String of the message
     *
     * @return The JSON of the message
     */
    @JsonIgnore
    String getJson();

    /**
     * Return the type of the com.tompy.messenger.Message
     *
     * @return
     */
    @JsonIgnore
    String getType();
}
