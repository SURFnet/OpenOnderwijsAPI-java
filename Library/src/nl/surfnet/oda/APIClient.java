package nl.surfnet.oda;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
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
public abstract class APIClient {

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
     * Returns a resource from the API using a GET method.
     *
     * @param path Path to the resource. Use the path to append additional parameters to the request. Example: "groups/2".
     * @param responseListener Callback for the response. The listener is called when there's a response.
     * @param errorListener Callback for errors.
     */
    protected void get(String path, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
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
    protected void post(String path, JSONObject params, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
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
     * Makes a full url from the path string using the base url.
     *
     * @param path path to the resource, like "buildings/1/".
     * @return the full URL, like "https://api.company.com/buildings/1/".
     */
    private String _createRequestUrl(String path) {
        return _baseUrl + path;
    }

    /**
     * If you need to add headers to each request (authorization headers, for example), then add them to the map.
     *
     * @return Map containing the headers. Used by the API methods
     */
    protected Map<String, String> _addHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        // add headers like this.
        // ensures that the formatting is json.
        headers.put("Content-Type", "application/json");
        return headers;
    }

    /**
     * Adds the URL parameters to the path. You can convert Maps to List<NameValuePairs> using the APIUtils
     * 
     * @param path resource path
     * @param params URL parameters
     * @return path with the parameters appended at the end
     */
    protected String appendParameters(String path, List<NameValuePair> params) {
        if (params == null || params.size() == 0) {
            return path;
        }
        path += "?";
        // go through the list, and append the items one-by-one
        for (int i = 0; i < params.size(); ++i) {
            NameValuePair pair = params.get(i);
            path += pair.getName() + "=" + pair.getValue();
            if (i != params.size() - 1) {
                path += "&";
            }
        }
        return path;
    }
}
