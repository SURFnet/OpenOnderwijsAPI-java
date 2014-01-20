package nl.surfnet.oda.groups;

import java.util.List;

/**
 * Represents a group.
 *
 * @author Daniel Zolnai
 *
 */
public class Group {

    private String _type;
    private String _name;
    private String _description;
    private String _resourceUrl;
    private List<GroupMember> _members;

    /**
     * Empty constructor needed for conversion.
     */
    public Group() {
    }

    public String getType() {
        return _type;
    }

    public void setType(String type) {
        _type = type;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public String getResourceUrl() {
        return _resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        _resourceUrl = resourceUrl;
    }

    public List<GroupMember> getMembers() {
        return _members;
    }

    public void setMembers(List<GroupMember> members) {
        _members = members;
    }

}
