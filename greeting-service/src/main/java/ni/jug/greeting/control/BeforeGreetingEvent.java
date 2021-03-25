package ni.jug.greeting.control;

public class BeforeGreetingEvent {
    private final String language;

    public BeforeGreetingEvent(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }
}
