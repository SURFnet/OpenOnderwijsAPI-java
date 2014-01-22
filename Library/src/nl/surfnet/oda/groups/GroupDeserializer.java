package nl.surfnet.oda.groups;

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
 * Deserializes a JSON to a Group object
 *
 * @author Daniel Zolnai
 *
 */
public class GroupDeserializer extends EntityDeserializer<Group> {

    @Override
    public Group deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        // check for nulls to avoid NPExceptions
        if (json == null || json.isJsonNull()) {
            return null;
        }
        JsonObject groupJson = json.getAsJsonObject();
        Group group = new Group();
        group.setId(getAsStringNoNull(groupJson.get("id")));
        group.setDescription(getAsStringNoNull(groupJson.get("description")));
        group.setName(getAsStringNoNull(groupJson.get("name")));
        group.setResourceUrl(getAsStringNoNull(groupJson.get("url")));
        group.setType(getAsStringNoNull(groupJson.get("type")));
        // get the members of the group, if there are any
        if (groupJson.has("members")) {
            JsonArray membersJson = groupJson.get("members").getAsJsonArray();
            List<GroupMember> members = new ArrayList<GroupMember>();
            GroupMemberDeserializer memberDeserializer = new GroupMemberDeserializer();
            for (int i = 0; i < membersJson.size(); ++i) {
                GroupMember member = memberDeserializer.deserialize(membersJson.get(i), typeOfT, context);
                members.add(member);
            }
            group.setMembers(members);
        } else {
            group.setMembers(null);
        }
        return group;
    }

}
