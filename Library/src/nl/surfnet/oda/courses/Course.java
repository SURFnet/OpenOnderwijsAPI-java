package nl.surfnet.oda.courses;

import java.util.List;

/**
 * Represents a course.
 * 
 * @author Daniel Zolnai
 * 
 */
public class Course {

    private List<String> _lessonUrls;
    private String _url;
    private String _abbr;
    private String _name;
    private Integer _ects;
    private String _description;
    private String _goals;
    private String _requirements;
    private String _level;
    private String _format;
    private String _language;
    private String _enrollment;
    private String _literature;
    private String _exams;
    private String _organisation;
    private String _department;
    private String _lecturerUrl;
    private List<String> _groupUrls;

    /**
     * Empty constructor needed for conversion.
     */
    public Course() {
    }

    public String getId() {
        return _abbr;
    }

    public void setId(String id) {
        _abbr = id;
    }

    public String getResourceUrl(){
        return _url;
    }

    public void setResourceUrl(String resourceUrl){
        _url = resourceUrl;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public List<String> getLessonUrls() {
        return _lessonUrls;
    }

    public void setLessonUrls(List<String> lessonUrls) {
        _lessonUrls = lessonUrls;
    }

    public Integer getEcts() {
        return _ects;
    }

    public void setEcts(Integer ects) {
        _ects = ects;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public String getGoals() {
        return _goals;
    }

    public void setGoals(String goals) {
        _goals = goals;
    }

    public String getRequierements() {
        return _requirements;
    }

    public void setRequirements(String requirements){
        _requirements =requirements;
    }

    public String getLevel() {
        return _level;
    }

    public void setLevel(String level) {
        _level = level;
    }

    public String getFormat() {
        return _format;
    }

    public void setFormat(String format) {
        _format = format;
    }

    public String getLanguage() {
        return _language;
    }

    public void setLanguage(String language) {
        _language = language;
    }

    public String getEnrollment() {
        return _enrollment;
    }

    public void setEnrollment(String enrollment) {
        _enrollment = enrollment;
    }

    public String getLiterature() {
        return _literature;
    }

    public void setLiterature(String literature) {
        _literature = literature;
    }

    public String getExams() {
        return _exams;
    }

    public void setExams(String exams) {
        _exams = exams;
    }

    public String getOrganisation(){
        return _organisation;
    }

    public void setOrganisation(String organisation){
        _organisation = organisation;
    }

    public String getDepartment(){
        return _department;
    }

    public void setDepartment(String department){
        _department = department;
    }

    public String getLecturerUrl() {
        return _lecturerUrl;
    }

    public void setLecturerUrl(String lecturerUrl) {
        _lecturerUrl = lecturerUrl;
    }

    public List<String> getGroupUrls() {
        return _groupUrls;
    }

    public void setGroupUrls(List<String> groupUrls) {
        _groupUrls = groupUrls;
    }
}
