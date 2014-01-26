package nl.surfnet.oda.schedule;

import java.util.Date;

/**
 * Represents a lesson.
 *
 * @author Daniel Zolnai
 *
 */
public class Lesson {

    private String _id;
    private String _url;
    private String _courseUrl;
    private String _roomUrl;
    private String _description;
    private Date _start;
    private Date _end;
    private Date _lastModified;

    /**
     * Empty constructor needed for conversion
     */
    public Lesson() {
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

    public String getCourseUrl() {
        return _courseUrl;
    }

    public void setCourseUrl(String courseUrl) {
        _courseUrl = courseUrl;
    }

    public String getRoomUrl() {
        return _roomUrl;
    }

    public void setRoomUrl(String roomUrl) {
        _roomUrl = roomUrl;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public Date getStartDate() {
        return _start;
    }

    public void setStartDate(Date startDate) {
        _start = startDate;
    }

    public Date getEndDate() {
        return _end;
    }

    public void setEndDate(Date endDate) {
        _end = endDate;
    }

    public Date getLastModifiedDate() {
        return _lastModified;
    }

    public void setLastModifiedDate(Date lastModified) {
        _lastModified = lastModified;
    }
}
