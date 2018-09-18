package com.hht.cloud.webture.producer.controller;

public class UrlDTO {
	
	private String url;
	
	private String fileType = "image";

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}
