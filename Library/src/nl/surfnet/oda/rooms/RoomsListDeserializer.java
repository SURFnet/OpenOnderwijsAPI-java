package nl.surfnet.oda.rooms;

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
 * Deserializes a list of persons. Reuses the person deserializer.
 *
 * @author Daniel Zolnai
 *
 */
public class RoomsListDeserializer implements JsonDeserializer<List<Room>> {

    /**
     * Deserializes a GSON to a plain java object.
     */
    @Override
    public List<Room> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<Room> rooms = new ArrayList<Room>();
        JsonObject root = json.getAsJsonObject();
        JsonArray data = root.get("data").getAsJsonArray();
        // use the room deserializer, since it is an array of rooms
        RoomDeserializer deserializer = new RoomDeserializer();
        for (int i = 0; i < data.size(); ++i) {
            JsonElement jsonRoom = data.get(i);
            Room room = deserializer.deserialize(jsonRoom, typeOfT, context);
            rooms.add(room);
        }
        return rooms;
    }
}
