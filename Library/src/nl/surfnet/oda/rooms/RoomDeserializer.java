package nl.surfnet.oda.rooms;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Deserializes a GSON to a Room object
 *
 * @author Daniel Zolnai
 *
 */
public class RoomDeserializer implements JsonDeserializer<Room> {

    @Override
    public Room deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final Room room = new Room();
        final JsonObject data = json.getAsJsonObject();
        room.setBuilding(_getAsStringNoNull(data.get("building")));
        room.setDescription(_getAsStringNoNull(data.get("description")));
        room.setName(_getAsStringNoNull(data.get("name")));
        room.setTotalSeats(_getAsIntegerNoNull(data.get("totalSeats")));
        room.setTotalWorkspaces(_getAsIntegerNoNull(data.get("totalWorkspaces")));
        room.setAvailableWorkspaces(_getAsIntegerNoNull(data.get("availableWorkspaces")));
        return room;
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
     * GSON can throw an error when it has to convert a null field to an integer. This method fixes that.
     *
     * @param element JSON element (which may be null)
     * @return null if the element was null, otherwise the value
     */
    private Integer _getAsIntegerNoNull(JsonElement element) {
        if (element == null || element.isJsonNull()) {
            return null;
        } else {
            return element.getAsInt();
        }
    }

}
