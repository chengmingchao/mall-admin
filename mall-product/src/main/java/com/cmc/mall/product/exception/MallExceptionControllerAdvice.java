package com.cmc.mall.product.exception;

import com.cmc.common.utils.R;
import com.cmc.common.utils.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2020/8/20 10:20 下午
 * controller异常处理类
 */
@RestControllerAdvice(basePackages = "com.cmc.mall.product.controller")
@Slf4j
public class MallExceptionControllerAdvice {

    /**
     * 处理数据校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        Map<String,String> map=new HashMap<>();
        bindingResult.getFieldErrors().forEach((item)->{
            map.put(item.getField(),item.getDefaultMessage());
        });
        return R.error(ResultCode.VALIDATE_FAILED.getCode(),ResultCode.VALIDATE_FAILED.getMessage()).put("data",map);
    }

    /**
     * 处理其他异常
     * @param t
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public R handleException(Throwable t){
        t.printStackTrace();
        return R.error();
    }
}
