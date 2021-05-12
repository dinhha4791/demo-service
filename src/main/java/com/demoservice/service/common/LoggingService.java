package com.demoservice.service.common;

import com.demoservice.constants.LoggingConstants;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

@Service
@Log4j
public class LoggingService {

    public void logInfo(String header, String message) {
        StringBuilder builder = new StringBuilder();
        builder.append(header + " - ");
        builder.append(message);
        log.info(builder.toString());
    }

    public void logHttpInfo(Object body, String logType) {
        StringBuilder builder = new StringBuilder();
        String type = logType == LoggingConstants.HTTP_TYPE_REQUEST ? LoggingConstants.HTTP_LOG_REQUEST : LoggingConstants.HTTP_LOG_RESPONSE;
        builder.append(type);
        builder.append(String.format(LoggingConstants.HTTP_LOG_BODY, new Gson().toJson(body)));
        log.info(builder.toString());
    }

    public void logError(Exception e) {
        log.error(e.getMessage());
        log.error(ExceptionUtils.getStackTrace(e));
    }
}
