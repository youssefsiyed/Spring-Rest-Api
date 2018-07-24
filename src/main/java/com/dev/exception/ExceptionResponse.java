package com.dev.exception;

import java.util.Date;

public class ExceptionResponse {
	
	private Date timestap;
	private String message;
	private String detail;
	
	////////////////////////////////////////
	public ExceptionResponse(Date timestap, String message, String detail) {
		super();
		this.timestap = timestap;
		this.message = message;
		this.detail = detail;
	}
	///////////////////////////////
	public Date getTimestap() {
		return timestap;
	}
	public void setTimestap(Date timestap) {
		this.timestap = timestap;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	

}
