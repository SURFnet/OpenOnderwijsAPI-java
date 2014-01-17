package nl.surfnet.oda;

import java.util.List;

/**
 * Provides a generic class for all API methods which return a list of objects.
 *
 * @author Daniel Zolnai
 *
 * @param <T> Type of object the list contains
 */
public abstract class ListHandler<T> {

    /**
     * If the API call finished without an error, call this method with the result as a parameter.
     *
     * @param list
     */
    public abstract void success(List<T> list);

    /**
     * If there was an error, call this with the exception
     *
     * @param e NetworkError containing the details of the problem.
     */
    public abstract void failure(NetworkError e);
}