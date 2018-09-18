package com.hht.cloud.webture.producer.model;

import java.io.Serializable;

public class ScreenshotRequest  implements Serializable {

    private String messageId;
    private String url;

    public ScreenshotRequest() {
    }

    public ScreenshotRequest(String messageId, String url) {
        this.messageId = messageId;
        this.url = url;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ScreenshotRequest{" +
                "messageId='" + messageId + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
