package nl.surfnet.oda.rooms;

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

/**
 * A client for getting info about persons from the API
 *
 * @author Daniel Zolnai
 *
 */
public class RoomsClient {

    /**
     * Provides an interface to the Rooms API using retrofit.
     *
     * @author Daniel Zolnai
     *
     */
    private interface RoomsAPIClient {

        @GET("/rooms")
        public void getList(@Query("page") Integer page, @Query("format") String format, Callback<List<Room>> cb);

        @GET("/rooms/{id}")
        public void get(@Path("id") String id, @Query("format") String format, Callback<Room> cb);
    }

    private final static String FORMAT = "json";

    private RoomsAPIClient _roomsAPI;


    public RoomsClient(String baseUrl) {
        //@formatter:off
        // set the GSON converter. Add the deserializers for the list and the Room object
        Type roomListType = new TypeToken<List<Room>>() {}.getType();
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Room.class, new RoomDeserializer())
            .registerTypeAdapter(roomListType, new RoomsListDeserializer())
            .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
            .setServer(baseUrl)
            .setConverter(new GsonConverter(gson))
            .build();
        //@formatter:on
        _roomsAPI = restAdapter.create(RoomsAPIClient.class);
    }

    /**
     * Returns a list of all rooms. Use the "page" parameter to select a page.
     *
     * @param page Page number. Use null if none.
     * @param listHandler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    public void getList(Integer page, final ListHandler<Room> handler) {
        _roomsAPI.getList(page, FORMAT, new Callback<List<Room>>() {

            @Override
            public void success(List<Room> list, Response response) {
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
     * @param id Identifier of the Room
     * @param handler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    public void get(String id, final EntityHandler<Room> handler) {
        _roomsAPI.get(id, FORMAT, new Callback<Room>() {

            @Override
            public void success(Room person, Response response) {
                handler.success(person);
            }

            @Override
            public void failure(RetrofitError error) {
                handler.failure(new NetworkError(error));
            }
        });
    }

}