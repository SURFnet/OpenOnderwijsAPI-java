package nl.surfnet.oda.persons;

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
 * A client for getting info about persons from the API
 *
 * @author Daniel Zolnai
 *
 */
public class PersonsClient extends AbstractAPIClient<Person> {

    /**
     * Provides an interface to the Persons API using retrofit.
     *
     * @author Daniel Zolnai
     *
     */
    private interface PersonsAPIClient {

        @GET("/persons{params}")
        public void getList(@EncodedPath("params") String params, Callback<List<Person>> cb);

        @GET("/persons/{id}{params}")
        public void get(@Path("id") String id, @EncodedPath("params") String params, Callback<Person> cb);
    }

    private PersonsAPIClient _personsAPI;


    /**
     * Constructor. Creates a new interface for the communication with the API using a Retrofit RestAdapter
     *
     * @param baseUrl
     */
    public PersonsClient(String baseUrl) {
        _personsAPI = getRestAdapter(baseUrl).create(PersonsAPIClient.class);
    }

    /**
     * Returns a list of all persons. Use the "page" parameter to select a page.
     *
     * @param params Parameters of the query. Use null if none
     * @param handler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    @Override
    public void getList(Params params, final ListHandler<Person> handler) {
        _personsAPI.getList(parametersToString(params), new Callback<List<Person>>() {

            @Override
            public void success(List<Person> list, Response response) {
                handler.success(list);
            }

            @Override
            public void failure(RetrofitError error) {
                handler.failure(new NetworkError(error));
            }
        });
    }

    /**
     * Gets the person with the given ID from the API
     *
     * @param id Identifier of the Person
     * @param handler The 'success' method is called with the result as parameter if everything went well. Otherwise 'failure' will be called.
     */
    @Override
    public void get(String id, Params params, final EntityHandler<Person> handler) {
        _personsAPI.get(id, parametersToString(params), new Callback<Person>() {

            @Override
            public void success(Person person, Response response) {
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
        Type personListType = new TypeToken<List<Person>>() {}.getType();
        Gson gson = new GsonBuilder()
        .registerTypeAdapter(Person.class, new PersonDeserializer())
        .registerTypeAdapter(personListType, new ListDeserializer<Person>(new PersonDeserializer()))
        .create();
        return new GsonConverter(gson);
        //@formatter:on
    }
}