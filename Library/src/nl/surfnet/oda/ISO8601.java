package nl.surfnet.oda;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Enables conversion between IS08601 type dates and strings
 *
 */
public class ISO8601 {

    /**
     * Transforms a date to an ISO8601 format String
     *
     * @param date The date to convert.
     * @return The formatted string
     */
    public static String fromDate(final Date date) {
        String formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(date);
        return formatted.substring(0, 26) + ":" + formatted.substring(26);
    }

    /**
     * Returns the current time as an ISO8601 formatted date
     *
     * @return Current time as an ISO8601 String.
     */
    public static String now() {
        return fromDate(GregorianCalendar.getInstance().getTime());
    }

    /**
     * Converts an ISO8601 format String to a Java Date object.
     *
     * @param iso8601string A string with a date in ISO8601 format.
     * @return The date of the String in a Java Date object
     * @throws ParseException If the date can not be parsed, a ParseException will be thrown.
     */
    public static Date toDate(final String iso8601string) throws ParseException {
        String s = iso8601string.replace("Z", "+00:00");
        // if the string does not have the microseconds field
        if (iso8601string.length() == 20) {
            try {
                s = s.substring(0, 22) + s.substring(23);
            } catch (IndexOutOfBoundsException e) {
                throw new ParseException("Invalid length", 0);
            }
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(s);
        } else {
            // if the string has the microseconds field
            try {
                s = s.substring(0, 25) + s.substring(26);
            } catch (IndexOutOfBoundsException e) {
                throw new ParseException("Invalid length", 0);
            }
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse(s);
        }
    }
}
