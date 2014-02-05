package nl.surfnet.oda.courses;

import java.lang.reflect.Type;
import java.util.List;

import nl.surfnet.oda.AbstractAPIClient;
import nl.surfnet.oda.EntityHandler;
import nl.surfnet.oda.ListDeserializer;
import nl.surfnet.oda.ListHandler;
import nl.surfnet.oda.NetworkError;
import nl.surfnet.oda.oauth.OAuthHandler;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.http.EncodedPath;
import retrofit.http.GET;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * A client for getting info about courses from the API
 *
 * @author Daniel Zolnai
 *
 */
public class CoursesClient extends AbstractAPIClient<Course> {

    /**
     * Provides an interface to the Courses API using retrofit.
     *
     * @author Daniel Zolnai
     *
     */
    private interface CoursesAPIClient {

        @GET("/courses{params}")
        public void getList(@EncodedPath("params") String params, Callback<List<Course>> cb);

        @GET("/{path}{params}")
        public void get(@EncodedPath("path") String path, @EncodedPath("params") String params, Callback<Course> cb);
    }

    private CoursesAPIClient _apiClient;

    public CoursesClient(String baseUrl, OAuthHandler oauthHandler) {
        super(baseUrl, oauthHandler);
        _apiClient = getRestAdapter(baseUrl).create(CoursesAPIClient.class);
    }

    /**
     * Returns a list of all courses. Use the "page" parameter to select a page.
     *
     * @param params Parameters of the query. Use null if none
     * @param handler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    @Override
    public void getList(Params params, final ListHandler<Course> handler) {
        _apiClient.getList(parametersToString(params), new Callback<List<Course>>() {

            @Override
            public void success(List<Course> list, Response response) {
                handler.success(list);
            }

            @Override
            public void failure(RetrofitError error) {
                handler.failure(new NetworkError(error));
            }
        });
    }

    /**
     * Gets the courses with the given URL from the API
     *
     * @param url URL of the resource which returns a Course type entity
     * @param params Parameters of the query. Use null if none.
     * @param handler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    @Override
    public void get(String url, Params params, final EntityHandler<Course> handler) {
        _apiClient.get(resolveUrl(url), parametersToString(params), new Callback<Course>() {

            @Override
            public void success(Course course, Response response) {
                handler.success(course);
            }

            @Override
            public void failure(RetrofitError error) {
                handler.failure(new NetworkError(error));
            }
        });
    }

    /**
     * Returns a GSON converter, this converts the API output to the correct java objects.
     */
    @Override
    protected GsonConverter getGsonConverter() {
        //@formatter:off
        Type courseListType = new TypeToken<List<Course>>() {}.getType();
        Gson gson = new GsonBuilder()
        .registerTypeAdapter(Course.class, new CourseDeserializer())
        .registerTypeAdapter(courseListType, new ListDeserializer<Course>(new CourseDeserializer()))
        .create();
        return new GsonConverter(gson);
        //@formatter:on
    }

    @Override
    protected String getEndpoint() {
        return "courses";
    }

}
