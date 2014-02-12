package nl.surfnet.oda;

import nl.surfnet.oda.affiliations.AffiliationsClient;
import nl.surfnet.oda.buildings.BuildingsClient;
import nl.surfnet.oda.courseresults.CourseResultsClient;
import nl.surfnet.oda.courses.CoursesClient;
import nl.surfnet.oda.grouproles.GroupRolesClient;
import nl.surfnet.oda.groups.GroupsClient;
import nl.surfnet.oda.minors.MinorsClient;
import nl.surfnet.oda.newfeeds.NewsFeedsClient;
import nl.surfnet.oda.newsitems.NewsItemsClient;
import nl.surfnet.oda.oauth.OAuthHandler;
import nl.surfnet.oda.persons.PersonsClient;
import nl.surfnet.oda.rooms.RoomsClient;
import nl.surfnet.oda.schedule.ScheduleClient;
import nl.surfnet.oda.testresults.TestResultsClient;

/**
 * Base class for communication with the OnderwijsDataAPI. Use the getter methods for getting the different clients.
 *
 * @author Daniel Zolnai
 *
 */
public class OnderwijsDataAPI {

    private String _baseUrl;
    private OAuthHandler _oauthHandler;

    private PersonsClient _personsClient;
    private BuildingsClient _buildingsClient;
    private RoomsClient _roomsClient;
    private GroupsClient _groupsClient;
    private AffiliationsClient _affiliationsClient;
    private NewsItemsClient _newsItemsClient;
    private NewsFeedsClient _newsFeedsClient;
    private GroupRolesClient _groupRolesClient;
    private CoursesClient _coursesClient;
    private ScheduleClient _scheduleClient;
    private CourseResultsClient _courseResultsClient;
    private TestResultsClient _testResultClient;
    private MinorsClient _minorsClient;

    /**
     * Basic constructor
     *
     * @param baseUrl The URL of your API. For example, https://api.example.com
     */
    public OnderwijsDataAPI(String baseUrl) {
        String fixedBaseUrl = baseUrl;
        if (!fixedBaseUrl.endsWith("/")) {
            fixedBaseUrl += "/";
        }
        _baseUrl = fixedBaseUrl;
        _oauthHandler = new OAuthHandler(baseUrl);
    }

    /**
     * Returns the OAuth handler. Needed if the API is OAuth protected, and you want to access it with your credentials.
     *
     * @return The manager of the authentication with the API.
     */
    public OAuthHandler getOAuthHandler() {
        return _oauthHandler;
    }

    /**
     * Returns the client which is responsible for the handling of persons data
     *
     * @return The client handling persons data
     */
    public PersonsClient getPersonsClient() {
        if (_personsClient == null) {
            _personsClient = new PersonsClient(_baseUrl, _oauthHandler);
        }
        return _personsClient;
    }

    /**
     * Return the client which is responsible for the handling of buildings data.
     *
     * @return The client handling buildings data
     */
    public BuildingsClient getBuildingsClient() {
        if (_buildingsClient == null) {
            _buildingsClient = new BuildingsClient(_baseUrl, _oauthHandler);
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
            _roomsClient = new RoomsClient(_baseUrl, _oauthHandler);
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
            _groupsClient = new GroupsClient(_baseUrl, _oauthHandler);
        }
        return _groupsClient;
    }

    /**
     * Returns the client which is responsible for the handling of affiliations data.
     *
     * @return The client handling affiliations data.
     */
    public AffiliationsClient getAffiliationsClient() {
        if (_affiliationsClient == null) {
            _affiliationsClient = new AffiliationsClient(_baseUrl, _oauthHandler);
        }
        return _affiliationsClient;
    }

    /**
     * Returns the client which is responsible for the handling of news items data.
     *
     * @return The client handling news items data.
     */
    public NewsItemsClient getNewsItemsClient() {
        if (_newsItemsClient == null) {
            _newsItemsClient = new NewsItemsClient(_baseUrl, _oauthHandler);
        }
        return _newsItemsClient;
    }

    /**
     * Returns the client which is responsible for the handling of news feeds data.
     *
     * @return The client handling news feeds data.
     */
    public NewsFeedsClient getNewsFeedsClient() {
        if (_newsFeedsClient == null) {
            _newsFeedsClient = new NewsFeedsClient(_baseUrl, _oauthHandler);
        }
        return _newsFeedsClient;
    }

    /**
     * Returns the client which is responsible for the handling of group roles data.
     *
     * @return The client handling group roles data.
     */
    public GroupRolesClient getGroupRolesClient() {
        if (_groupRolesClient == null) {
            _groupRolesClient = new GroupRolesClient(_baseUrl, _oauthHandler);
        }
        return _groupRolesClient;
    }

    /**
     * Returns the client which is responsible for the handling of courses data.
     *
     * @return The client handling courses data.
     */
    public CoursesClient getCoursesClient() {
        if (_coursesClient == null) {
            _coursesClient = new CoursesClient(_baseUrl, _oauthHandler);
        }
        return _coursesClient;
    }

    /**
     * Returns the client which is responsible for the handling of schedule data.
     *
     * @return The client handling schedule data.
     */
    public ScheduleClient getScheduleClient() {
        if (_scheduleClient == null) {
            _scheduleClient = new ScheduleClient(_baseUrl, _oauthHandler);
        }
        return _scheduleClient;
    }

    /**
     * Returns the client which is responsible for the handling of course results data.
     *
     * @return The client handling course results data.
     */
    public CourseResultsClient getCourseResultsClient() {
        if (_courseResultsClient == null) {
            _courseResultsClient = new CourseResultsClient(_baseUrl, _oauthHandler);
        }
        return _courseResultsClient;
    }

    /**
     * Returns the client which is responsible for the handling of test results data.
     *
     * @return The client handling test results data.
     */
    public TestResultsClient getTestResultsClient() {
        if (_testResultClient == null) {
            _testResultClient = new TestResultsClient(_baseUrl, _oauthHandler);
        }
        return _testResultClient;
    }

    /**
     * Returns the client which is responsible for the handling of minors data.
     *
     * @return The client handling minors data.
     */
    public MinorsClient getMinorsClient() {
        if (_minorsClient == null) {
            _minorsClient = new MinorsClient(_baseUrl, _oauthHandler);
        }
        return _minorsClient;
    }
}
