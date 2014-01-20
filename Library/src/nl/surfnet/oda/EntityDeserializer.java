package nl.surfnet.oda;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
     * Enables conversion between IS08601 type dates and strings
     *
     */
    protected static final class ISO8601 {
        /** Transform Calendar to ISO 8601 string. */
        public static String fromCalendar(final Calendar calendar) {
            Date date = calendar.getTime();
            String formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(date);
            return formatted.substring(0, 22) + ":" + formatted.substring(22);
        }

        /** Get current date and time formatted as ISO 8601 string. */
        public static String now() {
            return fromCalendar(GregorianCalendar.getInstance());
        }

        /** Transform ISO 8601 string to Calendar. */
        public static Calendar toCalendar(final String iso8601string) throws ParseException {
            Calendar calendar = GregorianCalendar.getInstance();
            String s = iso8601string.replace("Z", "+00:00");
            try {
                s = s.substring(0, 22) + s.substring(23);
            } catch (IndexOutOfBoundsException e) {
                throw new ParseException("Invalid length", 0);
            }
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse(s);
            calendar.setTime(date);
            return calendar;
        }
    };
}
