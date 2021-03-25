package ni.jug.greeting.control;

import ni.jug.greeting.entity.ErrorDefinition;

public class GreetingException extends RuntimeException {

    private final String code;

    public GreetingException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static GreetingException createNotFound(String language) {
        ErrorDefinition notFound = ErrorDefinition.LANG_NOT_SUPPORTED;
        return new GreetingException(notFound.getCode(), notFound.getMessage(language));
    }
}
