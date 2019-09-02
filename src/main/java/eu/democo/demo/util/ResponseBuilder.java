package eu.democo.demo.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author Charan
 * @since 21 May 2019
 */
public class ResponseBuilder<T>
{
    private ResponseWrapper<T> response;

    public ResponseBuilder()
    {
        this.response = new ResponseWrapper<>();
        this.response.setStatus( ResponseStatus.ERROR );
    }

    public ResponseBuilder( T data )
    {
        this();
        this.response.setData( data );
    }

    public ResponseBuilder( APIResponse<T> data )
    {
        this();
        if( data.getData() != null ) this.response.setData( data.getData() );
        this.response.setError( data.isError() ? data.getMessage() : null );
        this.response.setStatus( !data.isError() ? ResponseStatus.SUCCESS : ResponseStatus.ERROR );

    }

    public ResponseBuilder<T> withStatus( ResponseStatus responseStatus )
    {
        this.response.setStatus( responseStatus );
        return this;
    }

    public ResponseBuilder<T> withData( T data )
    {
        this.response.setData( data );
        return this;
    }

    public ResponseWrapper<T> build()
    {
        this.response.setVersion( "V1.0" );
        return this.response;
    }

    public ResponseEntity<ResponseWrapper<T>> buildEntity()
    {

        this.response.setVersion( "V1.0" );

        ResponseEntity<ResponseWrapper<T>> responseEntity;

        if( this.response.getError() == null )
        {
            responseEntity = new ResponseEntity<>( this.response, HttpStatus.OK );
        }
        else
        {
            responseEntity = new ResponseEntity<>( this.response, HttpStatus.BAD_REQUEST );
        }

        return responseEntity;
    }

}
