package nl.surfnet.oda.persons;

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
public class PersonsListDeserializer implements JsonDeserializer<List<Person>> {

    /**
     * Deserializes a GSON to a plain java object.
     */
    @Override
    public List<Person> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<Person> persons = new ArrayList<Person>();
        JsonObject root = json.getAsJsonObject();
        JsonArray data = root.get("data").getAsJsonArray();
        // use the person deserializer, since it is an array of persons
        PersonDeserializer deserializer = new PersonDeserializer();
        for (int i = 0; i < data.size(); ++i) {
            JsonElement jsonPerson = data.get(i);
            Person person = deserializer.deserialize(jsonPerson, typeOfT, context);
            persons.add(person);
        }
        return persons;
    }
}
