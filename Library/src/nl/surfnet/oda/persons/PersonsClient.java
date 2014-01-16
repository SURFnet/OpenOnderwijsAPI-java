package nl.surfnet.oda.persons;

import java.util.List;

import nl.surfnet.oda.APIClient;
import nl.surfnet.oda.EntityHandler;
import nl.surfnet.oda.ListHandler;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PersonsClient extends APIClient {

    private final static String PERSONS = "persons";
    private final static String PERSON = "person/%d";

    private ObjectMapper _mapper;

    /**
     * Constructor
     *
     * @param context Context of the application. Use an Activity, or Application.getApplicationContext()
     * @param baseUrl Base URL of the API, like https://api.example.com/
     */
    public PersonsClient(Context context, String baseUrl) {
        super(context, baseUrl);
        _mapper = new ObjectMapper();
    }

    /**
     * Returns a list of all persons. Use the "page" parameter to select a page.
     *
     * @param listHandler The onComplete method is called with the result as parameter if everything went well. Otherwise onError will be called. If you don't
     *            want to use a parameter, use null.
     */
    public void getList(List<NameValuePair> params, final ListHandler<Person> listHandler) {
        // append the parameters to the path
        String path = appendParameters(PERSONS, params);
        get(path, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject result) {
                try {
                    // Jackson Objectmapper converts the JSON to the Java objects
                    JSONArray personsData = result.getJSONArray("data");
                    List<Person> persons = _mapper.readValue(personsData.toString(), new TypeReference<List<Person>>() {
                    });
                    listHandler.onComplete(persons);
                } catch (Exception e) {
                    listHandler.onError(e);
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                listHandler.onError(error);
            }
        });
    }

    /**
     * Gets the person with the given index from the API
     *
     * @param index Identifier of the Person
     * @param handler The onComplete method is called with the result as parameter if everything went well. Otherwise onError will be called.
     */
    public void get(long id, final EntityHandler<Person> handler) {
        // insert the index into the path
        String path = String.format(PERSON, id);
        get(path, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject result) {
                try {
                    // Jackson Objectmapper converts the JSON to the Java objects
                    JSONArray personData = result.getJSONArray("data");
                    Person person = _mapper.readValue(personData.toString(), Person.class);
                    handler.onComplete(person);
                } catch (Exception e) {
                    handler.onError(e);
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                handler.onError(error);
            }
        });
    }
}
