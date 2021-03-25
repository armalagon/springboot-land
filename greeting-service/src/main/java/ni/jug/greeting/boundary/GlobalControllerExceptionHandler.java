package ni.jug.greeting.boundary;

import ni.jug.greeting.control.GreetingException;
import ni.jug.greeting.entity.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(GreetingException.class)
    public ErrorInfo handleGreetingNotFound(HttpServletRequest request, GreetingException ex) {
        return new ErrorInfo(request.getRequestURI(), ex);
    }
}
