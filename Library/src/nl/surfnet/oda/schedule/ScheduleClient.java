package nl.surfnet.oda.schedule;

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
 * A client for getting info about lessons from the API
 *
 * @author Daniel Zolnai
 *
 */
public class ScheduleClient extends AbstractAPIClient<Lesson> {

    /**
     * Provides an interface to the Schedule API using retrofit.
     *
     * @author Daniel Zolnai
     *
     */
    private interface LessonsAPIClient {

        @GET("/schedule{params}")
        public void getList(@EncodedPath("params") String params, Callback<List<Lesson>> cb);

        @GET("/{path}{params}")
        public void get(@EncodedPath("path") String path, @EncodedPath("params") String params, Callback<Lesson> cb);

        @GET("/persons/{person_id}/schedule{params}")
        public void getScheduleByPerson(@Path("person_id") String person_id, @EncodedPath("params") String params, Callback<List<Lesson>> cb);

        @GET("/rooms/{room_id}/schedule{params}")
        public void getScheduleByRoom(@Path("room_id") String room_id, @EncodedPath("params") String params, Callback<List<Lesson>> cb);

        @GET("/groups/{group_id}/schedule{params}")
        public void getScheduleByGroup(@Path("group_id") String group_id, @EncodedPath("params") String params, Callback<List<Lesson>> cb);

    }

    private LessonsAPIClient _apiClient;

    public ScheduleClient(String baseUrl) {
        super(baseUrl);
        _apiClient = getRestAdapter(baseUrl).create(LessonsAPIClient.class);
    }

    /**
     * Returns a list of all lessons. Use the "page" parameter to select a page.
     *
     * @param params Parameters of the query. Use null if none
     * @param handler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    @Override
    public void getList(Params params, final ListHandler<Lesson> handler) {
        _apiClient.getList(parametersToString(params), new Callback<List<Lesson>>() {

            @Override
            public void success(List<Lesson> list, Response response) {
                handler.success(list);
            }

            @Override
            public void failure(RetrofitError error) {
                handler.failure(new NetworkError(error));
            }
        });
    }

    /**
     * Gets the lesson with the given URL from the API
     *
     * @param url URL of the resource which returns a Lesson type entity
     * @param params Parameters of the query. Use null if none.
     * @param handler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    @Override
    public void get(String url, Params params, final EntityHandler<Lesson> handler) {
        _apiClient.get(resolveUrl(url), parametersToString(params), new Callback<Lesson>() {

            @Override
            public void success(Lesson lesson, Response response) {
                handler.success(lesson);
            }

            @Override
            public void failure(RetrofitError error) {
                handler.failure(new NetworkError(error));
            }
        });
    }

    /**
     * Returns a schedule assiociated to a group.
     *
     * @param group_id ID of the group.
     * @param params Parameters of the query. Use null if none.
     * @param handler Callback for success/failure.
     */
    public void getScheduleByGroup(String group_id, Params params, final ListHandler<Lesson> handler) {
        _apiClient.getScheduleByGroup(group_id, parametersToString(params), new Callback<List<Lesson>>() {

            @Override
            public void success(List<Lesson> list, Response response) {
                handler.success(list);
            }

            @Override
            public void failure(RetrofitError error) {
                handler.failure(new NetworkError(error));
            }
        });
    }

    /**
     * Returns a schedule assiociated to a room.
     *
     * @param group_id ID of the group.
     * @param params Parameters of the query. Use null if none.
     * @param handler Callback for success/failure.
     */
    public void getScheduleByRoom(String room_id, Params params, final ListHandler<Lesson> handler) {
        _apiClient.getScheduleByRoom(room_id, parametersToString(params), new Callback<List<Lesson>>() {

            @Override
            public void success(List<Lesson> list, Response response) {
                handler.success(list);
            }

            @Override
            public void failure(RetrofitError error) {
                handler.failure(new NetworkError(error));
            }
        });
    }

    /**
     * Returns a schedule assiociated to a person.
     *
     * @param group_id ID of the group.
     * @param params Parameters of the query. Use null if none.
     * @param handler Callback for success/failure.
     */
    public void getScheduleByPerson(String person_id, Params params, final ListHandler<Lesson> handler) {
        _apiClient.getScheduleByPerson(person_id, parametersToString(params), new Callback<List<Lesson>>() {

            @Override
            public void success(List<Lesson> list, Response response) {
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
        Type lessonListType = new TypeToken<List<Lesson>>() {}.getType();
        Gson gson = new GsonBuilder()
        .registerTypeAdapter(Lesson.class, new LessonDeserializer())
        .registerTypeAdapter(lessonListType, new ListDeserializer<Lesson>(new LessonDeserializer()))
        .create();
        return new GsonConverter(gson);
        //@formatter:on
    }

    @Override
    protected String getEndpoint() {
        return "schedule";
    }

}
