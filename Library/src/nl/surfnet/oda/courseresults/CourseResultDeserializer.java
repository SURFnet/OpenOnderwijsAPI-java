package nl.surfnet.oda.courseresults;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import nl.surfnet.oda.EntityDeserializer;
import nl.surfnet.oda.courses.Course;
import nl.surfnet.oda.courses.CourseDeserializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Deserializes a JSON to a course result
 *
 * @author Daniel Zolnai
 *
 */
public class CourseResultDeserializer extends EntityDeserializer<CourseResult> {

    @Override
    public CourseResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json == null || json.isJsonNull()) {
            return null;
        }
        JsonObject jsonCourseResult = json.getAsJsonObject();
        CourseResult courseResult = new CourseResult();
        courseResult.setId(getAsStringNoNull(jsonCourseResult.get("id")));
        CourseDeserializer courseDeserializer = new CourseDeserializer();
        Course course = courseDeserializer.deserialize(jsonCourseResult.get("course"), typeOfT, context);
        courseResult.setCourse(course);
        courseResult.setGrade(getAsDoubleNoNull(jsonCourseResult.get("grade")));
        courseResult.setIfStudentPassed(getAsBooleanNoNull(jsonCourseResult.get("passed")));
        courseResult.setLastModifiedDate(getAsDateNoNull(jsonCourseResult.get("lastModified")));
        courseResult.setResult(getAsStringNoNull(jsonCourseResult.get("result")));
        courseResult.setStudentUrl(getAsStringNoNull(jsonCourseResult.get("student")));
        JsonArray jsonTestResultUrls = jsonCourseResult.get("testResults").getAsJsonArray();
        List<String> testResultUrls = new ArrayList<String>();
        for (int i = 0; i < jsonTestResultUrls.size(); ++i) {
            String testResultUrl = jsonTestResultUrls.get(i).getAsString();
            testResultUrls.add(testResultUrl);
        }
        courseResult.setTestResultUrls(testResultUrls);
        return courseResult;
    }

}
