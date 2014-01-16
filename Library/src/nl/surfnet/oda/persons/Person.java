package nl.surfnet.oda.persons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A person. The unknown fields of the API are ignored, so slight changes in the API don't break the application.
 *
 * @author Daniel Zolnai
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
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

    @JsonProperty("url")
    public String resourceUrl;

    // More attributes will be added later.

    /**
     * Empty constructor. Needed for JSON - POJO conversion
     */
    public Person() {
    }
}
