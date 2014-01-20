package nl.surfnet.oda.groups;

import java.lang.reflect.Type;

import nl.surfnet.oda.EntityDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Deserializes a group member.
 * 
 * @author Daniel Zolnai
 * 
 */
public class GroupMemberDeserializer extends EntityDeserializer<GroupMember> {

    @Override
    public GroupMember deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        // check if the json is null to avoid NPExceptions
        if (json == null || json.isJsonNull()) {
            return null;
        }
        JsonObject memberJson = json.getAsJsonObject();
        GroupMember member = new GroupMember();
        member.setDisplayName(getAsStringNoNull(memberJson.get("displayName")));
        member.setGroupType(getAsStringNoNull(memberJson.get("groupType")));
        member.setGroupUrl(getAsStringNoNull(memberJson.get("group")));
        member.setPersonUrl(getAsStringNoNull(memberJson.get("person")));
        member.setResourceUrl(getAsStringNoNull(memberJson.get("url")));
        member.setRole(getAsStringNoNull(memberJson.get("role")));
        return member;
    }

}
