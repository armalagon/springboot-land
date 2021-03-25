package ni.jug.greeting.entity;

public enum ErrorDefinition {

    LANG_NOT_SUPPORTED("GNG:100", "Language [%s] not supported!");

    private final String code;
    private final String template;

    ErrorDefinition(String code, String template) {
        this.code = code;
        this.template = template;
    }

    public String getCode() {
        return code;
    }

    public String getMessage(String... args) {
        return code + ": " + String.format(template, args);
    }
}
