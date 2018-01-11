package com.alphaz.core.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.util
 * User: C0dEr
 * Date: 2016-11-10
 * Time: 10:51
 * Description:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("alphaz  Api")
                .description("所有api统一返回{\"state\":0,\"data\":null,\"message\":null},statusCode为业务状态0为成功，1为失败，message为具体描述，data存放具体返回值，会在api中Response里面具体描述\n" +
                        "\n")
                .version("V1.0.0")
                .build();
    }


}
