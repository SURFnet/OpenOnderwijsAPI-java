package nl.surfnet.oda.buildings;

import java.lang.reflect.Type;
import java.util.List;

import nl.surfnet.oda.EntityHandler;
import nl.surfnet.oda.ListHandler;
import nl.surfnet.oda.NetworkError;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class BuildingsClient {

    /**
     * Provides an interface to the Buildings API using retrofit.
     *
     * @author Daniel Zolnai
     *
     */
    private interface BuildingsAPIClient {

        @GET("/buildings")
        public void getList(@Query("page") Integer page, @Query("format") String format, Callback<List<Building>> cb);

        @GET("/buildings/{id}")
        public void get(@Path("id") String id, @Query("format") String format, Callback<Building> cb);
    }

    private final static String FORMAT = "json";

    private BuildingsAPIClient _buildingsAPI;

    public BuildingsClient(String baseUrl) {
        //@formatter:off
        // set the GSON converter. Add the deserializers for the list and the Person object
        Type buildingsListType = new TypeToken<List<Building>>() {}.getType();
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Building.class, new BuildingDeserializer())
            .registerTypeAdapter(buildingsListType, new BuildingsListDeserializer())
            .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
            .setServer(baseUrl)
            .setConverter(new GsonConverter(gson))
            .build();
        //@formatter:on
        _buildingsAPI = restAdapter.create(BuildingsAPIClient.class);
    }

    /**
     * Returns a list of all buildings. Use the "page" parameter to select a page.
     *
     * @param page Page number. Use null if none.
     * @param listHandler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    public void getList(Integer page, final ListHandler<Building> handler) {
        _buildingsAPI.getList(page, FORMAT, new Callback<List<Building>>() {

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
     * @param handler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    public void get(String id, final EntityHandler<Building> handler) {
        _buildingsAPI.get(id, FORMAT, new Callback<Building>() {

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
}
