package nl.surfnet.oda.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import nl.surfnet.oda.AbstractAPIClient.Params;
import nl.surfnet.oda.EntityHandler;
import nl.surfnet.oda.ListHandler;
import nl.surfnet.oda.NetworkError;
import nl.surfnet.oda.OnderwijsDataAPI;
import nl.surfnet.oda.affiliations.Affiliation;
import nl.surfnet.oda.buildings.Building;
import nl.surfnet.oda.courseresults.CourseResult;
import nl.surfnet.oda.courses.Course;
import nl.surfnet.oda.grouproles.GroupRole;
import nl.surfnet.oda.groups.Group;
import nl.surfnet.oda.minors.Minor;
import nl.surfnet.oda.newfeeds.NewsFeed;
import nl.surfnet.oda.newsitems.NewsItem;
import nl.surfnet.oda.oauth.OAuthHandler.LoginHandler;
import nl.surfnet.oda.persons.Person;
import nl.surfnet.oda.rooms.Room;
import nl.surfnet.oda.schedule.Lesson;
import nl.surfnet.oda.testresults.TestResult;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * An example of how to use the Surfnet APIWrapper and the APIClient together.
 *
 * @author Daniel Zolnai
 *
 */
public class MainActivity extends Activity {

    private static final String BASE_URL = "http://surfnetapi.pagekite.me/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a new client for our API
        final OnderwijsDataAPI apiClient = new OnderwijsDataAPI(BASE_URL);
        _setupLoginButton(apiClient);
        _testAllApis(apiClient);
    }

    private void _testAllApis(final OnderwijsDataAPI apiClient) {
        /**
         * PERSONS
         */
        // Retrieve the persons info using the PersonsClient
        // we do not add any additional parameters here, so we use null at the params.
        final TextView numberOfPersons = (TextView)findViewById(R.id.numberOfPersons);
        final TextView firstPerson = (TextView)findViewById(R.id.firstPerson);
        apiClient.getPersonsClient().getById("1", null, new EntityHandler<Person>() {

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

        // create a params object, and set the page to 1, also search to persons with the name 'Bibber'
        Params listParams = new Params();
        listParams.setPage(1);
        listParams.setSearchFilter("bibber");
        // get the list of the persons on the first page
        apiClient.getPersonsClient().getList(listParams, new ListHandler<Person>() {

            @Override
            public void success(List<Person> list) {
                // display the number of persons in the UI
                numberOfPersons.setText("There are " + list.size() + " persons on the first page named 'bibber'.");
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
        apiClient.getBuildingsClient().getById("AEGB", null, new EntityHandler<Building>() {

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
        apiClient.getRoomsClient().getById("1", null, new EntityHandler<Room>() {

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
        final TextView numberOfGroups = (TextView)findViewById(R.id.numberOfGroups); // get info about the group with the id '1'
        apiClient.getGroupsClient().getById("1", null, new EntityHandler<Group>() {

            @Override
            public void success(Group group) { // display the result
                firstGroupMembers.setText("The group with the id '1' has " + group.getMembers().size() + " member(s), and its name is '" + group.getName() + "'.");
            }

            @Override
            public void failure(NetworkError e) { // inform the user that an error happened
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
        apiClient.getAffiliationsClient().getById("2", null, new EntityHandler<Affiliation>() {

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
        apiClient.getNewsItemsClient().getById("1", null, new EntityHandler<NewsItem>() {

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
                // inform the user about the error
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
        apiClient.getNewsFeedsClient().getById("1", null, new EntityHandler<NewsFeed>() {

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
                // inform the user about the error
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
        apiClient.getGroupRolesClient().getById("1", null, new EntityHandler<GroupRole>() {

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
                /**
                 * GET PERSON BY URL
                 */
                _getPersonByURL(apiClient, list);
            }

            @Override
            public void failure(NetworkError e) {
                // inform the user about the error
                numberOfRoles.setText("Error listing the first page of the group roles :-(");
                e.printStackTrace();
            }
        });

        /**
         * COURSES
         */
        final TextView firstCourse = (TextView)findViewById(R.id.firstCourse);
        final TextView numberOfCourses = (TextView)findViewById(R.id.numberOfCourses);
        apiClient.getCoursesClient().getById("1", null, new EntityHandler<Course>() {

            @Override
            public void success(Course course) {
                // display the result
                firstCourse.setText("The first course has " + course.getLessonUrls().size() + " lessons.");
            }

            @Override
            public void failure(NetworkError error) {
                // inform the user that an error happened
                firstCourse.setText("Error getting first course :-(");
                error.printStackTrace();
            }
        });
        apiClient.getCoursesClient().getList(new Params().setPage(1), new ListHandler<Course>() {

            @Override
            public void success(List<Course> list) {
                // display the result
                numberOfCourses.setText("There are " + list.size() + " courses on the first page.");
            }

            @Override
            public void failure(NetworkError e) {
                // inform the user that an error happened
                numberOfCourses.setText("Error listing first page of courses :-(");
                e.printStackTrace();
            }
        });

        /**
         * LESSONS
         */
        final TextView firstLesson = (TextView)findViewById(R.id.firstLesson);
        final TextView numberOfLessons = (TextView)findViewById(R.id.numberOfLessons);
        apiClient.getScheduleClient().getById("1", null, new EntityHandler<Lesson>() {

            @Override
            public void success(Lesson lesson) {
                // display the result
                firstLesson.setText("The first lesson starts at: " + lesson.getStartDate());
            }

            @Override
            public void failure(NetworkError error) {
                // inform the user that an error happened
                firstLesson.setText("Error getting first lesson :-(");
                error.printStackTrace();
            }
        });
        apiClient.getScheduleClient().getList(new Params().setPage(1), new ListHandler<Lesson>() {

            @Override
            public void success(List<Lesson> list) {
                // display the result
                numberOfLessons.setText("There are " + list.size() + " lessons on the first page.");
            }

            @Override
            public void failure(NetworkError e) {
                // inform the user that an error happened
                numberOfLessons.setText("Error listing first page of lessons :-(");
                e.printStackTrace();
            }
        });

        /**
         * SCHEDULE OF A PERSON
         */
        final TextView schedulePerson = (TextView)findViewById(R.id.schedulePerson);
        Params scheduleParams = new Params();
        try {
            scheduleParams.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse("1996-01-01"));
        } catch (ParseException e) {
            // no start date then (default is today 00:00)
        }
        scheduleParams.setEndDate(new Date());
        apiClient.getScheduleClient().getScheduleByPerson("1", scheduleParams, new ListHandler<Lesson>() {

            @Override
            public void success(List<Lesson> list) {
                // display the result
                schedulePerson.setText("The first person has " + list.size() + " lessons.");
            }

            @Override
            public void failure(NetworkError error) {
                // inform the user that an error happened
                schedulePerson.setText("Error getting the schedule of the first person.");
                error.printStackTrace();
            }
        });

        /**
         * COURSE RESULTS
         */
        final TextView firstCourseResult = (TextView)findViewById(R.id.firstCourseResult);
        final TextView numberOfCourseResults = (TextView)findViewById(R.id.numberOfCourseResults);
        apiClient.getCourseResultsClient().getById("1", null, new EntityHandler<CourseResult>() {

            @Override
            public void success(CourseResult courseResult) {
                // display the result
                firstCourseResult.setText("The first course result's grade is: " + courseResult.getGrade());
            }

            @Override
            public void failure(NetworkError error) {
                // inform the user that an error happened
                firstCourseResult.setText("Error getting first course result :-(");
                error.printStackTrace();
            }
        });
        apiClient.getCourseResultsClient().getList(new Params().setPage(1), new ListHandler<CourseResult>() {

            @Override
            public void success(List<CourseResult> list) {
                // display the result
                numberOfCourseResults.setText("There are " + list.size() + " course results on the first page.");
            }

            @Override
            public void failure(NetworkError e) {
                // inform the user that an error happened
                numberOfCourseResults.setText("Error listing first page of course results :-(");
                e.printStackTrace();
            }
        });

        /**
         * TEST RESULTS
         */
        final TextView firstTestResult = (TextView)findViewById(R.id.firstTestResult);
        final TextView numberOfTestResults = (TextView)findViewById(R.id.numberOfTestResults);
        apiClient.getTestResultsClient().getById("1", null, new EntityHandler<TestResult>() {

            @Override
            public void success(TestResult testResult) {
                // display the result
                firstTestResult.setText("The first test result's date is: " + testResult.getDate().toString());
            }

            @Override
            public void failure(NetworkError error) {
                // inform the user that an error happened
                firstTestResult.setText("Error getting first test result :-(");
                error.printStackTrace();
            }
        });
        apiClient.getTestResultsClient().getList(new Params().setPage(1), new ListHandler<TestResult>() {

            @Override
            public void success(List<TestResult> list) {
                // display the result
                numberOfTestResults.setText("There are " + list.size() + " test results on the first page.");
            }

            @Override
            public void failure(NetworkError e) {
                // inform the user that an error happened
                numberOfTestResults.setText("Error listing first page of test results :-(");
                e.printStackTrace();
            }
        });

        /**
         * MINORS
         */
        final TextView firstMinor = (TextView)findViewById(R.id.firstMinor);
        final TextView numberOfMinors = (TextView)findViewById(R.id.numberOfMinors);
        apiClient.getMinorsClient().getById("1", null, new EntityHandler<Minor>() {

            @Override
            public void success(Minor minor) {
                // display the result
                firstMinor.setText("The first minor was last modificated at: " + minor.getLastModified());
            }

            @Override
            public void failure(NetworkError error) {
                // inform the user that an error happened
                firstMinor.setText("Error getting first minor :-(");
                error.printStackTrace();
            }
        });
        apiClient.getMinorsClient().getList(new Params().setPage(1), new ListHandler<Minor>() {

            @Override
            public void success(List<Minor> list) {
                // display the result
                numberOfMinors.setText("There are " + list.size() + " minors on the first page.");
            }

            @Override
            public void failure(NetworkError e) {
                // inform the user that an error happened
                numberOfMinors.setText("Error listing first page of minors :-(");
                e.printStackTrace();
            }
        });

    }

    protected void _getPersonByURL(OnderwijsDataAPI apiClient, List<GroupRole> list) {
        // can't do this without the roles
        if (list == null || list.size() == 0) {

            return;
        }
        GroupRole firstRole = list.get(0);
        String personUrl = firstRole.getPersonUrl();
        final TextView personByUrl = (TextView)findViewById(R.id.personByUrl);
        apiClient.getPersonsClient().get(personUrl, null, new EntityHandler<Person>() {

            @Override
            public void success(Person person) {
                // display the result
                personByUrl.setText("The person linked from the first role is named: " + person.getDisplayName());
            }

            @Override
            public void failure(NetworkError e) {
                // inform the user that an error happened
                personByUrl.setText("Couldn't get person linked from first role :-(");
                e.printStackTrace();
            }
        });
    }

    private void _setupLoginButton(final OnderwijsDataAPI api) {
        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                api.getOAuthHandler().login("admin", "admin", new LoginHandler() {

                    @Override
                    public void success() {
                        Toast.makeText(MainActivity.this, "Successful login", Toast.LENGTH_LONG).show();
                        System.out.println(api.getOAuthHandler().getTokenData().getAccessToken());
                        _testAllApis(api);
                    }

                    @Override
                    public void failure(String message) {
                        Toast.makeText(MainActivity.this, "Login error: " + message, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
