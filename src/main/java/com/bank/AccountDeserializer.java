package com.bank;

import com.google.gson.*;

import java.lang.reflect.Type;

public class AccountDeserializer implements JsonDeserializer<Account> {

    @Override
    public Account deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String accountType = jsonObject.get("type").getAsString();
        JsonElement element = jsonObject.get("properties");
        try{
            return jsonDeserializationContext.deserialize(element, Class.forName("com.bank." + accountType));
        } catch (ClassNotFoundException e){
            throw new JsonParseException(e);
        }
    }
}
