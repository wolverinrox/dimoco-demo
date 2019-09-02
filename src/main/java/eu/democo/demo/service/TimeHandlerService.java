package eu.democo.demo.service;

import com.google.common.base.Strings;
import eu.democo.demo.util.APIResponse;
import eu.democo.demo.util.Constants;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author Charan
 * @since 01 Sep 2019
 */
@Service
public class TimeHandlerService
{
    private static final Logger logger = LogManager.getLogger( TimeHandlerService.class );

    public APIResponse<String> getServerTime( String timeZone)
    {
        APIResponse<String> apiResponse = new APIResponse<>();

        if( !Strings.isNullOrEmpty( timeZone ) )
        {
            try
            {
                DateTimeZone dateTimeZone = DateTimeZone.forID( timeZone );
                DateTime now = DateTime.now( dateTimeZone );

                DateTimeFormatter outputFormat = DateTimeFormat.forPattern( Constants.TIME_FORMAT );

                apiResponse.setData( outputFormat.print( now ) );
            }
            catch( IllegalArgumentException ex )
            {
                String error = MessageFormat.format( "Invalid time zone: {0}", timeZone );
                logger.log( Level.ERROR, error, ex );
                apiResponse.setError( true );
                apiResponse.setMessage( error );
            }
        }
        else
        {
            apiResponse.setError( true );
            apiResponse.setMessage( "Timezone is required" );
        }

        return apiResponse;
    }
}
