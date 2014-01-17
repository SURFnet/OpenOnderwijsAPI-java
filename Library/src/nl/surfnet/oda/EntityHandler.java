package nl.surfnet.oda;


/**
 * Provides a generic class for all API methods which return an instance of an object.
 *
 * @author Daniel Zolnai
 *
 * @param <T> Type of the object. For example, Person
 */
public abstract class EntityHandler<T> {

    /**
     * If the API call finished without an error, call this method with the result as a parameter.
     *
     * @param result The result object
     */
    public abstract void success(T result);

    /**
     * If there was an error, call this with the exception
     *
     * @param e NetworkError object containing the details of the error.
     */
    public abstract void failure(NetworkError e);
}