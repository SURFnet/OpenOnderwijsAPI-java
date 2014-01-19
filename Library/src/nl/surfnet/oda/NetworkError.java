package nl.surfnet.oda;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.RetrofitError;
import retrofit.client.Header;

/**
 * Encapsulates a RetrofitError, and converts it's data to more readable formats.
 *
 * @author Daniel Zolnai
 *
 */
public class NetworkError {

    private RetrofitError _error;

    public NetworkError(RetrofitError retrofitError) {
        _error = retrofitError;
    }

    /**
     * Returns the headers of the response.
     *
     * @return HTTP headers
     */
    public Map<String, String> getHeaders(){
        return _convertHeaders(_error.getResponse().getHeaders());
    }

    /**
     * Returns the HTTP status code of the response
     *
     * @return HTTP status code. For example, 404 or 500.
     */
    public int getStatusCode() {
        return _error.getResponse().getStatus();
    }

    /**
     * Get the body of the response
     *
     * It is the responsibility of the caller to close the stream. This method may only be called once.
     *
     * @return the body as an InputStream
     * @throws IOException
     */
    public InputStream getBody() throws IOException {
        return _error.getResponse().getBody().in();
    }

    /**
     * Prints the stack trace to the System.err channel.
     */
    public void printStackTrace() {
        _error.printStackTrace();
    }

    /**
     * The message of the error - may be null;
     *
     * @return
     */
    public String getMessage() {
        return _error.getMessage();
    }

    /**
     * Returns a short description about this Error.
     *
     */
    @Override
    public String toString() {
        return "ODA Network error: " + _error.toString();
    }

    /**
     * Converts retrofit headers to an easier readable format
     *
     * @param headers Retrofit Response header list
     * @return a hashmap of the headers
     */
    private Map<String, String> _convertHeaders(List<Header> headers) {
        Map<String, String> map = new HashMap<String, String>();
        if (headers == null) {
            return map;
        }
        // convert each header to a key - value pair, and add it to the map
        for (Header header : headers) {
            map.put(header.getName(), header.getValue());
        }
        return map;
    }
}
