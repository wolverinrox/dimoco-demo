package eu.democo.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

import static com.google.common.collect.Lists.newArrayList;

/**
 * <b>Swagger Configuration</b>
 * Contains the configurations for swagger.
 *
 * @author Charan
 * @since 21 May 2019
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig
{
    @Bean
    public Docket swaggerDocket(){
        return new Docket( DocumentationType.SWAGGER_2)
                       .apiInfo(apiInfo())
                       .select().paths( PathSelectors.any())
                       .apis( RequestHandlerSelectors.basePackage("eu.democo.demo.api"))
                       .build()
                       .pathMapping("/")
                       .useDefaultResponseMessages(false)
                       .directModelSubstitute( LocalDate.class, String.class)
                       .genericModelSubstitutes( ResponseEntity.class);
    }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                       .title("API Reference")
                       .version("1.0.0")
                       .build();
    }
}
