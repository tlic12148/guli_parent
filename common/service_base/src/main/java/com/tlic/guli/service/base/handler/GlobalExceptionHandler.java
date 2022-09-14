package com.tlic.guli.service.base.handler;

import com.tlic.guli.common.base.result.R;
import com.tlic.guli.common.base.result.ResultCodeEnum;
import com.tlic.guli.common.base.util.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice // 切面注解，用于全局统一处理事项
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) // 异常处理注解
    @ResponseBody
    public R error(Exception e) {
        log.error(e.getMessage());
        return R.error();
    }

    @ExceptionHandler(BadSqlGrammarException.class) // 异常处理注解
    @ResponseBody
    public R error(BadSqlGrammarException e) {
        log.error(ExceptionUtils.getMessage(e));
        return R.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public R error(HttpMessageNotReadableException e) {
        log.error(ExceptionUtils.getMessage(e));
        return R.setResult(ResultCodeEnum.JSON_PARSE_ERROR);
    }
}
