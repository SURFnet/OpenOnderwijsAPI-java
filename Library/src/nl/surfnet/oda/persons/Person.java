package nl.surfnet.oda.persons;

import java.util.List;

import nl.surfnet.oda.groups.Group;

/**
 * Represents a person.
 *
 * @author Daniel Zolnai
 *
 */
public class Person {

    private String _id;
    private String _givenName;
    private String _surName;
    private String _displayName;
    private String _mail;
    private String _telephoneNumber;
    private String _mobileNumber;
    private String _photo;
    private String _gender;
    private String _organisation;
    private String _department;
    private String _title;
    private String _office;
    private String _employeeID;
    private String _studentID;
    private String _resourceUrl;
    private List<String> _affiliations;
    private List<Group> _groups;

    /**
     * Empty constructor. Needed for JSON - POJO conversion
     */
    public Person() {
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        _id = id;
    }

    public String getGivenName() {
        return _givenName;
    }

    public void setGivenName(String givenName) {
        _givenName = givenName;
    }

    public String getSurName() {
        return _surName;
    }

    public void setSurName(String surName) {
        _surName = surName;
    }

    public String getDisplayName() {
        return _displayName;
    }

    public void setDisplayName(String displayName) {
        _displayName = displayName;
    }

    public String getMail() {
        return _mail;
    }

    public void setMail(String mail) {
        _mail = mail;
    }

    public String getTelephoneNumber() {
        return _telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        _telephoneNumber = telephoneNumber;
    }

    public String getMobileNumber() {
        return _mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        _mobileNumber = mobileNumber;
    }

    public String getPhoto() {
        return _photo;
    }

    public void setPhoto(String photo) {
        _photo = photo;
    }

    public String getGender() {
        return _gender;
    }

    public void setGender(String gender) {
        _gender = gender;
    }

    public String getOrganisation() {
        return _organisation;
    }

    public void setOrganisation(String organisation) {
        _organisation = organisation;
    }

    public String getDepartment() {
        return _department;
    }

    public void setDepartment(String department) {
        _department = department;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getOffice() {
        return _office;
    }

    public void setOffice(String office) {
        _office = office;
    }

    public String getEmployeeID() {
        return _employeeID;
    }

    public void setEmployeeID(String employeeID) {
        _employeeID = employeeID;
    }

    public String getStudentID() {
        return _studentID;
    }

    public void setStudentID(String studentID) {
        _studentID = studentID;
    }

    public String getResourceUrl() {
        return _resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        _resourceUrl = resourceUrl;
    }

    public List<String> getAffiliations() {
        return _affiliations;
    }

    public void setAffiliations(List<String> affiliations) {
        _affiliations = affiliations;
    }

    public List<Group> getGroups() {
        return _groups;
    }

    public void setGroups(List<Group> groups) {
        _groups = groups;
    }
}