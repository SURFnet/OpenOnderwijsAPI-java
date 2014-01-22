package nl.surfnet.oda;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TimeZone;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public abstract class AbstractAPIClient<T> {

    public static class Params extends HashMap<String, String> {

        // serialversion UID for serialization.
        private static final long serialVersionUID = 1L;

        /**
         * Sets the page number for the listing. Use only with getList methods.
         *
         * @param pageNumber Number of the page
         * @return the instance of the actual Params object, allows inline creation and modification
         */
        public Params setPage(Integer pageNumber) {
            put("page", pageNumber.toString());
            return this;
        }

        /**
         * Sets the starting date for the listing. Used for example at schedules.
         *
         * @param date Starting date
         * @return Parameters with the added parameter
         */
        public Params setStartDate(Date date) {
            String endString = _convertDateToString(date);
            put("start", endString);
            return this;
        }

        /**
         * Sets the ending date for the listing. Used for example at schedules.
         *
         * @param date Ending date
         * @return Parameters with the added parameter
         */
        public Params setEndDate(Date date) {
            String endString = _convertDateToString(date);
            put("end", endString);
            return this;
        }

        // add additional parameters here

        private String _convertDateToString(Date date) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return (dateFormat.format(date) + "UTC");
        }

    }

    private String _baseUrl;

    public AbstractAPIClient(String url) {
        _baseUrl = url;
    }

    /**
     * Returns a GsonConverter, which can be used by retrofit. Create a GSON with a builder, then use GsonConverter(GSON).
     *
     * @return An object, which converts JSON to the correct Java objects.
     */
    protected abstract GsonConverter getGsonConverter();

    /**
     * Used for fetching single objects from the API
     * 
     * @param url Resource locator of the object.
     * @param params Parameters of the query. Use null if none.
     * @param handler Callback on success or failure.
     */
    public abstract void get(String url, Params params, EntityHandler<T> handler);

    /**
     * Used for fetching lists from the API.
     *
     * @param params Parameters of the query. Use null if none.
     * @param handler Callback on success or failure.
     */
    public abstract void getList(Params params, ListHandler<T> handler);


    /**
     * Used for fetching objects from the API using their ID's
     *
     * @param id ID of the object. Use entity.getId() to get the id of the object.
     * @param params Parameters of the query. Use null if none.
     * @param handler Callback on success or failure.
     */
    public void getById(String id, Params params, EntityHandler<T> handler){
        String url = _baseUrl;
        if (!_baseUrl.endsWith("/")) {
            url += "/";
        }
        url += getEndpoint() + "/" + id;
        get(url, params, handler);
    }

    /**
     * Returns the endpoint as a string.
     *
     * @return The endpoint as a string, without the base
     */
    protected abstract String getEndpoint();

    /**
     * Resolves an URL to the endpoint version
     *
     * @param url The absolute url.
     * @return The url which is relative to the base url.
     */
    protected String resolveUrl(String url) {
        if (url.startsWith(_baseUrl)) {
            return url.substring(_baseUrl.length());
        } else {
            try {
            URL uri = new URL(url);
            return uri.getFile();
            } catch (MalformedURLException e) {
                return null;
            }
        }
    }

    /**
     * Creates a Rest adapter for the communication between the API and the application.
     */
    protected RestAdapter getRestAdapter(String baseUrl) {
        //@formatter:off
        RestAdapter restAdapter = new RestAdapter.Builder()
        .setServer(baseUrl)
        .setConverter(getGsonConverter())
        .build();
        return restAdapter;
        //@formatter:on
    }

    /**
     * Converts the Params object to a query string
     *
     * @param params parameters of the request
     * @return the parameters concatenated to a string.
     */
    protected String parametersToString(Params params) {
        String result = "";
        params = addParametersAlways(params);
        if (params != null && params.size() > 0) {
            result = "?";
        } else {
            return result;
        }
        int count = 0;
        for (Entry<String, String> entry : params.entrySet()) {
            result += entry.getKey() + "=" + entry.getValue();
            count++;
            if (count < params.size()) {
                result += "&";
            }
        }
        return result;

    }

    /**
     * These parameters are always added to the queries.
     *
     * @param params Parameters to append to.
     * @return The Params object with the needed parameters.
     */
    private Params addParametersAlways(Params params) {
        if (params == null) {
            params = new Params();
        }
        // the format should be always json.
        params.put("format", "json");
        // add additional parameters here, which you want to add to each query
        return params;
    }
}
