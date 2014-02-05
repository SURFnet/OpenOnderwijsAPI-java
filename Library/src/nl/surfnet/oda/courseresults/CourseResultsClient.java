package nl.surfnet.oda.courseresults;

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
 * A client for getting info about course results from the API
 *
 * @author Daniel Zolnai
 *
 */
public class CourseResultsClient extends AbstractAPIClient<CourseResult> {

    /**
     * Provides an interface to the Course results API using retrofit.
     *
     * @author Daniel Zolnai
     *
     */
    private interface CourseResultsAPIClient {

        @GET("/courseresults{params}")
        public void getList(@EncodedPath("params") String params, Callback<List<CourseResult>> cb);

        @GET("/{path}{params}")
        public void get(@EncodedPath("path") String path, @EncodedPath("params") String params, Callback<CourseResult> cb);
    }

    private CourseResultsAPIClient _apiClient;

    public CourseResultsClient(String baseUrl, OAuthHandler oauthHandler) {
        super(baseUrl, oauthHandler);
        _apiClient = getRestAdapter(baseUrl).create(CourseResultsAPIClient.class);
    }

    /**
     * Returns a list of all course results. Use the "page" parameter to select a page.
     *
     * @param params Parameters of the query
     * @param handler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    @Override
    public void getList(Params params, final ListHandler<CourseResult> handler) {
        _apiClient.getList(parametersToString(params), new Callback<List<CourseResult>>() {

            @Override
            public void success(List<CourseResult> list, Response response) {
                handler.success(list);
            }

            @Override
            public void failure(RetrofitError error) {
                handler.failure(new NetworkError(error));
            }
        });
    }

    /**
     * Gets the course result with the given url from the API
     *
     * @param url URL of the resource returning a CourseResult type of entity
     * @param params Parameters of the query
     * @param handler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    @Override
    public void get(String url, Params params, final EntityHandler<CourseResult> handler) {
        _apiClient.get(resolveUrl(url), parametersToString(params), new Callback<CourseResult>() {

            @Override
            public void success(CourseResult room, Response response) {
                handler.success(room);
            }

            @Override
            public void failure(RetrofitError error) {
                handler.failure(new NetworkError(error));
            }
        });
    }

    @Override
    protected GsonConverter getGsonConverter() {
        //@formatter:off
        // set the GSON converter. Add the deserializers for the list and the CourseResult object
        Type courseResultListType = new TypeToken<List<CourseResult>>() {}.getType();
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(CourseResult.class, new CourseResultDeserializer())
            .registerTypeAdapter(courseResultListType, new ListDeserializer<CourseResult>(new CourseResultDeserializer()))
            .create();
        return new GsonConverter(gson);
        //@formatter:on
    }

    @Override
    protected String getEndpoint() {
        return "courseresults";
    }
}