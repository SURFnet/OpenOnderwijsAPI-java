package nl.surfnet.oda.testresults;

import java.lang.reflect.Type;

import nl.surfnet.oda.EntityDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Converts a JSON to a TestResult object.
 *
 * @author Daniel Zolnai
 *
 */
public class TestResultDeserializer extends EntityDeserializer<TestResult> {

    @Override
    public TestResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json == null || json.isJsonNull()) {
            return null;
        }
        JsonObject jsonTestResult = json.getAsJsonObject();
        TestResult testResult = new TestResult();
        testResult.setId(getAsStringNoNull(jsonTestResult.get("id")));
        testResult.setCourseResultUrl(getAsStringNoNull(jsonTestResult.get("courseResult")));
        testResult.setCourseUrl(getAsStringNoNull(jsonTestResult.get("course")));
        testResult.setDate(getAsShortDateNoNull(jsonTestResult.get("date")));
        testResult.setDescription(getAsStringNoNull(jsonTestResult.get("description")));
        testResult.setGrade(getAsDoubleNoNull(jsonTestResult.get("grade")));
        testResult.setLastModifiedDate(getAsDateNoNull(jsonTestResult.get("lastModified")));
        testResult.setPassed(getAsBooleanNoNull(jsonTestResult.get("passed")));
        testResult.setResourceUrl(getAsStringNoNull(jsonTestResult.get("url")));
        testResult.setResult(getAsStringNoNull(jsonTestResult.get("result")));
        testResult.setStudentUrl(getAsStringNoNull(jsonTestResult.get("student")));
        testResult.setWeight(getAsDoubleNoNull(jsonTestResult.get("weight")));
        return testResult;
    }

}
