package team.ljm.secw.exception;

import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import team.ljm.secw.vo.ResponseVO;

@ControllerAdvice
public class GlobalExceptionResolver {

    @ExceptionHandler(value = {org.springframework.dao.DuplicateKeyException.class})
    @ResponseBody
    public ResponseVO handleDuplicateKeyException(Exception exception) {
        System.out.println("----------->"+exception.getClass());
        exception.printStackTrace();
        return new ResponseVO("500", "DuplicateKey");
    }

    // 捕捉shiro的异常
    //@ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {ShiroException.class})
    @ResponseBody
    public ResponseVO handleShiroException(Exception exception) {
        System.out.println("----->ShiroException.class");
        exception.printStackTrace();
        return new ResponseVO("401","Unauthorized!", exception.getMessage());
    }

    //@ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {UnauthorizedException.class})
    @ResponseBody
    public ResponseVO handleUnauthorizedException(Exception exception) {
        System.out.println("----->UnauthorizedException.class");
        exception.printStackTrace();
        return new ResponseVO("401", "Unauthorized!", exception.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public ResponseVO handleException(Exception exception) {
        System.out.println("----------->"+exception.getClass());
        exception.printStackTrace();
        return new ResponseVO("500", exception.getMessage());
    }

}
