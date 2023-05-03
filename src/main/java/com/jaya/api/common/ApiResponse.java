package com.jaya.api.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ApiResponse<T> {
    private T data;
    private List<String> errors;
    private HttpStatus httpStatus;

    public ApiResponse(T data){
        this.data = data;
    }
    public ApiResponse(List<String> errors){
        this.errors = errors;
    }

    public ApiResponse(String addedToWishlist, HttpStatus httpStatus) {
    }

    public ApiResponse(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getTimestamp() {
        return LocalDateTime.now().toString();
    }
}
