package com.cuonglv.learning_spring.utility.helper;

import com.google.gson.*;

import java.lang.reflect.Type;

import org.bson.types.ObjectId;

public class ObjectIdAdapter implements JsonSerializer<ObjectId>, JsonDeserializer<ObjectId> {
    @Override
    public JsonElement serialize(ObjectId src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }

    @Override
    public ObjectId deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return new ObjectId(json.getAsString());
    }
}
