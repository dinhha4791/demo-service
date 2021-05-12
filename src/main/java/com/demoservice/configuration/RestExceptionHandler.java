package com.demoservice.configuration;

import com.demoservice.rest.payload.common.RestResponse;
import com.demoservice.rest.payload.common.RestResponseHeader;
import com.demoservice.constants.RestConstants;
import com.demoservice.exception.BusinessException;
import com.demoservice.utils.DateTimeUtil;
import com.demoservice.service.common.LoggingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private LoggingService loggingService;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        loggingService.logError(e);

        FieldError fieldError = e.getBindingResult().getFieldError();
        String errorDesc = fieldError.getField() + " " + fieldError.getDefaultMessage();
        RestResponse restResponse = createRestResponse(RestConstants.BUSINESS_EXCEPTION_CODE, errorDesc);
        return ResponseEntity.ok().body(restResponse);
    }

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<Object> handleBusinessException(BusinessException e, WebRequest request) {
        loggingService.logError(e);

        String exceptionCode = StringUtils.isNotEmpty(e.getErrorCode()) ? e.getErrorCode() : RestConstants.BUSINESS_EXCEPTION_CODE;
        String exceptionDesc = StringUtils.isNotEmpty(e.getErrorDesc()) ? e.getErrorDesc() : RestConstants.BUSINESS_EXCEPTION_DESC;
        RestResponse restResponse = createRestResponse(exceptionCode, exceptionDesc);
        return ResponseEntity.ok().body(restResponse);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleCommonException(Exception e, WebRequest request) {
        loggingService.logError(e);

        RestResponse restResponse = createRestResponse(RestConstants.SYSTEM_ERROR_CODE, RestConstants.SYSTEM_ERROR_DESC);
        return ResponseEntity.ok().body(restResponse);
    }

    private RestResponse createRestResponse(String errorCode, String errorDesc) {
        RestResponseHeader responseHeader = RestResponseHeader.builder()
                .respCode(errorCode)
                .respDesc(errorDesc)
                .build();

        responseHeader.setResponseDt(DateTimeUtil.getCurrentDateTime(RestConstants.DATETIME_PATTERN));
        return RestResponse.builder().header(responseHeader).build();
    }

}
