package com.tompy.messenger.service;

public class MessageInstance {
    protected static MessageServiceImpl service;
    /**
     * Retrieve the message service singleton
     *
     * @return com.tompy.messenger.Message Service singleton
     */
    public synchronized static MessageServiceImpl get() {
        if (service == null) {
            service = new MessageServiceImpl();
        }
        return service;
    }
}
