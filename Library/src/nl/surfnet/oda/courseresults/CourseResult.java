package nl.surfnet.oda.courseresults;

import java.util.Date;
import java.util.List;

import nl.surfnet.oda.courses.Course;

/**
 * Represents a course result.
 *
 * @author Daniel Zolnai
 *
 */
public class CourseResult {

    private String _id;
    private String _studentUrl;
    private Course _course;
    private List<String> _testResultUrls;
    private String _url;
    private Date _lastModified;
    private Double _grade;
    private String _result;
    private Boolean _passed;

    /**
     * Empty constructor needed for JSON conversion
     */
    public CourseResult() {
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        _id = id;
    }

    public String getStudentUrl() {
        return _studentUrl;
    }

    public void setStudentUrl(String studentUrl) {
        _studentUrl = studentUrl;
    }

    public String getResourceUrl() {
        return _url;
    }

    public void setResourceUrl(String resourceUrl) {
        _url = resourceUrl;
    }

    public List<String> getTestResultUrls() {
        return _testResultUrls;
    }

    public void setTestResultUrls(List<String> testResultUrls) {
        _testResultUrls = testResultUrls;
    }

    public Course getCourse() {
        return _course;
    }

    public void setCourse(Course course) {
        _course = course;
    }

    public Date getLastModifiedDate() {
        return _lastModified;
    }

    public void setLastModifiedDate(Date lastModified) {
        _lastModified = lastModified;
    }

    public Double getGrade() {
        return _grade;
    }

    public void setGrade(Double grade) {
        _grade = grade;
    }

    public Boolean getIfStudentPassed() {
        return _passed;
    }

    public void setIfStudentPassed(Boolean passed) {
        _passed = passed;
    }

    public String getResult() {
        return _result;
    }

    public void setResult(String result) {
        _result = result;
    }
}
