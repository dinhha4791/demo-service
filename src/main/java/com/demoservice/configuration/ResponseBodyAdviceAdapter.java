package com.demoservice.configuration;

import com.demoservice.constants.LoggingConstants;
import com.demoservice.constants.RestConstants;
import com.demoservice.rest.payload.common.RestResponse;
import com.demoservice.rest.payload.common.RestResponseHeader;
import com.demoservice.service.common.LoggingService;
import com.demoservice.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ResponseBodyAdviceAdapter implements ResponseBodyAdvice {
    @Autowired
    private LoggingService loggingService;

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof RestResponse) {
            RestResponse restResponse = (RestResponse) o;
            if (restResponse.getHeader() == null) {
                RestResponseHeader responseHeader = RestResponseHeader.builder()
                        .respCode(RestConstants.SUCCESS_CODE)
                        .respDesc(RestConstants.SUCCESS_DESC)
                        .build();
                responseHeader.setResponseDt(DateTimeUtil.getCurrentDateTime(RestConstants.DATETIME_PATTERN));
                restResponse.setHeader(responseHeader);
            }
        }

        loggingService.logHttpInfo(o, LoggingConstants.HTTP_TYPE_RESPONSE);
        return o;
    }
}
