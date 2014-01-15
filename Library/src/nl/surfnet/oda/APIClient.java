package nl.surfnet.oda;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

/**
 * A Google Volley based API client for accessing API's built on Surfnet OnderwijsData.
 *
 * @author Daniel Zolnai
 *
 */
public class APIClient {

    private final static String FORMAT = "?format=json";
    private String _baseUrl;
    private RequestQueue _requestQueue;

    /**
     * Creates a new API client.
     *
     * @param context Context of the application. For example an Activity
     * @param baseUrl The URL of the API. E.g. "https://api.example.com/".
     */
    public APIClient(Context context, String baseUrl) {
        _baseUrl = baseUrl;
        // if user forgot the slash on the end, append it
        if (!_baseUrl.endsWith("/")) {
            _baseUrl += "/";
        }
        _requestQueue = Volley.newRequestQueue(context);
    }

    /**
     * Returns a resource from the API using a GET method. Use this when you want to parse the data your own way, or you need to access resources, which are not
     * available through the API client.
     *
     * @param path Path to the resource. Use the path to append additional parameters to the request. Example: "groups/2".
     * @param responseListener Callback for the response. The listener is called when there's a response.
     * @param errorListener Callback for errors.
     */
    public void get(String path, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        String url = _createRequestUrl(path);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, responseListener, errorListener) {
            // Most authentication frameworks need to add additional headers to each request
            @Override
            public java.util.Map<String, String> getHeaders() throws com.android.volley.AuthFailureError {
                return _addHeaders();
            };
        };
        _requestQueue.add(request);
    }


    /**
     * Performs a POST method to send data to the API.
     *
     * @param path The path the request should be sent to.
     * @param params Parameters of the request in a JSONObject. Use APIUtils.parametersListToJSON( ) to convert a list to compatible JSON.
     * @param responseListener Response callback.
     * @param errorListener Error callback.
     */
    public void post(String path, JSONObject params, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        String url = _createRequestUrl(path);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, responseListener, errorListener) {
            // Most authentication frameworks need to add additional headers to each request
            @Override
            public java.util.Map<String, String> getHeaders() throws com.android.volley.AuthFailureError {
                return _addHeaders();
            };
        };
        _requestQueue.add(request);
    }

    /**
     * Makes a full url from the path string using the base url. Adds the format to it, ensuring that the response will be a JSON object.
     * 
     * @param path path to the resource, like "buildings/1/".
     * @return the full URL, like "https://api.company.com/buildings/1/".
     */
    private String _createRequestUrl(String path) {
        return _baseUrl + path + FORMAT;
    }

    /**
     * If you need to add headers to each request (authorization headers, for example), then add them to the hashmap
     *
     * @return Map containing the headers. Used by the API methods
     */
    protected Map<String, String> _addHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        // add headers like this
        // headers.put("key", "value");
        return headers;
    }
}
