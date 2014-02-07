package nl.surfnet.oda.schedule;

import java.lang.reflect.Type;

import nl.surfnet.oda.EntityDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Converts a JSON to a Lesson object
 *
 * @author Daniel Zolnai
 *
 */
public class LessonDeserializer extends EntityDeserializer<Lesson> {

    @Override
    public Lesson deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json == null || json.isJsonNull()) {
            return null;
        }
        JsonObject jsonLesson = json.getAsJsonObject();
        Lesson lesson = new Lesson();
        lesson.setId(getAsStringNoNull(jsonLesson.get("id")));
        lesson.setResourceUrl(getAsStringNoNull(jsonLesson.get("url")));
        JsonObject jsonCourse = jsonLesson.get("course").getAsJsonObject();
        lesson.setCourseUrl(getAsStringNoNull(jsonCourse.get("url")));
        lesson.setCourseId(getAsStringNoNull(jsonCourse.get("id")));
        lesson.setCourseAbbreviation(getAsStringNoNull(jsonCourse.get("abbr")));
        lesson.setCourseDescription(getAsStringNoNull(jsonCourse.get("description")));
        lesson.setCourseName(getAsStringNoNull(jsonCourse.get("name")));
        lesson.setDescription(getAsStringNoNull(jsonLesson.get("description")));
        JsonObject jsonRoom = jsonLesson.get("room").getAsJsonObject();
        lesson.setRoomAbbreviation(getAsStringNoNull(jsonRoom.get("abbr")));
        lesson.setRoomId(getAsStringNoNull(jsonRoom.get("id")));
        lesson.setRoomUrl(getAsStringNoNull(jsonRoom.get("url")));
        lesson.setLastModifiedDate(getAsDateNoNull(jsonLesson.get("lastModified")));
        lesson.setStartDate(getAsDateNoNull(jsonLesson.get("start")));
        lesson.setEndDate(getAsDateNoNull(jsonLesson.get("end")));
        return lesson;
    }

}
