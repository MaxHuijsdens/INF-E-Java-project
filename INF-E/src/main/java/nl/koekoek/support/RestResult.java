package nl.koekoek.support;

/**
 * REST result wrapper of a value.
 * @author Kevin van Leeuwen
 * @param <T> the type of data
 */
public final class RestResult<T> {

    private final boolean successful;

    private String errors;

    private RestResult(boolean success, T result) {
        this.successful = success;
    }

    /**
     * Create a new successful REST result.
     * @param <T> the type of data
     * @param data the data to return
     * @return created REST result
     */
    public static <T> RestResult<T> success(T data) {
        return new RestResult<T>(true, data);
    }

    /**
     * Create a failed REST result.
     * @param <T> the type of data
     * @param error the error message to return
     * @return created REST result
     */
    public static <T> RestResult<T> error(String error) {
        RestResult<T> result = new RestResult<T>(false, null);
        result.errors = error;
        return result;
    }

    /**
     * Determine if the result was retrieved successful.
     * @return {@code true} when success, otherwise {@code false}
     */
    public boolean isSuccess() {
        return successful;
    }

    /**
     * Return the error message, when not successful.
     * @return the error message
     */
    public String getError() {
        return errors;
    }

}
