package com.zero.common.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author herenpeng
 * @since 2020-09-27 20:12
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zero"))
                // .paths(PathSelectors.any())
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 标题信息
                .title("Zero-Admin通用后台管理系统接口文档")
                // 描述信息
                .description("该篇接口文档是关于zero-admin通用后台管理系统的接口说明文档，对于该系统中的所有接口进行了详细的描述！")
                // 系统版本
                .version("1.0.0")
                .contact(new Contact("何任鹏", "https://herenpeng.github.io", "he.ren.peng@qq.com"))
                .license("前往系统")
                .licenseUrl("https://www.baidu.com/")
                .build();
    }

}
