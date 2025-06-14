package edu.gdou.recipebackend.common.handler;


import edu.gdou.recipebackend.core.entity.base.Result;
import lombok.extern.log4j.Log4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        BindingResult result = e.getBindingResult();
        String s = "参数验证失败";
        if(result.hasErrors()){
            List<ObjectError> errors = result.getAllErrors();
            s = errors.get(0).getDefaultMessage();
        }
        return Result.error(s);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result requestExceptionHandler(Exception e){
        e.printStackTrace();
        return Result.error("内部错误");
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public Result requestRuntimeExceptionHandler(RuntimeException e){
        e.printStackTrace();
        return Result.error(e.getMessage());
    }



}
