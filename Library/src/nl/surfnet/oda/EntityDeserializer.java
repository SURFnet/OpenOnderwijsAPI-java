package nl.surfnet.oda;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    /**
     * GSON can throw an error, when converting a null JSONElement to a Boolean.
     *
     * @param element JSONElement which might be null
     * @return null if the data is null, otherwise the value
     */
    protected Boolean getAsBooleanNoNull(JsonElement element) {
        if (element == null || element.isJsonNull()) {
            return null;
        } else {
            return element.getAsBoolean();
        }
    }

    /**
     * GSON can throw an error, when converting a null JSONElement to a Date. Also, the date needs to be parsed from a String.
     *
     * @param element JSONElement which might be null
     * @return null if the data is null or can't be parsed, otherwise the value
     */
    protected Date getAsDateNoNull(JsonElement element) {
        if (element == null || element.isJsonNull()) {
            return null;
        } else {
            String dateString = element.getAsString();
            Date date;
            try {
                date = ISO8601.toDate(dateString);
            } catch (ParseException e) {
                return null;
            }
            return date;
        }
    }

    /**
     * GSON can throw an error, when converting a null JSONElement to a Date. Also, the date needs to be parsed from a String. A short date looks like this:
     * 2013-01-22
     *
     * @param element JSONElement which might be null
     * @return null if the data is null or can't be parsed, otherwise the value
     */
    protected Date getAsShortDateNoNull(JsonElement element) {
        if (element == null || element.isJsonNull()) {
            return null;
        } else {
            String dateString = element.getAsString();
            Date date;
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                date = dateFormat.parse(dateString);
            } catch (ParseException e) {
                return null;
            }
            return date;
        }
    }
}
