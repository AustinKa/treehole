package net.com.treehole.treeholecore.utils.exception;

import lombok.extern.slf4j.Slf4j;
import net.com.treehole.treeholecore.utils.result.Result;
import net.com.treehole.treeholecore.utils.result.ResultMsgEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomerExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result Execption(Exception e) {
        log.error("未知异常！", e);
        return Result.error(ResultMsgEnum.SERVER_BUSY.getCode(), ResultMsgEnum.SERVER_BUSY.getMessage());
    }

}
