package nl.surfnet.oda.minors;

import java.util.Date;
import java.util.List;

/**
 * Represents a minor
 *
 * @author Daniel Zolnai
 *
 */
public class Minor {

    private String _id;
    private String _url;
    private String _name;
    private String _description;
    private Date _lastModified;
    private List<String> _courseUrls;

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

    public Date getLastModified() {
        return _lastModified;
    }

    public void setLastModified(Date lastModified) {
        _lastModified = lastModified;
    }

    public List<String> getCourseUrls() {
        return _courseUrls;
    }

    public void setCourseUrls(List<String> courseUrls) {
        _courseUrls = courseUrls;
    }

}
