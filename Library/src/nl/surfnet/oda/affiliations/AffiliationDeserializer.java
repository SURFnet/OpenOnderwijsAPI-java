package nl.surfnet.oda.affiliations;

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
 * Deserializes a JSON to an Affiliation object.
 * 
 * @author Daniel Zolnai
 * 
 */
public class AffiliationDeserializer extends EntityDeserializer<Affiliation> {

    @Override
    public Affiliation deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json == null || json.isJsonNull()) {
            return null;
        }
        JsonObject affiliationJson = json.getAsJsonObject();
        Affiliation affiliation = new Affiliation();
        affiliation.setAffiliation(getAsStringNoNull(affiliationJson.get("affiliation")));
        affiliation.setResourceUrl(getAsStringNoNull(affiliationJson.get("url")));
        // get the urls of the persons
        JsonArray personsJson = affiliationJson.get("persons").getAsJsonArray();
        List<String> persons = new ArrayList<String>();
        for (int i = 0; i < personsJson.size(); ++i) {
            String person = personsJson.get(i).getAsString();
            persons.add(person);
        }
        affiliation.setPersonsUrls(persons);
        return affiliation;
    }

}
