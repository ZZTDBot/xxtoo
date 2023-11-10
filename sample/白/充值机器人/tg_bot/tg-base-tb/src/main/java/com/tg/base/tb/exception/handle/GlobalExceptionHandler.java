package com.tg.base.tb.exception.handle;

import com.tg.base.tb.exception.BaseException;
import com.tg.base.tb.exception.ErrorInfo;
import com.tg.base.tb.exception.enum1.BaseErrorEnum;
import com.tg.base.tb.utils.JSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public JSONResult handleBaseException(HttpServletRequest request, BaseException exception) {
        ErrorInfo errorInfo = exception.getErrorInfo();
        logger.error("URL:" + request.getRequestURL());
        logger.error("BaseException: " + errorInfo, exception);
        return JSONResult.error(errorInfo.getStatusCode(), errorInfo.getMessage());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseBody
    public JSONResult handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException exception) {
        logger.error("URL:" + request.getRequestURL());
        logger.error("IllegalArgumentException: " + exception);
        return JSONResult.error(BaseErrorEnum.REQUEST_PARAM_ERROR.getCode(), exception.getMessage());
    }

    @ExceptionHandler(value = SQLException.class)
    @ResponseBody
    public JSONResult handlePSQLException(HttpServletRequest request, SQLException exception) {
        logger.error("URL:" + request.getRequestURL());
        logger.error("SQLException: ", exception);
        return JSONResult.error(BaseErrorEnum.DATABASE_ERROR.getCode(), BaseErrorEnum.DATABASE_ERROR.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JSONResult handleRuntimeException(HttpServletRequest request, Exception exception) {
        logger.error("URL:" + request.getRequestURL());
        logger.error("RuntimeException: ", exception);
        return JSONResult.error(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }
}
