package nl.surfnet.oda;

import nl.surfnet.oda.buildings.BuildingsClient;
import nl.surfnet.oda.groups.GroupsClient;
import nl.surfnet.oda.persons.PersonsClient;
import nl.surfnet.oda.rooms.RoomsClient;

/**
 * Base class for communication with the OnderwijsDataAPI. Use the getter methods for getting the different clients.
 *
 * @author Daniel Zolnai
 *
 */
public class OnderwijsDataAPI {

    private String _baseUrl;

    private PersonsClient _personsClient;
    private BuildingsClient _buildingsClient;
    private RoomsClient _roomsClient;
    private GroupsClient _groupsClient;

    /**
     * Basic constructor
     *
     * @param baseUrl The URL of your API. For example, https://api.example.com
     */
    public OnderwijsDataAPI(String baseUrl) {
        _baseUrl = baseUrl;
    }

    /**
     * Returns the client which is responsible for the handling of persons data
     *
     * @return The client handling persons data
     */
    public PersonsClient getPersonsClient() {
        if (_personsClient == null) {
            _personsClient = new PersonsClient(_baseUrl);
        }
        return _personsClient;
    }

    /**
     * Return the client which is responsible for the handling of buildings data.
     *
     * @return The client handling buildings data
     */
    public BuildingsClient getBuildingsClient() {
        if (_buildingsClient == null){
            _buildingsClient = new BuildingsClient(_baseUrl);
        }
        return _buildingsClient;
    }

    /**
     * Returns the client which is responsible for the handling of rooms data.
     *
     * @return The client handling rooms data
     */
    public RoomsClient getRoomsClient() {
        if (_roomsClient == null) {
            _roomsClient = new RoomsClient(_baseUrl);
        }
        return _roomsClient;
    }

    /**
     * Returns the client which is responsible for the handling of groups data.
     *
     * @return The client handling groups data.
     */
    public GroupsClient getGroupsClient() {
        if (_groupsClient == null) {
            _groupsClient = new GroupsClient(_baseUrl);
        }
        return _groupsClient;
    }

}
