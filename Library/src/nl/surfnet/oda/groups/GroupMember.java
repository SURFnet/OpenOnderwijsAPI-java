package nl.surfnet.oda.groups;

/**
 * A member of a group.
 *
 * @author Daniel Zolnai
 *
 */
public class GroupMember {

    String _personUrl;
    String _displayName;
    String _groupType;
    String _resourceUrl;
    String _groupUrl;
    String _role;

    /**
     * Empty constructor for conversion.
     */
    public GroupMember() {
    }

    public String getPersonUrl() {
        return _personUrl;
    }

    public void setPersonUrl(String personUrl) {
        _personUrl = personUrl;
    }

    public String getDisplayName() {
        return _displayName;
    }

    public void setDisplayName(String displayName) {
        _displayName = displayName;
    }

    public String getGroupType() {
        return _groupType;
    }

    public void setGroupType(String groupType) {
        _groupType = groupType;
    }

    public String getResourceUrl() {
        return _resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        _resourceUrl = resourceUrl;
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
