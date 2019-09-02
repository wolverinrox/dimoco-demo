package eu.democo.demo.util;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author Charan
 * @since 25 May 2019
 */
public class APIResponse<T>
{
    private boolean isError;
    private String message;
    private T data;

    public T getData()
    {
        return data;
    }

    public void setData( T data )
    {
        this.data = data;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

    public boolean isError()
    {
        return isError;
    }

    public void setError( boolean error )
    {
        isError = error;
    }
}
