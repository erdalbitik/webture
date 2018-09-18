package com.hht.cloud.webture.producer.model;


import java.io.Serializable;

public class ScreenshotResponse implements Serializable {

    private String messageId;
    private String url;
    private String screenshotUrl;

    public ScreenshotResponse() {
    }

    public ScreenshotResponse(String messageId, String url, String screenshotUrl) {
        this.messageId = messageId;
        this.url = url;
        this.screenshotUrl = screenshotUrl;
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

    public String getScreenshotUrl() {
        return screenshotUrl;
    }

    public void setScreenshotUrl(String screenshotUrl) {
        this.screenshotUrl = screenshotUrl;
    }

    @Override
    public String toString() {
        return "ScreenshotResponse{" +
                "messageId='" + messageId + '\'' +
                ", url='" + url + '\'' +
                ", screenshotUrl='" + screenshotUrl + '\'' +
                '}';
    }
}
