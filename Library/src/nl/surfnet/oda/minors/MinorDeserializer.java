package nl.surfnet.oda.minors;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import nl.surfnet.oda.EntityDeserializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Deserializes a JSON to a Minor object
 *
 * @author Daniel Zolnai
 *
 */
public class MinorDeserializer extends EntityDeserializer<Minor> {

    @Override
    public Minor deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json == null || json.isJsonNull()) {
            return null;
        }
        JsonObject jsonMinor = json.getAsJsonObject();
        Minor minor = new Minor();
        minor.setId(getAsStringNoNull(jsonMinor.get("id")));
        minor.setDescription(getAsStringNoNull(jsonMinor.get("description")));
        minor.setName(getAsStringNoNull(jsonMinor.get("name")));
        minor.setResourceUrl(getAsStringNoNull(jsonMinor.get("url")));
        minor.setLastModified(getAsDateNoNull(jsonMinor.get("lastModified")));
        JsonArray jsonCourseUrls = jsonMinor.get("courses").getAsJsonArray();
        List<String> courseUrls = new ArrayList<String>();
        for (int i = 0; i < jsonCourseUrls.size(); ++i) {
            String courseUrl = jsonCourseUrls.get(i).getAsString();
            courseUrls.add(courseUrl);
        }
        minor.setCourseUrls(courseUrls);
        return minor;
    }

}
