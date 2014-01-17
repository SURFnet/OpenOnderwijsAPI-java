package nl.surfnet.oda.buildings;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Deserializes a list of buildings. Reuses the Building deserializer.
 * 
 * @author Daniel Zolnai
 * 
 */
public class BuildingsListDeserializer implements JsonDeserializer<List<Building>> {

    /**
     * Deserializes a GSON to a plain java object.
     */
    @Override
    public List<Building> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<Building> persons = new ArrayList<Building>();
        JsonObject root = json.getAsJsonObject();
        JsonArray data = root.get("data").getAsJsonArray();
        // use the person deserializer, since it is an array of persons
        BuildingDeserializer deserializer = new BuildingDeserializer();
        for (int i = 0; i < data.size(); ++i) {
            JsonElement jsonBuilding = data.get(i);
            Building person = deserializer.deserialize(jsonBuilding, typeOfT, context);
            persons.add(person);
        }
        return persons;
    }
}
