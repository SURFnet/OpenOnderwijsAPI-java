package nl.surfnet.oda;

import nl.surfnet.oda.persons.PersonsClient;



/**
 * Base class for communication with the OnderwijsDataAPI. Use the getter methods for getting the different clients.
 * 
 * @author Daniel Zolnai
 * 
 */
public class OnderwijsDataAPI {

    private String _baseUrl;

    private PersonsClient _personsClient;
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

}
