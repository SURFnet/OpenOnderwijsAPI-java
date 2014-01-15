package nl.surfnet.oda;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Provides methods for easier usage of the Surfnet OnderwijsData API client.
 * 
 * @author Daniel Zolnai
 * 
 */
public class APIUtils {

    /**
     * Converts a list of NameValuePairs to a JSONObject which can be added to a request as a parameter
     *
     * @param params Parameters in a List<NameValuePair>.
     * @return Parsed JSONObject
     * @throws JSONException If there's something wrong with the parsing, a JSONException can be thrown
     */
    public static JSONObject parametersListToJSON(List<NameValuePair> params) throws JSONException {
        JSONObject root = new JSONObject();
        // for nulls or empty lists it returns an empty json
        if (params == null || params.size() == 0) {
            return root;
        }
        for (NameValuePair pair : params) {
            root.put(pair.getName(), pair.getValue());
        }
        return root;
    }

    /**
     * Converts a map of key-values to a JSON object which can be added to requests as a parameter
     *
     * @param params Parameters in a Map<String,String>
     * @return Parsed JSONObject
     * @throws JSONException If there's something wrong with the parsing, a JSONException can be thrown
     */
    public static JSONObject parametersMapToJSON(Map<String, String> params) throws JSONException {
        JSONObject root = new JSONObject();
        // for nulls or empty maps it returns an empty json
        if (params == null || params.size() == 0) {
            return root;
        }
        for (Entry<String, String> entry : params.entrySet()) {
            root.put(entry.getKey(), entry.getValue());
        }
        return root;
    }

}
