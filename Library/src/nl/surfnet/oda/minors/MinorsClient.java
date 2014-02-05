package nl.surfnet.oda.minors;

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
 * A client for getting info about minors from the API
 *
 * @author Daniel Zolnai
 *
 */
public class MinorsClient extends AbstractAPIClient<Minor> {

    /**
     * Provides an interface to the Minors API using retrofit.
     *
     * @author Daniel Zolnai
     *
     */
    private interface MinorsAPIClient {

        @GET("/minors{params}")
        public void getList(@EncodedPath("params") String params, Callback<List<Minor>> cb);

        @GET("/{path}{params}")
        public void get(@EncodedPath("path") String path, @EncodedPath("params") String params, Callback<Minor> cb);
    }

    private MinorsAPIClient _apiClient;

    public MinorsClient(String baseUrl, OAuthHandler oauthHandler) {
        super(baseUrl, oauthHandler);
        _apiClient = getRestAdapter(baseUrl).create(MinorsAPIClient.class);
    }

    /**
     * Returns a list of all minors. Use the "page" parameter to select a page.
     *
     * @param params Parameters of the query
     * @param handler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    @Override
    public void getList(Params params, final ListHandler<Minor> handler) {
        _apiClient.getList(parametersToString(params), new Callback<List<Minor>>() {

            @Override
            public void success(List<Minor> list, Response response) {
                handler.success(list);
            }

            @Override
            public void failure(RetrofitError error) {
                handler.failure(new NetworkError(error));
            }
        });
    }

    /**
     * Gets the minor with the given url from the API
     *
     * @param url URL of the resource returning a Minor type of entity
     * @param params Parameters of the query
     * @param handler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    @Override
    public void get(String url, Params params, final EntityHandler<Minor> handler) {
        _apiClient.get(resolveUrl(url), parametersToString(params), new Callback<Minor>() {

            @Override
            public void success(Minor room, Response response) {
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
        // set the GSON converter. Add the deserializers for the list and the Minor object
        Type minorListType = new TypeToken<List<Minor>>() {}.getType();
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Minor.class, new MinorDeserializer())
            .registerTypeAdapter(minorListType, new ListDeserializer<Minor>(new MinorDeserializer()))
            .create();
        return new GsonConverter(gson);
        //@formatter:on
    }

    @Override
    protected String getEndpoint() {
        return "minors";
    }
}
