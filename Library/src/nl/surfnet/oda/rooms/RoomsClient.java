package nl.surfnet.oda.rooms;

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
 * A client for getting info about rooms from the API
 *
 * @author Daniel Zolnai
 *
 */
public class RoomsClient extends AbstractAPIClient<Room> {

    /**
     * Provides an interface to the Rooms API using retrofit.
     *
     * @author Daniel Zolnai
     *
     */
    private interface RoomsAPIClient {

        @GET("/rooms{params}")
        public void getList(@EncodedPath("params") String params, Callback<List<Room>> cb);

        @GET("/rooms/{id}{params}")
        public void get(@Path("id") String id, @EncodedPath("params") String params, Callback<Room> cb);
    }

    private RoomsAPIClient _roomsAPI;

    public RoomsClient(String baseUrl) {
        _roomsAPI = getRestAdapter(baseUrl).create(RoomsAPIClient.class);
    }

    /**
     * Returns a list of all rooms. Use the "page" parameter to select a page.
     * 
     * @param params Parameters of the query
     * @param handler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    @Override
    public void getList(Params params, final ListHandler<Room> handler) {
        _roomsAPI.getList(parametersToString(params), new Callback<List<Room>>() {

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
     * @param params Parameters of the query
     * @param handler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    @Override
    public void get(String id, Params params, final EntityHandler<Room> handler) {
        _roomsAPI.get(id, parametersToString(params), new Callback<Room>() {

            @Override
            public void success(Room room, Response response) {
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
        // set the GSON converter. Add the deserializers for the list and the Room object
        Type roomListType = new TypeToken<List<Room>>() {}.getType();
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Room.class, new RoomDeserializer())
            .registerTypeAdapter(roomListType, new ListDeserializer<Room>(new RoomDeserializer()))
            .create();
        return new GsonConverter(gson);
        //@formatter:on
    }
}