package eu.democo.demo.api;

import eu.democo.demo.service.TimeHandlerService;
import eu.democo.demo.util.Constants;
import eu.democo.demo.util.ResponseBuilder;
import eu.democo.demo.util.ResponseWrapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author Charan
 * @since 01 Sep 2019
 */
@RestController
@RequestMapping( value = "service",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE )
@Api( value = "Time Service", tags = { "Time Service" } )
@SwaggerDefinition( tags = {
        @Tag( name = "Time Service", description = "Provide Time for given time zone" )
} )
public class ServiceController
{
    private final TimeHandlerService handlerService;

    @Autowired
    public ServiceController( TimeHandlerService handlerService )
    {
        this.handlerService = handlerService;
    }

    @ApiOperation( value = "Get Time", notes = "Get current time for time zone - " + Constants.TIME_FORMAT, response = String.class, responseContainer = "ResponseWrapper" )
    @ApiResponses( value = { @ApiResponse( code = 200, message = "Get current time for time zone", response = String.class ) } )
    @RequestMapping( method = { RequestMethod.GET }, value = "time" )
    public ResponseEntity<ResponseWrapper<String>> getTime( @RequestParam( name = "timeZone" ) String timeZone )
    {
        return new ResponseBuilder<>( handlerService.getServerTime( timeZone ) )
                       .buildEntity();
    }
}
