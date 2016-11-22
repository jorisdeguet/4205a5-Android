package org.deguet;

import java.lang.reflect.Type;

import com.google.common.io.BaseEncoding;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class CodecByteArray implements JsonSerializer<byte[]>, JsonDeserializer<byte[]> {
    public JsonElement serialize(byte[] src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(BaseEncoding.base64().encode(src));
    }
    public byte[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return BaseEncoding.base64().decode(json.getAsJsonPrimitive().getAsString());
    }
}