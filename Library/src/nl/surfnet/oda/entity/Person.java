package nl.surfnet.oda.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    public String mail;

    // More attributes will be added later.

    /**
     * Empty constructor. Needed for JSON - POJO conversion
     */
    public Person() {
    }
}
