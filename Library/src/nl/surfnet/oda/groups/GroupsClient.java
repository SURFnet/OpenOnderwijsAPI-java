package nl.surfnet.oda.groups;

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
 * The client communicating with the Groups API.
 *
 * @author Daniel Zolnai
 *
 */
public class GroupsClient extends AbstractAPIClient<Group> {

    /**
     * Defines the interface for the communication with the groups API using retrofit annotations.
     *
     * @author Daniel Zolnai
     *
     */
    private interface GroupsAPIClient {
        @GET("/groups{params}")
        public void getList(@EncodedPath("params") String params, Callback<List<Group>> cb);

        @GET("/{path}{params}")
        public void get(@EncodedPath("path") String path, @EncodedPath("params") String params, Callback<Group> cb);
    }

    private GroupsAPIClient _groupsApi;

    /**
     * Constructor. Creates a new client for the communication with the API using the RestAdapter.
     *
     * @param baseUrl URL of the API. For example: https://api.company.com/
     */
    public GroupsClient(String baseUrl) {
        super(baseUrl);
        _groupsApi = getRestAdapter(baseUrl).create(GroupsAPIClient.class);
    }

    /**
     * Returns a group with the given url. You can add additional parameters to your Params object.
     *
     * @param url URL of the resource with a Group type
     * @param params Additional parameters. Use 'null' if none.
     * @param handler Callback for the result or error.
     */
    @Override
    public void get(String url, Params params, final EntityHandler<Group> handler) {
        _groupsApi.get(resolveUrl(url), parametersToString(params), new Callback<Group>() {

            @Override
            public void success(Group group, Response response) {
                handler.success(group);
            }

            @Override
            public void failure(RetrofitError error) {
                handler.failure(new NetworkError(error));
            }
        });
    }

    /**
     * Returns a list of all groups. Use the setPage( ) on the Params to select a page.
     *
     * @param params A Param object containing the parameters of this query.
     * @param handler Callback for the result or error.
     */
    @Override
    public void getList(Params params, final ListHandler<Group> handler) {
        _groupsApi.getList(parametersToString(params), new Callback<List<Group>>() {

            @Override
            public void success(List<Group> list, Response response) {
                handler.success(list);
            }

            @Override
            public void failure(RetrofitError error) {
                handler.failure(new NetworkError(error));
            }
        });
    }

    /**
     * Returns a GSON converter which converts the JSON output to Group objects
     */
    @Override
    protected GsonConverter getGsonConverter() {
        //@formatter:off
        Type groupListType = new TypeToken<List<Group>>() {}.getType();
        Gson gson = new GsonBuilder()
        .registerTypeAdapter(Group.class, new GroupDeserializer())
        .registerTypeAdapter(groupListType, new ListDeserializer<Group>(new GroupDeserializer()))
        .create();
        return new GsonConverter(gson);
        //@formatter:on
    }

    @Override
    protected String getEndpoint() {
        return "groups";
    }

}
