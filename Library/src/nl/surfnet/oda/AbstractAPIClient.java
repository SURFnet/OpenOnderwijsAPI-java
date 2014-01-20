package nl.surfnet.oda;

import java.util.HashMap;
import java.util.Map.Entry;

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

        // add additional parameters here
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
     * @param id Unique identifier of the object.
     * @param params Parameters of the query. Use null if none.
     * @param handler Callback on success or failure.
     */
    public abstract void get(String id, Params params, EntityHandler<T> handler);

    /**
     * Used for fetching lists from the API.
     *
     * @param params Parameters of the query. Use null if none.
     * @param handler Callback on success or failure.
     */
    public abstract void getList(Params params, ListHandler<T> handler);

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
