package eu.democo.demo.service;

import eu.democo.demo.util.APIResponse;
import static org.junit.jupiter.api.Assertions.*;

import eu.democo.demo.util.Constants;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author Charan
 * @since 02 Sep 2019
 */
class TimeHandlerServiceTest
{
    private TimeHandlerService handlerService;

    @BeforeEach
    void init()
    {
        handlerService = new TimeHandlerService();
    }

    @Test
    void getServerTimeTest_WhenTimeZoneNull()
    {
        APIResponse<String> apiResponse = handlerService.getServerTime( null );

        assertTrue( apiResponse.isError() );
        assertNotNull( apiResponse.getMessage() );
    }

    @Test
    void getServerTimeTest_WhenTimeZoneEmpty()
    {
        APIResponse<String> apiResponse = handlerService.getServerTime( "" );

        assertTrue( apiResponse.isError() );
        assertNotNull( apiResponse.getMessage() );
    }

    @Test
    void getServerTimeTest_WhenTimeZoneInvalid()
    {
        APIResponse<String> apiResponse = handlerService.getServerTime( "TST" );

        assertTrue( apiResponse.isError() );
        assertNotNull( apiResponse.getMessage() );
        assertTrue( apiResponse.getMessage().contains( "TST" ) );
    }

    @Test
    void getServerTimeTest_WhenSuccess()
    {
        APIResponse<String> apiResponse = handlerService.getServerTime( "Asia/Colombo" );

        assertFalse( apiResponse.isError() );

        DateTimeFormatter inputFormat = DateTimeFormat.forPattern( Constants.TIME_FORMAT );

        assertDoesNotThrow( () -> inputFormat.parseDateTime( apiResponse.getData() ) );
    }
}