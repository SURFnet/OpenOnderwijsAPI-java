package nl.surfnet.oda.buildings;

import java.lang.reflect.Type;

import nl.surfnet.oda.EntityDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Deserializes a GSON to a Building object
 *
 * @author Daniel Zolnai
 *
 */
public class BuildingDeserializer extends EntityDeserializer<Building> {

    @Override
    public Building deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final Building building = new Building();
        final JsonObject data = json.getAsJsonObject();
        building.setId(getAsStringNoNull(data.get("id")));
        building.setAbbreviation(getAsStringNoNull(data.get("abbr")));
        building.setAddress(getAsStringNoNull(data.get("address")));
        building.setCity(getAsStringNoNull(data.get("city")));
        building.setDescription(getAsStringNoNull(data.get("description")));
        building.setLatitude(getAsDoubleNoNull(data.get("lat")));
        building.setLongitude(getAsDoubleNoNull(data.get("lon")));
        building.setName(getAsStringNoNull(data.get("name")));
        building.setPostalCode(getAsStringNoNull(data.get("postalCode")));
        building.setResourceUrl(getAsStringNoNull(data.get("url")));
        building.setLastModifiedDate(getAsDateNoNull(data.get("lastModified")));
        return building;
    }
}
