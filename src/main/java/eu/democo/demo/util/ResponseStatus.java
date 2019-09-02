package eu.democo.demo.util;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author Charan
 * @since 22 May 2019
 */
public enum ResponseStatus
{
    SUCCESS( 1, "SUCCESS" ),
    ERROR( -1, "ERROR" ),
    WARNING( 0, "WARNING" ),
    NO_ACCESS( 2, "NO_ACCESS" );

    private long no;
    private String message;

    ResponseStatus( long no, String message )
    {
        this.no = no;
        this.message = message;
    }

    public long getNo()
    {
        return no;
    }

    public String getMessage()
    {
        return message;
    }
}
