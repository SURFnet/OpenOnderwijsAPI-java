package nl.surfnet.oda.example;

import java.util.List;

import nl.surfnet.oda.AbstractAPIClient.Params;
import nl.surfnet.oda.EntityHandler;
import nl.surfnet.oda.ListHandler;
import nl.surfnet.oda.NetworkError;
import nl.surfnet.oda.OnderwijsDataAPI;
import nl.surfnet.oda.buildings.Building;
import nl.surfnet.oda.persons.Person;
import nl.surfnet.oda.rooms.Room;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * An example of how to use the Surfnet APIWrapper and the APIClient together.
 *
 * @author Daniel Zolnai
 *
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView numberOfPersons = (TextView)findViewById(R.id.numberOfPersons);
        final TextView firstPerson = (TextView)findViewById(R.id.firstPerson);
        // Create a new client for our API
        OnderwijsDataAPI apiClient = new OnderwijsDataAPI("http://surfnetapi.pagekite.me/");

        /**
         * PERSONS
         */

        // Retrieve the persons info using the PersonsClient
        // we do not add any additional parameters here, so we use null at the params.
        apiClient.getPersonsClient().get("1", null, new EntityHandler<Person>() {

            @Override
            public void success(Person person) {
                // display the name of the first person
                firstPerson.setText("The first person is called: " + person.getDisplayName());
            }

            @Override
            public void failure(NetworkError e) {
                // if an error happened, inform the user
                e.printStackTrace();
                firstPerson.setText("Error in getting first person :-(");
            }
        });

        // create a params object, and set the page to 1.
        Params listParams = new Params();
        listParams.setPage(1);
        // get the list of the persons on the first page
        apiClient.getPersonsClient().getList(listParams, new ListHandler<Person>() {

            @Override
            public void success(List<Person> list) {
                // display the number of persons in the UI
                numberOfPersons.setText("There are " + list.size() + " persons on the first page.");
            }

            @Override
            public void failure(NetworkError e) {
                // if an error happened, inform the user
                e.printStackTrace();
                numberOfPersons.setText("Error in listing persons of first page :-(");
            }
        });

        /**
         * BUILDINGS
         */
        final TextView AEGBBuilding = (TextView) findViewById(R.id.AEGBBuilding);
        final TextView numberOfBuildings = (TextView) findViewById(R.id.numberOfBuildings);
        // get the building with the ID 'AEGB'
        apiClient.getBuildingsClient().get("AEGB", null, new EntityHandler<Building>() {

            @Override
            public void success(Building building) {
                // display its address
                AEGBBuilding.setText("The building with the ID 'AEGB' is located at " + building.getAddress());
            }

            @Override
            public void failure(NetworkError e) {
                // inform the user if an error happened
                e.printStackTrace();
                AEGBBuilding.setText("Error in getting building with ID: 'AEGB' :-(");
            }
        });
        Params buildingsParams = new Params();
        buildingsParams.setPage(1);
        apiClient.getBuildingsClient().getList(buildingsParams, new ListHandler<Building>() {

            @Override
            public void success(List<Building> list) {
                //display the result
                numberOfBuildings.setText("There are " + list.size() + " buildings on the first page.");
            }

            @Override
            public void failure(NetworkError e) {
                //inform the user that en error happened.
                numberOfBuildings.setText("Error in listing first page of buildings :-(");
                e.printStackTrace();
            }
        });

        /**
         * ROOMS
         */

        final TextView firstRoom = (TextView)findViewById(R.id.firstRoom);
        final TextView numberOfRooms = (TextView)findViewById(R.id.numberOfRooms);
        // get the data of the room with the ID '1'
        apiClient.getRoomsClient().get("1", null, new EntityHandler<Room>() {

            @Override
            public void success(Room room) {
                // display the result
                firstRoom.setText("The first room has " + room.getTotalSeats() + " seats.");
            }

            @Override
            public void failure(NetworkError e) {
                // inform the user if an error happened
                firstRoom.setText("Error getting info about the first room :-(");
                e.printStackTrace();
            }
        });
        // we want to know how many rooms there are on the first page.
        Params roomParams = new Params();
        roomParams.setPage(1);
        apiClient.getRoomsClient().getList(roomParams, new ListHandler<Room>() {

            @Override
            public void success(List<Room> list) {
                // display the result
                numberOfRooms.setText("There are " + list.size() + " rooms on the first page.");
            }

            @Override
            public void failure(NetworkError e) {
                // inform the user if an error happened.
                numberOfRooms.setText("Error in listing first page of room :-(");
                e.printStackTrace();
            }
        });
    }
}
