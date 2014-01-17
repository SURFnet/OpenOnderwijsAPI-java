package nl.surfnet.oda.persons;

/**
 * Represents a person.
 * 
 * @author Daniel Zolnai
 * 
 */
public class Person {

    public String givenName;
    public String surName;
    public String displayName;
    public String mail;
    public String telephoneNumber;
    public String mobileNumber;
    public String photo;
    public String gender;
    public String organisation;
    public String department;
    public String title;
    public String office;
    public String employeeID;
    public String studentID;
    public String resourceUrl;

    /**
     * Empty constructor. Needed for JSON - POJO conversion
     */
    public Person() {
    }
}