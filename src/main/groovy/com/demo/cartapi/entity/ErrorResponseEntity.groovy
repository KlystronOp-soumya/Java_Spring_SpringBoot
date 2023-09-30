package com.demo.cartapi.entity

import java.sql.Date

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonRootName

@JsonRootName(value="errorResponse")
class ErrorResponseEntity implements Serializable {
	
	private String errorType
	private String errorMessage
	private String timeStamp
	private String date
	private String httpStatusCode
	private String httpStatusCodeDesc
	private Boolean hasErrors
	private String status
	@JsonIgnore
	private Throwable throwable
	public String getErrorType() {
		return errorType;
	}
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHttpStatusCode() {
		return httpStatusCode;
	}
	public void setHttpStatusCode(String httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}
	public Boolean getHasErrors() {
		return hasErrors;
	}
	public void setHasErrors(Boolean hasErrors) {
		this.hasErrors = hasErrors;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Throwable getThrowable() {
		return throwable;
	}
	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
	public String getHttpStatusCodeDesc() {
		return httpStatusCodeDesc;
	}
	public void setHttpStatusCodeDesc(String httpStatusCodeDesc) {
		this.httpStatusCodeDesc = httpStatusCodeDesc;
	}
	
	
	
}
