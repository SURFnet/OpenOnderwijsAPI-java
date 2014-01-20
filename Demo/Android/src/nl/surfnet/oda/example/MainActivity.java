package nl.surfnet.oda.example;

import java.util.List;

import nl.surfnet.oda.AbstractAPIClient.Params;
import nl.surfnet.oda.EntityHandler;
import nl.surfnet.oda.ListHandler;
import nl.surfnet.oda.NetworkError;
import nl.surfnet.oda.OnderwijsDataAPI;
import nl.surfnet.oda.affiliations.Affiliation;
import nl.surfnet.oda.buildings.Building;
import nl.surfnet.oda.grouproles.GroupRole;
import nl.surfnet.oda.groups.Group;
import nl.surfnet.oda.newfeeds.NewsFeed;
import nl.surfnet.oda.newsitems.NewsItem;
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
        final TextView AEGBBuilding = (TextView)findViewById(R.id.AEGBBuilding);
        final TextView numberOfBuildings = (TextView)findViewById(R.id.numberOfBuildings);
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
                // display the result
                numberOfBuildings.setText("There are " + list.size() + " buildings on the first page.");
            }

            @Override
            public void failure(NetworkError e) {
                // inform the user that en error happened.
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

        /**
         * GROUPS
         */
        final TextView firstGroupMembers = (TextView)findViewById(R.id.firstGroupMembers);
        final TextView numberOfGroups = (TextView)findViewById(R.id.numberOfGroups);
        // get info about the group with the id '1'
        apiClient.getGroupsClient().get("1", null, new EntityHandler<Group>() {

            @Override
            public void success(Group group) {
                // display the result
                firstGroupMembers.setText("The group with the id '1' has " + group.getMembers().size() + " member(s), and its name is '" + group.getName() + "'.");
            }

            @Override
            public void failure(NetworkError e) {
                // inform the user that an error happened
                firstGroupMembers.setText("Error getting info about the group with the id '1' :-(");
                e.printStackTrace();
            }
        });
        // you can also do inline params:
        apiClient.getGroupsClient().getList(new Params().setPage(1), new ListHandler<Group>() {

            @Override
            public void success(List<Group> list) {
                // display the result
                numberOfGroups.setText("On the first page there are " + list.size() + " groups.");
            }

            @Override
            public void failure(NetworkError e) {
                // inform the user that an error happened
                numberOfGroups.setText("Error in listing first page of groups :-(");
                e.printStackTrace();
            }
        });

        /**
         * AFFILIATIONS
         */
        final TextView secondAffiliationPersons = (TextView)findViewById(R.id.secondAffiliationPersons);
        final TextView numberOfAffiliations = (TextView)findViewById(R.id.numberOfAffiliations);
        // get the info about the affiliation with the id '1'
        apiClient.getAffiliationsClient().get("2", null, new EntityHandler<Affiliation>() {

            @Override
            public void success(Affiliation affiliation) {
                // display the result
                secondAffiliationPersons.setText("The second affiliation has " + affiliation.getPersonUrls().size() + " persons.");
            }

            @Override
            public void failure(NetworkError e) {
                // inform the user that an error happened
                secondAffiliationPersons.setText("Error getting info about the first affiliation :-(");
                e.printStackTrace();
            }
        });
        // get the number of the affiliations on the first page
        apiClient.getAffiliationsClient().getList(new Params().setPage(1), new ListHandler<Affiliation>() {

            @Override
            public void success(List<Affiliation> list) {
                // display the result
                numberOfAffiliations.setText("There are " + list.size() + " affiliations on the first page.");
            }

            @Override
            public void failure(NetworkError e) {
                // inform the user that an error happened
                numberOfAffiliations.setText("Error in listing first page of affiliations :-(");
                e.printStackTrace();
            }
        });

        /**
         * NEWS ITEMS
         */
        final TextView firstNewsItem = (TextView)findViewById(R.id.firstNewsItem);
        final TextView numberOfNewsItems = (TextView)findViewById(R.id.numberOfNewsItems);
        // get the info about the first news item
        apiClient.getNewsItemsClient().get("1", null, new EntityHandler<NewsItem>() {

            @Override
            public void success(NewsItem newsItem) {
                // display the result
                firstNewsItem.setText("The first news item was publicated at: " + newsItem.getPublicationDate());
            }

            @Override
            public void failure(NetworkError e) {
                // inform the user about the error.
                firstNewsItem.setText("Error getting info about the first news item :-(");
                e.printStackTrace();
            }
        });
        // get the number of the news items on the first page
        apiClient.getNewsItemsClient().getList(new Params().setPage(1), new ListHandler<NewsItem>() {

            @Override
            public void success(List<NewsItem> list) {
                // display the result
                numberOfNewsItems.setText("There are " + list.size() + " news items on the first page.");
            }

            @Override
            public void failure(NetworkError e) {
                //  inform the user about the error
                numberOfNewsItems.setText("Error listing the first page of the news items :-(");
                e.printStackTrace();
            }
        });

        /**
         * NEWS FEEDS
         */
        final TextView firstNewsFeed = (TextView)findViewById(R.id.firstNewsFeed);
        final TextView numberOfNewsFeeds = (TextView)findViewById(R.id.numberOfNewsFeeds);
        // get the info about the first newsfeed
        apiClient.getNewsFeedsClient().get("1", null, new EntityHandler<NewsFeed>() {

            @Override
            public void success(NewsFeed newsFeed) {
                // display the result
                firstNewsFeed.setText("The first news feed has " + newsFeed.getItems().size() + " items.");
            }

            @Override
            public void failure(NetworkError e) {
                // inform the user about the error.
                firstNewsFeed.setText("Error getting info about the first newsfeed :-(");
                e.printStackTrace();
            }
        });
        // get the number of the newsfeeds on the first page
        apiClient.getNewsFeedsClient().getList(new Params().setPage(1), new ListHandler<NewsFeed>() {

            @Override
            public void success(List<NewsFeed> list) {
                // display the result
                numberOfNewsFeeds.setText("There are " + list.size() + " newsfeeds on the first page.");
            }

            @Override
            public void failure(NetworkError e) {
                //  inform the user about the error
                numberOfNewsFeeds.setText("Error listing the first page of the newsfeeds :-(");
                e.printStackTrace();
            }
        });

        /**
         * GROUP ROLES
         */
        final TextView firstRoleName = (TextView)findViewById(R.id.firstRoleName);
        final TextView numberOfRoles = (TextView)findViewById(R.id.numberOfRoles);
        // get the info about the first news item
        apiClient.getGroupRolesClient().get("1", null, new EntityHandler<GroupRole>() {

            @Override
            public void success(GroupRole groupRole) {
                // display the result
                firstRoleName.setText("The first role's name is: '" + groupRole.getRole() + "'.");
            }

            @Override
            public void failure(NetworkError e) {
                // inform the user about the error.
                firstRoleName.setText("Error getting info about the first group role :-(");
                e.printStackTrace();
            }
        });
        // get the number of group roles on the first page
        apiClient.getGroupRolesClient().getList(new Params().setPage(1), new ListHandler<GroupRole>() {

            @Override
            public void success(List<GroupRole> list) {
                // display the result
                numberOfRoles.setText("There are " + list.size() + " group roles on the first page.");
            }

            @Override
            public void failure(NetworkError e) {
                // inform the user about the error
                numberOfRoles.setText("Error listing the first page of the group roles :-(");
                e.printStackTrace();
            }
        });

    }
}
