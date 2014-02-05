package nl.surfnet.oda.affiliations;

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
 * A client for getting info about affiliations from the API
 *
 * @author Daniel Zolnai
 *
 */
public class AffiliationsClient extends AbstractAPIClient<Affiliation> {

    /**
     * Provides an interface to the Affiliations API using retrofit.
     *
     * @author Daniel Zolnai
     *
     */
    private interface AffiliationsAPIClient {

        @GET("/affiliations{params}")
        public void getList(@EncodedPath("params") String params, Callback<List<Affiliation>> cb);

        @GET("/{path}{params}")
        public void get(@EncodedPath("path") String path, @EncodedPath("params") String params, Callback<Affiliation> cb);
    }

    private AffiliationsAPIClient _apiClient;

    /**
     * Constructor. Creates an interface to the API using the RestAdapter
     *
     * @param baseUrl Base URL of the API. For example: https://api.example.com/
     */
    public AffiliationsClient(String baseUrl, OAuthHandler oauthHandler) {
        super(baseUrl, oauthHandler);
        _apiClient = getRestAdapter(baseUrl).create(AffiliationsAPIClient.class);
    }

    /**
     * Gets the affiliation with the given URL from the API
     *
     * @param url URL of the resource which returns an Affiliation entity
     * @param handler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called with the error.
     */
    @Override
    public void get(String url, Params params, final EntityHandler<Affiliation> handler) {
        _apiClient.get(resolveUrl(url), parametersToString(params), new Callback<Affiliation>() {

            @Override
            public void success(Affiliation affiliation, Response response) {
                handler.success(affiliation);
            }

            @Override
            public void failure(RetrofitError error) {
                handler.failure(new NetworkError(error));
            }
        });
    }

    /**
     * Returns a list of all affiliations. Use the "page" parameter to select a page.
     *
     * @param params Parameters of the query. Use null if none
     * @param handler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    @Override
    public void getList(Params params, final ListHandler<Affiliation> handler) {
        _apiClient.getList(parametersToString(params), new Callback<List<Affiliation>>() {

            @Override
            public void success(List<Affiliation> list, Response response) {
                handler.success(list);
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
        Type affiliationListType = new TypeToken<List<Affiliation>>() {}.getType();
        Gson gson = new GsonBuilder()
        .registerTypeAdapter(Affiliation.class, new AffiliationDeserializer())
        .registerTypeAdapter(affiliationListType, new ListDeserializer<Affiliation>(new AffiliationDeserializer()))
        .create();
        return new GsonConverter(gson);
        //@formatter:on
    }

    @Override
    protected String getEndpoint() {
        return "affiliations";
    }

}
