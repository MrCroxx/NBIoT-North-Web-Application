package com.croxx.nbiot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestHeader;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Value("${project.version}")
    private String version;

    @Bean
    public Docket restfulApi() {

        List<Parameter> pars = new ArrayList<>();
        pars.add(
                new ParameterBuilder().name("Authorization").description("JWT鉴权字段(`/jwt/auth`与`/nbiot/**`路由不需要此字段)")
                        .modelRef(new ModelRef("string")).parameterType("header")
                        .required(true).build()
        );

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.croxx.nbiot"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);

        /*

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.croxx.nbiot"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
         */

        /*
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.croxx.nbiot"))
                .paths(PathSelectors.regex("^(?!/jwt/auth)$)"))
                .build()
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.croxx.nbiot"))
                .paths(PathSelectors.ant("/jwt/auth"))
                .build()
                .globalOperationParameters(pars);
        */
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("《NBIoT一键报警系统北向Web App》API文档")
                .description("假装这是一条描述")
                .termsOfServiceUrl("服务条款URL")
                .contact("Croxx")
                .version(version)
                .build();
    }
}