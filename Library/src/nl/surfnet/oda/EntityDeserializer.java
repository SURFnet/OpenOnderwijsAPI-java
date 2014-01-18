package nl.surfnet.oda;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

/**
 * Parent class for all entity deserializers. Has some methods for easier GSON conversion
 *
 * @author Daniel Zolnai
 *
 * @param <T> Type of entity, for example, Person.
 */
public abstract class EntityDeserializer<T> implements JsonDeserializer<T> {

    /**
     * GSON throws an error if a field is null and we try to convert it to a String. This method handles that.
     *
     * @param element JSONElement which might be null
     * @return null if the element is null, otherwise the value as a string
     */
    protected String getAsStringNoNull(JsonElement element) {
        if (element == null || element.isJsonNull()) {
            return null;
        } else {
            return element.getAsString();
        }
    }

    /**
     * GSON can throw an error when it has to convert a null field to an integer. This method fixes that.
     *
     * @param element JSON element (which may be null)
     * @return null if the element was null, otherwise the value
     */
    protected Integer getAsIntegerNoNull(JsonElement element) {
        if (element == null || element.isJsonNull()) {
            return null;
        } else {
            return element.getAsInt();
        }
    }

    /**
     * GSON can throw an error, when converting a null JSONElement to a Double.
     *
     * @param element JSONElement which might be null
     * @return null if the data is null, otherwise the value
     */
    protected Double getAsDoubleNoNull(JsonElement element) {
        if (element == null || element.isJsonNull()) {
            return null;
        } else {
            return element.getAsDouble();
        }
    }
}
