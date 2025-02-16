package com.polishealt.hospital_management_v2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {
 
	 @ExceptionHandler(RuntimeException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST) // Kthen 400 në vend të 500
	    @ResponseBody
	    public Map<String, String> handleRuntimeException(RuntimeException ex) {
	        Map<String, String> errorResponse = new HashMap<>();
	        errorResponse.put("error", "Bad Request");
	        errorResponse.put("message", ex.getMessage());
	        return errorResponse;
	    }
	}
