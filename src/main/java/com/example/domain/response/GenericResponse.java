package com.example.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "address", "distance", "openingHours", "functionality", "type", "errorMessage", "status" })
public class GenericResponse<T> {
	
	@JsonProperty("errorMessage")
	private String errorMessage;
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("reposnse")
	private T reposnse;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public T getReposnse() {
		return reposnse;
	}

	public void setReposnse(T reposnse) {
		this.reposnse = reposnse;
	}
}
