package org.suliga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TrantorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrantorApplication.class, args);
	}
	
    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("greetings")
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.regex("/greeting.*"))
                .build();
    }
     
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Trantor Swagger Title")
                .description("Trantor Swagger Desc")
                .termsOfServiceUrl("http://termsofservice.com")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/tomsuliga/trantor/master/LICENSE")
                .version("2.0")
                .build();
    }
}
