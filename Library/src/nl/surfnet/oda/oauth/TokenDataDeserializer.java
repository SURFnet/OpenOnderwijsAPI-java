package nl.surfnet.oda.oauth;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;

import nl.surfnet.oda.EntityDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Deserializes a token data into a java object.
 *
 * @author Daniel Zolnai
 * 
 */
public class TokenDataDeserializer extends EntityDeserializer<TokenData> {

    @Override
    public TokenData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json == null || json.isJsonNull()) {
            return null;
        }
        TokenData tokenData = new TokenData();
        JsonObject jsonTokenData = json.getAsJsonObject();
        tokenData.setAccessToken(getAsStringNoNull(jsonTokenData.get("access_token")));
        tokenData.setRefreshToken(getAsStringNoNull(jsonTokenData.get("refresh_token")));
        int seconds = getAsIntegerNoNull(jsonTokenData.get("expires_in")); // offset from current time in seconds
        Date expiryDate = new Date(Calendar.getInstance().getTime().getTime() + seconds * 1000);
        tokenData.setExpiryDate(expiryDate);
        return tokenData;
    }

}
