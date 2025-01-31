package com.ets.r2dbc.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ErrorDetails {
	
	private Date timestamp;
	private String message;
	private String details;
	private HttpStatus httpStatus;
	
	
	
	public ErrorDetails(Date timestamp, String message, String details, HttpStatus httpStatus) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.httpStatus = httpStatus;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	@Override
	public String toString() {
		return "ErrorDetails [timestamp=" + timestamp + ", message=" + message + ", details=" + details
				+ ", httpStatus=" + httpStatus + "]";
	}

	
	

}
