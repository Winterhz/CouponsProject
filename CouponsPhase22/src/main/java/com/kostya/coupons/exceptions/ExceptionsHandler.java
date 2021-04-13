package com.kostya.coupons.exceptions;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kostya.coupons.dto.errorDto.ErrorBean;
import com.kostya.coupons.enums.ErrorType;

@RestControllerAdvice
public class ExceptionsHandler {

	//	Response - Object in Spring
	@ExceptionHandler
	@ResponseBody
	// Variable name is throwable in order to remember that it handles Exception and Error
	public ErrorBean toResponse(Throwable throwable, HttpServletResponse response) {
		
		//	ErrorBean errorBean;
		if(throwable instanceof ApplicationException) {
		
			ApplicationException appException = (ApplicationException) throwable;

			ErrorType errorType = appException.getErrorType(); 
			int errorNumber = errorType.getErrorNumber();
			String errorMessage = errorType.getErrorMessage();
			response.setStatus(errorNumber);	

			ErrorBean errorBean = new ErrorBean(errorNumber ,errorMessage); 
			if(appException.getErrorType().isShowStackTrace()) {
				appException.printStackTrace();
			}
			
			return errorBean;
		}
		
		response.setStatus(600);

		String errorMessage = throwable.getMessage();
		ErrorBean errorBean = new ErrorBean(601, "General error", errorMessage);
		throwable.printStackTrace();

		return errorBean;
	}

}
