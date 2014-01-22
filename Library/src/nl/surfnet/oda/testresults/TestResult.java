package nl.surfnet.oda.testresults;

import java.util.Date;

/**
 * Represents a test result.
 *
 * @author Daniel Zolnai
 *
 */
public class TestResult {

    private String _id;
    private String _url;
    private String _studentUrl;
    private String _courseUrl;
    private String _courseResultUrl;
    private String _description;
    private Date _lastModified;
    private Date _date;
    private Double _grade;
    private String _result;
    private Boolean _passed;
    private Double _weight;

    /**
     * Empty constructor needed for JSON conversion.
     */
    public TestResult() {
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        _id = id;
    }

    public String getResourceUrl() {
        return _url;
    }

    public void setResourceUrl(String resourceUrl) {
        _url = resourceUrl;
    }

    public String getStudentUrl() {
        return _studentUrl;
    }

    public void setStudentUrl(String studentUrl) {
        _studentUrl = studentUrl;
    }

    public String getCourseUrl() {
        return _courseUrl;
    }

    public void setCourseUrl(String courseUrl) {
        _courseUrl = courseUrl;
    }

    public String getCourseResultUrl() {
        return _courseResultUrl;
    }

    public void setCourseResultUrl(String courseResultUrl) {
        _courseResultUrl = courseResultUrl;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public Date getLastModifiedDate() {
        return _lastModified;
    }

    public void setLastModifiedDate(Date lastModified) {
        _lastModified = lastModified;
    }

    public Date getDate() {
        return _date;
    }

    public void setDate(Date date) {
        _date = date;
    }

    public Double getGrade() {
        return _grade;
    }

    public void setGrade(Double grade) {
        _grade = grade;
    }

    public String getResult() {
        return _result;
    }

    public void setResult(String result) {
        _result = result;
    }

    public Boolean getPassed() {
        return _passed;
    }

    public void setPassed(Boolean passed) {
        _passed = passed;
    }

    public Double getWeight() {
        return _weight;
    }

    public void setWeight(Double weight) {
        _weight = weight;
    }

}
