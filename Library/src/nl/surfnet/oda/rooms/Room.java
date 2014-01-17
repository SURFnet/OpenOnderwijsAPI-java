package nl.surfnet.oda.rooms;

/**
 * Represents a room.
 *
 * @author Daniel Zolnai
 *
 */
public class Room {

    private String _building;
    private String _name;
    private String _description;
    private Integer _totalSeats;
    private Integer _totalWorkspaces;
    private Integer _availableWorkspaces;

    public String getBuilding() {
        return _building;
    }

    public void setBuilding(String building) {
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
}
