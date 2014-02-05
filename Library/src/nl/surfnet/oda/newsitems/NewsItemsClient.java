package nl.surfnet.oda.newsitems;

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
 * A client for getting info about news items from the API
 *
 * @author Daniel Zolnai
 *
 */
public class NewsItemsClient extends AbstractAPIClient<NewsItem> {

    /**
     * Provides an interface to the NewsItems API using retrofit.
     *
     * @author Daniel Zolnai
     *
     */
    private interface NewsItemsAPIClient {

        @GET("/newsitems{params}")
        public void getList(@EncodedPath("params") String params, Callback<List<NewsItem>> cb);

        @GET("/{path}{params}")
        public void get(@EncodedPath("path") String path, @EncodedPath("params") String params, Callback<NewsItem> cb);
    }

    private NewsItemsAPIClient _apiClient;

    /**
     * Constructor. Creates a new interface for the communication with the API using a Retrofit RestAdapter
     *
     * @param baseUrl
     */
    public NewsItemsClient(String baseUrl, OAuthHandler oauthHandler) {
        super(baseUrl, oauthHandler);
        _apiClient = getRestAdapter(baseUrl).create(NewsItemsAPIClient.class);
    }

    /**
     * Returns a list of all news items. Use the "page" parameter to select a page.
     *
     * @param params Parameters of the query. Use null if none
     * @param handler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    @Override
    public void getList(Params params, final ListHandler<NewsItem> handler) {
        _apiClient.getList(parametersToString(params), new Callback<List<NewsItem>>() {

            @Override
            public void success(List<NewsItem> list, Response response) {
                handler.success(list);
            }

            @Override
            public void failure(RetrofitError error) {
                handler.failure(new NetworkError(error));
            }
        });
    }

    /**
     * Gets the news item with the given ID from the API
     *
     * @param url URL of the resource which returns a NewsItem entity.
     * @param handler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    @Override
    public void get(String url, Params params, final EntityHandler<NewsItem> handler) {
        _apiClient.get(resolveUrl(url), parametersToString(params), new Callback<NewsItem>() {

            @Override
            public void success(NewsItem person, Response response) {
                handler.success(person);
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
        Type newsItemListType = new TypeToken<List<NewsItem>>() {}.getType();
        Gson gson = new GsonBuilder()
        .registerTypeAdapter(NewsItem.class, new NewsItemDeserializer())
        .registerTypeAdapter(newsItemListType, new ListDeserializer<NewsItem>(new NewsItemDeserializer()))
        .create();
        return new GsonConverter(gson);
        //@formatter:on
    }

    @Override
    protected String getEndpoint() {
        return "newsitems";
    }
}
