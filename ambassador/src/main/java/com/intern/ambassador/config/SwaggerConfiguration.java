package com.intern.ambassador.config;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.intern.ambassador"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("앰배서더 API 테스트 페이지")
                .description("앰배서더 교육 & 활동 신청 Swagger 페이지입니다.")
                .version("1.0.0")
                .build();
    }

    @ApiOperation(value = "Ambassador 신청서 메서드", notes = "Ambassador 신청서 예제")
    @GetMapping(value = "/ambassador-application")
    public String getAmbassadorApplicationParam(
            @ApiParam(value = "지원동기", required = true) @RequestParam String reason,
            @ApiParam(value = "피드백을 받는다면", required = true) @RequestParam String feedback,
            @ApiParam(value = "활동", required = true) @RequestParam String activity,
            @ApiParam(value = "장점", required = true) @RequestParam String advantage,
            @ApiParam(value = "마지막 한 마디", required = true) @RequestParam String lastWord) {
        return reason + ' ' + feedback + ' ' + activity + ' ' + advantage + ' ' + lastWord;
    }


}
