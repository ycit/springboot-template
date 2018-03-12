package com.vastio.basic.controller;

import com.vastio.basic.VastioException;
import com.vastio.basic.entity.response.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

/**
 * 异常处理
 *
 * @author xlch
 * @Date 2018-02-26 10:28
 */
@ControllerAdvice(annotations = RestController.class)
public class RestControllerAdvice {

    private MessageSource messageSource;

    @Autowired
    public RestControllerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(RestControllerAdvice.class);

    @ExceptionHandler
    @ResponseBody
    public ResponseResult generalError(Exception ex, HttpServletRequest request) {
        LOGGER.error("Error 500: {}, {}", ex.getMessage(), ex.getStackTrace()[0]);
        ex.printStackTrace();
        ResponseResult error = new ResponseResult();
        error.setCode(500);
        error.setMessage("服务器内部错误");
        return error;
    }


    @ExceptionHandler(VastioException.class)
    @ResponseBody
    public ResponseResult customException(Exception ex, HttpServletRequest request) {
        LOGGER.error("custom exception: {}, {}", ex.getMessage(), ex.getStackTrace()[0]);
        ex.printStackTrace();
        ResponseResult error = new ResponseResult();
        error.setCode(400);
        error.setMessage(ex.getMessage());
        return error;
    }

    @ExceptionHandler({IllegalArgumentException.class, MissingServletRequestParameterException.class})
    @ResponseBody
    public ResponseResult typeMismatch(Exception ex, HttpServletRequest request) {
        ResponseResult error = new ResponseResult();
        error.setCode(400);
        error.setMessage(ex.getMessage());
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseResult processValidationError(MethodArgumentNotValidException ex, HttpServletRequest request) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    private ResponseResult processFieldErrors(List<FieldError> fieldErrors) {
        ResponseResult error = new ResponseResult();
        error.setCode(500);
        for (FieldError fieldError : fieldErrors) {
            resolveLocalizedErrorMessage(fieldError);
            error.setMessage(fieldError.getField() + fieldError.getDefaultMessage());
        }
        return error;
    }

    private String resolveLocalizedErrorMessage(FieldError fieldError) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(fieldError, currentLocale);
    }

}
