package nl.surfnet.oda.buildings;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Deserializes a GSON to a Person object
 *
 * @author Daniel Zolnai
 *
 */
public class BuildingDeserializer implements JsonDeserializer<Building> {

    @Override
    public Building deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final Building building = new Building();
        final JsonObject data = json.getAsJsonObject();
        building.setAddress(_getAsStringNoNull(data.get("address")));
        building.setCity(_getAsStringNoNull(data.get("city")));
        building.setDescription(_getAsStringNoNull(data.get("description")));
        building.setLatitude(_getAsDoubleNoNull(data.get("lat")));
        building.setLongitude(_getAsDoubleNoNull(data.get("lon")));
        building.setName(_getAsStringNoNull(data.get("name")));
        building.setPostalCode(_getAsStringNoNull(data.get("postalCode")));
        building.setResourceUrl(_getAsStringNoNull(data.get("url")));
        return building;
    }

    /**
     * GSON throws an error if a field is null and we try to convert it to a String. This method handles that.
     *
     * @param element JSONElement which might be null
     * @return null if the element is null, otherwise the value as a string
     */
    private String _getAsStringNoNull(JsonElement element) {
        if (element == null || element.isJsonNull()) {
            return null;
        } else {
            return element.getAsString();
        }
    }

    /**
     * GSON can throw an error, when converting a null JSONElement to a Double.
     *
     * @param element JSONElement which might be null
     * @return null if the data is null, otherwise the value
     */
    private Double _getAsDoubleNoNull(JsonElement element) {
        if (element == null || element.isJsonNull()) {
            return null;
        } else {
            return element.getAsDouble();
        }
    }

}
