package nl.surfnet.oda;

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
 * Deserializes a list of entities.
 *
 * @author Daniel Zolnai
 *
 */
public class ListDeserializer<T> implements JsonDeserializer<List<T>> {

    JsonDeserializer<T> _entityDeserializer;

    /**
     * Constructor. Add the entity deserializer as parameter.
     *
     * @param entityDeserializer Deserializer of the entity. If you are deserializing a list of Persons, this should be the PersonDeserializer
     */
    public ListDeserializer(JsonDeserializer<T> entityDeserializer) {
        _entityDeserializer = entityDeserializer;
    }

    /**
     * Deserializes a GSON to a list of plain java objects.
     */
    @Override
    public List<T> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<T> entities = new ArrayList<T>();
        JsonObject root = json.getAsJsonObject();
        JsonArray data = root.get("data").getAsJsonArray();
        // use the person deserializer, since it is an array of persons
        for (int i = 0; i < data.size(); ++i) {
            JsonElement jsonEntity = data.get(i);
            T entity = _entityDeserializer.deserialize(jsonEntity, typeOfT, context);
            entities.add(entity);
        }
        return entities;
    }
}
