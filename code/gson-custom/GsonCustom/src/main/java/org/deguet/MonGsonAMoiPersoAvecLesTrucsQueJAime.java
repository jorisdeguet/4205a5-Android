package org.deguet;


import com.google.gson.*;
import org.joda.time.DateTime;

import java.lang.reflect.Type;
import java.util.Date;

public class MonGsonAMoiPersoAvecLesTrucsQueJAime {

    public static Gson getIt(){
        GsonBuilder builder = new GsonBuilder();
        builder.enableComplexMapKeySerialization(); // permet de serialiser des Map avec des cles definies par vous
        builder.registerTypeAdapter(DateTime.class, new CodecDateTime());
        builder.registerTypeAdapter(Date.class, new DateSerialiser());
        builder.registerTypeAdapter(byte[].class, new CodecByteArray());
        builder.setPrettyPrinting();
        return builder.create();
    }



    public static class DateSerialiser  implements JsonSerializer<Date>,JsonDeserializer<Date> {
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(new DateTime(src).toString());
        }
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return new DateTime(json.getAsJsonPrimitive().getAsString()).toDate();
        }
    }

}
