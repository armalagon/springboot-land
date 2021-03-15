package ni.jug.demo.greeting.entity;

import ni.jug.demo.greeting.control.GreetingException;

public class ErrorInfo {
    private final String url;
    private final String code;
    private final String message;

    public ErrorInfo(String url, GreetingException ex) {
        this.url = url;
        this.code = ex.getCode();
        this.message = ex.getMessage();
    }

    public String getUrl() {
        return url;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
