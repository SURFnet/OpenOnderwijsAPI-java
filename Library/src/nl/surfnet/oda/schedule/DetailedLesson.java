package nl.surfnet.oda.schedule;

import java.util.Date;

import nl.surfnet.oda.courses.Course;
import nl.surfnet.oda.rooms.Room;

/**
 * Represents a lesson.
 *
 * @author Daniel Zolnai
 *
 */
public class DetailedLesson {

    private String _id;
    private String _url;
    private Course _course;
    private Room _room;
    private String _description;
    private Date _start;
    private Date _end;
    private Date _lastModified;

    /**
     * Empty constructor needed for conversion
     */
    public DetailedLesson() {
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

    public Course getCourse() {
        return _course;
    }

    public void setCourse(Course course) {
        _course = course;
    }

    public Room getRoom() {
        return _room;
    }

    public void setRoom(Room room) {
        _room = room;
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
