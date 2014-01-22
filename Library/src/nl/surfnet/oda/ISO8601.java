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
     * Transform Date to ISO 8601 string.
     */
    public static String fromDate(final Date date) {
        String formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(date);
        return formatted.substring(0, 26) + ":" + formatted.substring(26);
    }

    /**
     * Get current date and time formatted as ISO 8601 string.
     */
    public static String now() {
        return fromDate(GregorianCalendar.getInstance().getTime());
    }

    /**
     * Transform ISO 8601 string to a java Date.
     */
    public static Date toDate(final String iso8601string) throws ParseException {
        String s = iso8601string.replace("Z", "+00:00");
        if (iso8601string.length() == 20) {
            try {
                s = s.substring(0, 22) + s.substring(23);
            } catch (IndexOutOfBoundsException e) {
                throw new ParseException("Invalid length", 0);
            }
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(s);
        } else {
            try {
                s = s.substring(0, 25) + s.substring(26);
            } catch (IndexOutOfBoundsException e) {
                throw new ParseException("Invalid length", 0);
            }
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse(s);
        }
    }
}
