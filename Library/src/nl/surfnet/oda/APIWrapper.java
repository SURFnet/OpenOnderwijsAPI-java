package nl.surfnet.oda;

import java.util.List;

import nl.surfnet.oda.entity.Person;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class APIWrapper {

    private final static String PERSONS = "persons";

    private APIClient _apiClient;
    private ObjectMapper _mapper;

    //@formatter:off
    public interface PersonsListener {
        public void onResponse(List<Person> persons);
        public void onError(Exception error);
    }
    //@formatter:on

    /**
     * Alternative constructor if the user does not want to create a separate API client.
     *
     * @param context Application context
     * @param baseUrl Base URL of the API. For example: "https://api.example.com/"
     */
    public APIWrapper(Context context, String baseUrl) {
        this(new APIClient(context, baseUrl));
    }

    /**
     * Constructor with an API client. If you don't want to create a separate API client, you can use the other constructor.
     *
     * @param apiClient
     */
    public APIWrapper(APIClient apiClient) {
        _apiClient = apiClient;
        _mapper = new ObjectMapper();
    }



    /**
     * Returns the API client used by the wrapper. Can be used to configure the client on-the-fly.
     *
     * @return The API client which the wrapper is using.
     */
    public APIClient getAPIClient() {
        return _apiClient;
    }

    /**
     * Returns every person in a list.
     *
     * @param personsListener Callback listener. If an error happened, onError is called. If the request was successful, onResponse is called with the result as
     *            the parameter.
     */
    public void getPersons(final PersonsListener personsListener) {

        _apiClient.get(PERSONS, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject result) {
                try {
                    // Jackson Objectmapper converts the JSON to the Java objects
                    JSONArray personsData = result.getJSONArray("data");
                    System.out.println(personsData.toString());
                    List<Person> persons = _mapper.readValue(personsData.toString(), new TypeReference<List<Person>>() {
                    });
                    personsListener.onResponse(persons);
                } catch (Exception e) {
                    personsListener.onError(e);
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                personsListener.onError(error);
            }
        });
    }

}
