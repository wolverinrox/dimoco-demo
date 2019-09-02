package eu.democo.demo.api;

import eu.democo.demo.service.TimeHandlerService;
import eu.democo.demo.util.APIResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author Charan
 * @since 02 Sep 2019
 */
@ExtendWith( SpringExtension.class )
public class EndPointTest
{
    @InjectMocks
    ServiceController serviceController;

    @Mock
    private TimeHandlerService handlerService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup()
    {
        APIResponse<String> apiResponse = new APIResponse<>();
        apiResponse.setData( "TST" );
        Mockito.doReturn( apiResponse ).when( handlerService ).getServerTime( ArgumentMatchers.eq( "Asia/Colombo" ) );

        MockitoAnnotations.initMocks( this );
        this.mockMvc = MockMvcBuilders.standaloneSetup( serviceController ).build();
    }

    @Test
    void endPointTest() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                                 .get( "/service/time" )
                                 .accept( MediaType.APPLICATION_JSON )
                                 .param( "timeZone", "Asia/Colombo" )
                                 .header( "Content-Type", "application/json" ) )
               .andDo( print() )
               .andExpect( status().isOk() )
               .andExpect( jsonPath( "$.status" ).exists() )
               .andExpect( jsonPath( "$.data" ).isNotEmpty() );
    }

}
