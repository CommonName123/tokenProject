package jwtappdemo.json;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class JsonEntityViaUuidDeserializer<T extends ISerializableViaUuid> extends JsonDeserializer<ISerializableViaUuid> {

    @Override
    @SuppressWarnings("unchecked")
    public ISerializableViaUuid deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        T entity = null;

        String text = node.asText();
        if (text == null || text.isEmpty()) {
            return null;
        }
        try {
            entity = (T) handleType().newInstance();
            entity.setId(UUID.fromString(text));
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return entity;
    }

    public Class<?> handleType() {
        ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class) superClass.getActualTypeArguments()[0];
    }
}
