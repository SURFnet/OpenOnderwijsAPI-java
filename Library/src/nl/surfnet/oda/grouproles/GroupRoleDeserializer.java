package nl.surfnet.oda.grouproles;

import java.lang.reflect.Type;

import nl.surfnet.oda.EntityDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Deserializes a JSON to a GroupRole object
 *
 * @author Daniel Zolnai
 *
 */
public class GroupRoleDeserializer extends EntityDeserializer<GroupRole> {

    @Override
    public GroupRole deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json == null || json.isJsonNull()) {
            return null;
        }
        JsonObject jsonGroupRole = json.getAsJsonObject();
        GroupRole groupRole = new GroupRole();
        groupRole.setId(getAsStringNoNull(jsonGroupRole.get("id")));
        groupRole.setGroupUrl(getAsStringNoNull(jsonGroupRole.get("group")));
        groupRole.setPersonUrl(getAsStringNoNull(jsonGroupRole.get("person")));
        groupRole.setResourceUrl(getAsStringNoNull(jsonGroupRole.get("url")));
        groupRole.setRole(getAsStringNoNull(jsonGroupRole.get("role")));
        return groupRole;
    }

}
