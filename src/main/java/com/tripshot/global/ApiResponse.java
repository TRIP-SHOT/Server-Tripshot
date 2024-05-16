package com.tripshot.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse<T> {
    private HttpStatus status;
    private String message;
    private T data;

    public ApiResponse() {}

    public ApiResponse(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(HttpStatus.OK, "Success", data);
    }

	public HttpStatus getStatus() {
		return status;
	}

//	public void setStatus(HttpStatus status) {
//		this.status = status;
//	}

	public String getMessage() {
		return message;
	}

//	public void setMessage(String message) {
//		this.message = message;
//	}

	public T getData() {
		return data;
	}

//	public void setData(T data) {
//		this.data = data;
//	}
    
    
}