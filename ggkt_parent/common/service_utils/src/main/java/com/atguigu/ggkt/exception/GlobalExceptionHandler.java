package com.atguigu.ggkt.exception;

import com.atguigu.ggkt.Result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    //执行全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exception(Exception e){
        e.printStackTrace();
        return Result.fail().message("执行全局异常处理");
    }
    //执行特定异常处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result exception(ArithmeticException e){
        e.printStackTrace();
        return Result.fail().message("执行特定异常处理");
    }
    //执行自定义异常处理
    @ExceptionHandler(GGKTException.class)
    @ResponseBody
    public Result exception(GGKTException e){
        e.printStackTrace();
        return Result.fail().message(e.getMsg()).code(e.getCode());
    }
}
