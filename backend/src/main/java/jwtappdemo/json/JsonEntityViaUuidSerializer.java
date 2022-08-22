package jwtappdemo.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonEntityViaUuidSerializer extends JsonSerializer<ISerializableViaUuid> {

    @Override
    public void serialize(ISerializableViaUuid entity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(entity.getId().toString());
    }
}
