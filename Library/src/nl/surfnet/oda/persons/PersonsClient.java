package nl.surfnet.oda.persons;

import java.lang.reflect.Type;
import java.util.List;

import nl.surfnet.oda.EntityHandler;
import nl.surfnet.oda.ListHandler;
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
public class PersonsClient {

    /**
     * Provides an interface to the Persons API using retrofit.
     *
     * @author Daniel Zolnai
     *
     */
    private interface PersonsAPIClient {

        @GET("/persons")
        public void getList(@Query("page") Integer page, @Query("format") String format, Callback<List<Person>> cb);

        @GET("/persons/{id}")
        public void get(@Path("id") int id, @Query("format") String format, Callback<Person> cb);
    }

    private final static String FORMAT = "json";

    private PersonsAPIClient _personsAPI;


    public PersonsClient(String baseUrl) {
        //@formatter:off
        // set the GSON converter. Add the deserializers for the list and the Person object
        Type personListType = new TypeToken<List<Person>>() {}.getType();
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Person.class, new PersonDeserializer())
            .registerTypeAdapter(personListType, new PersonsListDeserializer())
            .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
            .setServer(baseUrl)
            .setConverter(new GsonConverter(gson))
            .build();
        //@formatter:on
        _personsAPI = restAdapter.create(PersonsAPIClient.class);
    }

    /**
     * Returns a list of all persons. Use the "page" parameter to select a page.
     *
     * @param page Page number. Use null if none.
     * @param listHandler The onComplete method is called with the result as parameter if everything went well. Otherwise onError will be called.
     */
    public void getList(Integer page, final ListHandler<Person> handler) {
        _personsAPI.getList(page, FORMAT, new Callback<List<Person>>() {

            @Override
            public void success(List<Person> list, Response response) {
                handler.onComplete(list);
            }

            @Override
            public void failure(RetrofitError error) {
                handler.onError(error);
            }
        });
    }

    /**
     * Gets the person with the given index from the API
     *
     * @param id Identifier of the Person
     * @param handler The onComplete method is called with the result as parameter if everything went well. Otherwise onError will be called.
     */
    public void get(int id, final EntityHandler<Person> handler) {
        _personsAPI.get(id, FORMAT, new Callback<Person>() {

            @Override
            public void success(Person person, Response response) {
                handler.onComplete(person);
            }

            @Override
            public void failure(RetrofitError error) {
                handler.onError(error);
            }
        });
    }

}