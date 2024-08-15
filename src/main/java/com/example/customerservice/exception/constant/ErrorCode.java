package com.example.customerservice.exception.constant;

import com.example.customerservice.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode implements ExceptionCode {
    CUSTOMER_ALREADY_EXIST("C0001","customer with username: {} is already existed", HttpStatus.INTERNAL_SERVER_ERROR),

    SYSTEM_BUSY("C0002","System busy", HttpStatus.INTERNAL_SERVER_ERROR),
    JWT_ID_NOT_MATCH("C0003", "Jwt id is not matched with corresponding customer", HttpStatus.UNAUTHORIZED);

    private final String errorCode;
    private final String message;

    private final HttpStatus statusCode;

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage(Object... arguments) {
        if(arguments == null){
            return message;
        }
        return new ParameterizedMessage(message, arguments).getFormattedMessage();
    }

    @Override
    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
