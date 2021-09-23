package com.wust.system.exception;

import com.wust.system.util.Result;
import io.jsonwebtoken.SignatureException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * ~program: web
 * ~description: 全局异常处理类
 * ~author: zhuyingce
 * ~create: 2021-07-31
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    //  处理 json 请求体调用接口校验失败抛出的异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return Result.error(collect.get(0));
    }

    //处理 form-data方式调用接口校验失败抛出的异常
    @ExceptionHandler(BindException.class)
    public Result bindExceptionHandler(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return Result.error(collect.get(0));
    }

    //  处理单个参数校验失败抛出的异常
    @ExceptionHandler(ConstraintViolationException.class)
    public Result constraintViolationExceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        List<String> collect = constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        return Result.error(collect.get(0));
    }

    //token异常
    @ExceptionHandler(value = {SignatureException.class})     //捕捉jwt产生的异常
    public Result authorizationException(SignatureException e) {

        return Result.error(1008, e.getMessage());
    }

    //文件大小超出10MB异常
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result maxUploadSizeExceeded() {

        return Result.error("图片大小不可超出10MB");
    }
}
