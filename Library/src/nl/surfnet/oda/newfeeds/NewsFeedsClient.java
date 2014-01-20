package nl.surfnet.oda.newfeeds;

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
import retrofit.http.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * A client for getting info about news feeds from the API
 * 
 * @author Daniel Zolnai
 * 
 */
public class NewsFeedsClient extends AbstractAPIClient<NewsFeed> {
    /**
     * Provides an interface to the NewsFeeds API using retrofit.
     *
     * @author Daniel Zolnai
     *
     */
    private interface NewsFeedsAPIClient {

        @GET("/newsfeeds{params}")
        public void getList(@EncodedPath("params") String params, Callback<List<NewsFeed>> cb);

        @GET("/newsfeeds/{id}{params}")
        public void get(@Path("id") String id, @EncodedPath("params") String params, Callback<NewsFeed> cb);
    }

    private NewsFeedsAPIClient _apiClient;

    /**
     * Constructor. Creates a new interface for the communication with the API using a Retrofit RestAdapter
     *
     * @param baseUrl
     */
    public NewsFeedsClient(String baseUrl) {
        _apiClient = getRestAdapter(baseUrl).create(NewsFeedsAPIClient.class);
    }

    /**
     * Returns a list of all newsfeeds. Use the "page" parameter to select a page.
     *
     * @param params Parameters of the query. Use null if none
     * @param listHandler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    @Override
    public void getList(Params params, final ListHandler<NewsFeed> handler) {
        _apiClient.getList(parametersToString(params), new Callback<List<NewsFeed>>() {

            @Override
            public void success(List<NewsFeed> list, Response response) {
                handler.success(list);
            }

            @Override
            public void failure(RetrofitError error) {
                handler.failure(new NetworkError(error));
            }
        });
    }

    /**
     * Gets the newsFeed with the given ID from the API
     *
     * @param id Identifier of the NewsFeed
     * @param handler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    @Override
    public void get(String id, Params params, final EntityHandler<NewsFeed> handler) {
        _apiClient.get(id, parametersToString(params), new Callback<NewsFeed>() {

            @Override
            public void success(NewsFeed newsFeed, Response response) {
                handler.success(newsFeed);
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
        Type newsFeedListType = new TypeToken<List<NewsFeed>>() {}.getType();
        Gson gson = new GsonBuilder()
        .registerTypeAdapter(NewsFeed.class, new NewsFeedDeserializer())
        .registerTypeAdapter(newsFeedListType, new ListDeserializer<NewsFeed>(new NewsFeedDeserializer()))
        .create();
        return new GsonConverter(gson);
        //@formatter:on
    }

}
