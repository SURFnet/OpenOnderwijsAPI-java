package nl.surfnet.oda.persons;

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
public class PersonDeserializer implements JsonDeserializer<Person> {

    @Override
    public Person deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final Person person = new Person();
        final JsonObject data = json.getAsJsonObject();
        person.department = _getAsStringNoNull(data.get("department"));
        person.displayName = _getAsStringNoNull(data.get("displayName"));
        person.employeeID = _getAsStringNoNull(data.get("employeeID"));
        person.gender = _getAsStringNoNull(data.get("gender"));
        person.givenName = _getAsStringNoNull(data.get("givenName"));
        person.mail = _getAsStringNoNull(data.get("mail"));
        person.mobileNumber = _getAsStringNoNull(data.get("mobileNumber"));
        person.office = _getAsStringNoNull(data.get("office"));
        person.organisation = _getAsStringNoNull(data.get("organisation"));
        person.photo = _getAsStringNoNull(data.get("photo"));
        person.resourceUrl = _getAsStringNoNull(data.get("url"));
        person.studentID = _getAsStringNoNull(data.get("studentID"));
        person.surName = _getAsStringNoNull(data.get("surName"));
        person.telephoneNumber = _getAsStringNoNull(data.get("telephoneNumber"));
        person.title = _getAsStringNoNull(data.get("title"));
        return person;
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

}
