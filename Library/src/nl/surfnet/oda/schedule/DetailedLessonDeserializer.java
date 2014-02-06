package nl.surfnet.oda.schedule;

import java.lang.reflect.Type;

import nl.surfnet.oda.EntityDeserializer;
import nl.surfnet.oda.courses.Course;
import nl.surfnet.oda.courses.CourseDeserializer;
import nl.surfnet.oda.rooms.Room;
import nl.surfnet.oda.rooms.RoomDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Converts a JSON to a DetailedLesson object (instead of URLs, it has full data of the room and the course).
 * 
 * @author Daniel Zolnai
 * 
 */
public class DetailedLessonDeserializer extends EntityDeserializer<DetailedLesson> {

    @Override
    public DetailedLesson deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json == null || json.isJsonNull()) {
            return null;
        }
        JsonObject jsonLesson = json.getAsJsonObject();
        DetailedLesson lesson = new DetailedLesson();
        lesson.setId(getAsStringNoNull(jsonLesson.get("id")));
        CourseDeserializer courseDeserializer = new CourseDeserializer();
        Course course = courseDeserializer.deserialize(jsonLesson.get("course"), typeOfT, context);
        lesson.setCourse(course);
        lesson.setDescription(getAsStringNoNull(jsonLesson.get("description")));
        RoomDeserializer roomDeserializer = new RoomDeserializer();
        Room room = roomDeserializer.deserialize(jsonLesson.get("room"), typeOfT, context);
        lesson.setRoom(room);
        lesson.setLastModifiedDate(getAsDateNoNull(jsonLesson.get("lastModified")));
        lesson.setStartDate(getAsDateNoNull(jsonLesson.get("start")));
        lesson.setEndDate(getAsDateNoNull(jsonLesson.get("end")));
        return lesson;
    }

}
