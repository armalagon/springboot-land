package ni.jug.meetup.entity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class SurrogateIdentifierSerializer extends JsonSerializer<SurrogateIdentifier> {
    @Override
    public void serialize(SurrogateIdentifier key, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(key.getId());
    }
}
