package nl.surfnet.oda.grouproles;

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
 * A client for getting info about group roles from the API
 *
 * @author Daniel Zolnai
 *
 */
public class GroupRolesClient extends AbstractAPIClient<GroupRole> {

    /**
     * Provides an interface to the GroupRoles API using retrofit.
     *
     * @author Daniel Zolnai
     *
     */
    private interface GroupRolesAPIClient {

        @GET("/grouproles{params}")
        public void getList(@EncodedPath("params") String params, Callback<List<GroupRole>> cb);

        @GET("/{path}{params}")
        public void get(@EncodedPath("path") String path, @EncodedPath("params") String params, Callback<GroupRole> cb);
    }

    private GroupRolesAPIClient _apiClient;

    /**
     * Constructor. Creates a new interface for the communication with the API using a Retrofit RestAdapter
     *
     * @param baseUrl
     */
    public GroupRolesClient(String baseUrl) {
        super(baseUrl);
        _apiClient = getRestAdapter(baseUrl).create(GroupRolesAPIClient.class);
    }

    /**
     * Returns a list of all group roles. Use the "page" parameter to select a page.
     *
     * @param params Parameters of the query. Use null if none
     * @param handler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    @Override
    public void getList(Params params, final ListHandler<GroupRole> handler) {
        _apiClient.getList(parametersToString(params), new Callback<List<GroupRole>>() {

            @Override
            public void success(List<GroupRole> list, Response response) {
                handler.success(list);
            }

            @Override
            public void failure(RetrofitError error) {
                handler.failure(new NetworkError(error));
            }
        });
    }

    /**
     * Gets the group role with the given URL from the API
     * 
     * @param url URL of the resource which returns a GroupRole entity
     * @param handler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    @Override
    public void get(String url, Params params, final EntityHandler<GroupRole> handler) {
        _apiClient.get(resolveUrl(url), parametersToString(params), new Callback<GroupRole>() {

            @Override
            public void success(GroupRole person, Response response) {
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
        Type groupRoleListType = new TypeToken<List<GroupRole>>() {}.getType();
        Gson gson = new GsonBuilder()
        .registerTypeAdapter(GroupRole.class, new GroupRoleDeserializer())
        .registerTypeAdapter(groupRoleListType, new ListDeserializer<GroupRole>(new GroupRoleDeserializer()))
        .create();
        return new GsonConverter(gson);
        //@formatter:on
    }

    @Override
    protected String getEndpoint() {
        return "grouproles";
    }
}