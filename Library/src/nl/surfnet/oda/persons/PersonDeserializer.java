package nl.surfnet.oda.persons;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import nl.surfnet.oda.EntityDeserializer;
import nl.surfnet.oda.groups.Group;
import nl.surfnet.oda.groups.GroupDeserializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Deserializes a GSON to a Person object
 *
 * @author Daniel Zolnai
 *
 */
public class PersonDeserializer extends EntityDeserializer<Person> {

    @Override
    public Person deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final Person person = new Person();
        final JsonObject data = json.getAsJsonObject();
        Integer id = getAsIntegerNoNull(data.get("id"));
        if (id == null) {
            person.setId(null);
        } else {
            person.setId(id.toString());
        }
        person.setDepartment(getAsStringNoNull(data.get("department")));
        person.setDisplayName(getAsStringNoNull(data.get("displayName")));
        person.setEmployeeID(getAsStringNoNull(data.get("employeeID")));
        person.setGender(getAsStringNoNull(data.get("gender")));
        person.setGivenName(getAsStringNoNull(data.get("givenName")));
        person.setMail(getAsStringNoNull(data.get("mail")));
        person.setMobileNumber(getAsStringNoNull(data.get("mobileNumber")));
        person.setOffice(getAsStringNoNull(data.get("office")));
        person.setOrganisation(getAsStringNoNull(data.get("organisation")));
        person.setPhoto(getAsStringNoNull(data.get("photo")));
        person.setResourceUrl(getAsStringNoNull(data.get("url")));
        person.setStudentID(getAsStringNoNull(data.get("studentID")));
        person.setSurName(getAsStringNoNull(data.get("surName")));
        person.setTelephoneNumber(getAsStringNoNull(data.get("telephoneNumber")));
        person.setTitle(getAsStringNoNull(data.get("title")));
        // get the affiliations
        JsonArray jsonAffiliations = data.get("affiliations").getAsJsonArray();
        List<String> affiliations = new ArrayList<String>();
        for (int i = 0; i < jsonAffiliations.size(); ++i) {
            affiliations.add(jsonAffiliations.get(i).getAsString());
        }
        person.setAffiliations(affiliations);
        // get the groups
        JsonArray jsonGroups = data.get("groups").getAsJsonArray();
        List<Group> groups = new ArrayList<Group>();
        GroupDeserializer groupDeserializer = new GroupDeserializer();
        for (int i = 0; i < jsonGroups.size(); ++i) {
            JsonElement jsonGroup = jsonGroups.get(i);
            Group group = groupDeserializer.deserialize(jsonGroup, typeOfT, context);
            groups.add(group);
        }
        person.setGroups(groups);
        return person;
    }
}
