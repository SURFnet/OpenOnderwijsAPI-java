package nl.surfnet.oda.rooms;

import java.lang.reflect.Type;

import nl.surfnet.oda.EntityDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Deserializes a GSON to a Room object
 *
 * @author Daniel Zolnai
 *
 */
public class RoomDeserializer extends EntityDeserializer<Room> {

    @Override
    public Room deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final Room room = new Room();
        final JsonObject data = json.getAsJsonObject();
        room.setId(getAsStringNoNull(data.get("id")));
        room.setAbbreviation(getAsStringNoNull(data.get("abbr")));
        room.setBuildingUrl(getAsStringNoNull(data.get("building")));
        room.setDescription(getAsStringNoNull(data.get("description")));
        room.setName(getAsStringNoNull(data.get("name")));
        room.setTotalSeats(getAsIntegerNoNull(data.get("totalSeats")));
        room.setTotalWorkspaces(getAsIntegerNoNull(data.get("totalWorkspaces")));
        room.setAvailableWorkspaces(getAsIntegerNoNull(data.get("availableWorkspaces")));
        room.setLastModifiedDate(getAsDateNoNull(data.get("lastModified")));
        return room;
    }
}
