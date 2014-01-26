package nl.surfnet.oda.testresults;

import java.lang.reflect.Type;
import java.util.List;

import nl.surfnet.oda.AbstractAPIClient;
import nl.surfnet.oda.EntityHandler;
import nl.surfnet.oda.ListDeserializer;
import nl.surfnet.oda.ListHandler;
import nl.surfnet.oda.NetworkError;
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
 * A client for getting info about testresults from the API
 *
 * @author Daniel Zolnai
 *
 */
public class TestResultsClient extends AbstractAPIClient<TestResult> {

    /**
     * Provides an interface to the Test results API using retrofit.
     *
     * @author Daniel Zolnai
     *
     */
    private interface TestResultsAPIClient {

        @GET("/testresults{params}")
        public void getList(@EncodedPath("params") String params, Callback<List<TestResult>> cb);

        @GET("/{path}{params}")
        public void get(@EncodedPath("path") String path, @EncodedPath("params") String params, Callback<TestResult> cb);
    }

    private TestResultsAPIClient _apiClient;

    public TestResultsClient(String baseUrl) {
        super(baseUrl);
        _apiClient = getRestAdapter(baseUrl).create(TestResultsAPIClient.class);
    }

    /**
     * Returns a list of all test results. Use the "page" parameter to select a page.
     *
     * @param params Parameters of the query
     * @param handler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    @Override
    public void getList(Params params, final ListHandler<TestResult> handler) {
        _apiClient.getList(parametersToString(params), new Callback<List<TestResult>>() {

            @Override
            public void success(List<TestResult> list, Response response) {
                handler.success(list);
            }

            @Override
            public void failure(RetrofitError error) {
                handler.failure(new NetworkError(error));
            }
        });
    }

    /**
     * Gets the test result with the given url from the API
     *
     * @param url URL of the resource returning a TestResult type of entity
     * @param params Parameters of the query
     * @param handler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    @Override
    public void get(String url, Params params, final EntityHandler<TestResult> handler) {
        _apiClient.get(resolveUrl(url), parametersToString(params), new Callback<TestResult>() {

            @Override
            public void success(TestResult room, Response response) {
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
        // set the GSON converter. Add the deserializers for the list and the TestResult object
        Type testResultListType = new TypeToken<List<TestResult>>() {}.getType();
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(TestResult.class, new TestResultDeserializer())
            .registerTypeAdapter(testResultListType, new ListDeserializer<TestResult>(new TestResultDeserializer()))
            .create();
        return new GsonConverter(gson);
        //@formatter:on
    }

    @Override
    protected String getEndpoint() {
        return "testresults";
    }
}

