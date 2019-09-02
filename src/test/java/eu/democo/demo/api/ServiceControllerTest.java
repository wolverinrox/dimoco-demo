package eu.democo.demo.api;

import eu.democo.demo.service.TimeHandlerService;
import eu.democo.demo.util.APIResponse;
import eu.democo.demo.util.ResponseWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author Charan
 * @since 02 Sep 2019
 */
class ServiceControllerTest
{
    private TimeHandlerService handlerService;
    private ServiceController serviceController;

    @BeforeEach
    void init()
    {
        handlerService = mock( TimeHandlerService.class );
        serviceController = new ServiceController( handlerService );
    }

    @Test
    void getTimeSuccessTest()
    {
        String timeZone = "Asia/Colombo";

        doAnswer( invocationOnMock ->
        {
            String zone = ( String ) invocationOnMock.getArguments()[0];
            assertEquals( timeZone, zone );

            APIResponse<String> apiResponse = new APIResponse<>();
            apiResponse.setData( "TST" );
            return apiResponse;
        } ).when( handlerService ).getServerTime( anyString() );

        ResponseEntity<ResponseWrapper<String>> responseEntity = serviceController.getTime( timeZone );

        assertNotNull( responseEntity );
        assertEquals( HttpStatus.OK, responseEntity.getStatusCode() );
        assertNotNull( responseEntity.getBody() );
        assertEquals( "TST", responseEntity.getBody().getData() );
    }

    @Test
    void getTimeFailTest()
    {
        String timeZone = "Asia/Colombo";

        APIResponse<String> apiResponse = new APIResponse<>();
        apiResponse.setError( true );
        apiResponse.setMessage( "TST" );

        doReturn( apiResponse ).when( handlerService ).getServerTime( anyString() );

        ResponseEntity<ResponseWrapper<String>> responseEntity = serviceController.getTime( timeZone );

        assertNotNull( responseEntity );
        assertEquals( HttpStatus.BAD_REQUEST, responseEntity.getStatusCode() );
        assertNotNull( responseEntity.getBody() );
        assertEquals( "TST", responseEntity.getBody().getError() );
    }
}