package eu.democo.demo.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author Charan
 * @since 21 May 2019
 */
@JsonInclude( JsonInclude.Include.NON_NULL )
@JsonPropertyOrder( { "status", "error", "message", "data", "version" } )
public class ResponseWrapper<T>
{
    @JsonProperty( "version" )
    private String version;
    @JsonProperty( "status" )
    private ResponseWrapper<T>.Status status;
    @JsonProperty( "error" )
    private String error;
    @JsonProperty( "data" )
    private T data;

    public String getVersion()
    {
        return version;
    }

    public void setVersion( String version )
    {
        this.version = version;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus( ResponseStatus responseStatus )
    {
        Status status = new Status();
        status.setCode( responseStatus.getNo() );
        status.setMessage( responseStatus.getMessage() );
        this.status = status;
    }

    public void setStatus( Status status )
    {
        this.status = status;
    }

    public T getData()
    {
        return data;
    }

    public void setData( T data )
    {
        this.data = data;
    }

    public String getError()
    {
        return error;
    }

    public void setError( String error )
    {
        this.error = error;
    }

    class Status
    {
        private long code;
        private String message;

        public long getCode()
        {
            return this.code;
        }

        public void setCode( long code )
        {
            this.code = code;
        }

        public String getMessage()
        {
            return this.message;
        }

        public void setMessage( String message )
        {
            this.message = message;
        }
    }
}
