package nl.surfnet.oda.grouproles;

/**
 * Represents a group role.
 *
 * @author Daniel Zolnai
 *
 */
public class GroupRole {

    private String _id;
    private String _url;
    private String _personUrl;
    private String _groupUrl;
    private String _role;

    /**
     * Empty constructor needed for conversion.
     */
    public GroupRole() {
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

    public void setResourceUrl(String url) {
        _url = url;
    }

    public String getPersonUrl() {
        return _personUrl;
    }

    public void setPersonUrl(String personUrl) {
        _personUrl = personUrl;
    }

    public String getGroupUrl() {
        return _groupUrl;
    }

    public void setGroupUrl(String groupUrl) {
        _groupUrl = groupUrl;
    }

    public String getRole() {
        return _role;
    }

    public void setRole(String role) {
        _role = role;
    }
}
