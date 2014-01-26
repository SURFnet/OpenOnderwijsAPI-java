package nl.surfnet.oda.rooms;

import java.util.Date;


/**
 * Represents a room.
 *
 * @author Daniel Zolnai
 *
 */

public class Room {

    private String _abbr;
    private String _building;
    private String _name;
    private String _description;
    private Integer _totalSeats;
    private Integer _totalWorkspaces;
    private Integer _availableWorkspaces;
    private Date _lastModified;

    public String getId() {
        return _abbr;
    }

    public void setId(String id) {
        _abbr = id;
    }

    public String getBuildingUrl() {
        return _building;
    }

    public void setBuildingUrl(String building) {
        _building = building;
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

    public Integer getTotalSeats() {
        return _totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        _totalSeats = totalSeats;
    }

    public Integer getTotalWorkspaces() {
        return _totalWorkspaces;
    }

    public void setTotalWorkspaces(Integer totalWorkspaces) {
        _totalWorkspaces = totalWorkspaces;
    }

    public Integer getAvailableWorkspaces() {
        return _availableWorkspaces;
    }

    public void setAvailableWorkspaces(Integer availableWorkspaces) {
        _availableWorkspaces = availableWorkspaces;
    }

    public Date getLastModifiedDate() {
        return _lastModified;
    }

    public void setLastModifiedDate(Date lastModified) {
        _lastModified = lastModified;
    }
}
