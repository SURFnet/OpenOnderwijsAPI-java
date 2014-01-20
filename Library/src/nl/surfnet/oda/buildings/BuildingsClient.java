package nl.surfnet.oda.buildings;

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

public class BuildingsClient extends AbstractAPIClient<Building> {

    /**
     * Provides an interface to the Buildings API using retrofit.
     *
     * @author Daniel Zolnai
     *
     */
    private interface BuildingsAPIClient {

        @GET("/buildings{params}")
        public void getList(@EncodedPath("params") String params, Callback<List<Building>> cb);

        @GET("/buildings/{id}{params}")
        public void get(@Path("id") String id, @EncodedPath("params") String params, Callback<Building> cb);
    }

    private BuildingsAPIClient _buildingsAPI;

    public BuildingsClient(String baseUrl) {
        _buildingsAPI = getRestAdapter(baseUrl).create(BuildingsAPIClient.class);
    }

    /**
     * Returns a list of all buildings. Use the "page" parameter to select a page.
     * 
     * @param params Parameters of the query.
     * @param handler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    @Override
    public void getList(Params params, final ListHandler<Building> handler) {
        _buildingsAPI.getList(parametersToString(params), new Callback<List<Building>>() {

            @Override
            public void success(List<Building> list, Response response) {
                handler.success(list);
            }

            @Override
            public void failure(RetrofitError error) {
                handler.failure(new NetworkError(error));
            }
        });
    }

    /**
     * Gets the person with the given index from the API
     *
     * @param id Identifier of the Building
     * @param params Parameters of the query
     * @param handler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    @Override
    public void get(String id, Params params, final EntityHandler<Building> handler) {
        _buildingsAPI.get(id, parametersToString(params), new Callback<Building>() {

            @Override
            public void success(Building building, Response response) {
                handler.success(building);
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
        // set the GSON converter. Add the deserializers for the list and the Person object
        Type buildingsListType = new TypeToken<List<Building>>() {}.getType();
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Building.class, new BuildingDeserializer())
            .registerTypeAdapter(buildingsListType, new ListDeserializer<Building>(new BuildingDeserializer()))
            .create();
        return new GsonConverter(gson);
        //@formatter:on
    }
}
