package nl.surfnet.oda.courses;

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
 * Deserializes a JSON to a Course object
 *
 * @author Daniel Zolnai
 *
 */
public class CourseDeserializer extends EntityDeserializer<Course> {

    @Override
    public Course deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json == null || json.isJsonNull()) {
            return null;
        }
        JsonObject jsonCourse = json.getAsJsonObject();
        Course course = new Course();
        course.setId(getAsStringNoNull(jsonCourse.get("abbr")));
        course.setResourceUrl(getAsStringNoNull(jsonCourse.get("url")));
        course.setDepartment(getAsStringNoNull(jsonCourse.get("department")));
        course.setDescription(getAsStringNoNull(jsonCourse.get("description")));
        course.setEcts(getAsIntegerNoNull(jsonCourse.get("ects")));
        course.setEnrollment(getAsStringNoNull(jsonCourse.get("enrollment")));
        course.setExams(getAsStringNoNull(jsonCourse.get("exams")));
        course.setFormat(getAsStringNoNull(jsonCourse.get("format")));
        course.setGoals(getAsStringNoNull(jsonCourse.get("goals")));
        course.setLanguage(getAsStringNoNull(jsonCourse.get("language")));
        course.setLecturerUrl(getAsStringNoNull(jsonCourse.get("lecturerUrl")));
        course.setLevel(getAsStringNoNull(jsonCourse.get("level")));
        course.setLiterature(getAsStringNoNull(jsonCourse.get("literature")));
        course.setName(getAsStringNoNull(jsonCourse.get("name")));
        course.setOrganisation(getAsStringNoNull(jsonCourse.get("organisation")));
        course.setRequirements(getAsStringNoNull(jsonCourse.get("requirements")));
        JsonArray jsonGroupUrls = jsonCourse.get("groups").getAsJsonArray();
        List<String> groupUrls = new ArrayList<String>();
        for (int i = 0; i < jsonGroupUrls.size(); ++i) {
            String groupUrl = jsonGroupUrls.get(i).getAsString();
            groupUrls.add(groupUrl);
        }
        course.setGroupUrls(groupUrls);
        JsonArray jsonLessonUrls = jsonCourse.get("lessons").getAsJsonArray();
        List<String> lessonUrls = new ArrayList<String>();
        for (int i = 0; i < jsonLessonUrls.size(); ++i) {
            String lessonUrl = jsonLessonUrls.get(i).getAsString();
            lessonUrls.add(lessonUrl);
        }
        course.setLessonUrls(lessonUrls);
        return course;
    }

}
