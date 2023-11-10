package com.tg.base.tb.exception;

import com.tg.base.tb.exception.enum1.BaseErrorEnum;

public class BaseException extends RuntimeException {

    private ErrorInfo errorInfo;

    public BaseException(){
        super();
    }

    public BaseException(String message){
        super(message);
    }

    public BaseException(String statusCode, String message) {
        super(message);
        this.errorInfo = new ErrorInfo(statusCode, message);
    }

    public BaseException(BaseErrorEnum baseErrorEnum) {
        super(baseErrorEnum.getMessage());
        this.errorInfo = new ErrorInfo(baseErrorEnum.getCode(), baseErrorEnum.getMessage());
    }


    public ErrorInfo getErrorInfo() {
        return this.errorInfo;
    }

    public void setErrorInfo(String statusCode, String message) {
        this.errorInfo = new ErrorInfo(statusCode, message);
    }



}
