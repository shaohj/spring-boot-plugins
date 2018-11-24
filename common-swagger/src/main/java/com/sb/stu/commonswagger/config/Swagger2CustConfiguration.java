package com.sb.stu.commonswagger.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 编  号：
 * 名  称：Swagger2CustConfiguration
 * 描  述：
 * 完成日期：2018/11/24 14:55
 * @author：felix.shao
 */
@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class Swagger2CustConfiguration {

	/** 排除Spring Boot默认的错误处理路径和端点 */
	private static final List<String> DEFAULT_EXCLUDE_PATH = Arrays.asList("/error");

	private static final String BASE_PATH = "/**";

	@Bean
	@ConditionalOnMissingBean
	public Swagger2Properties swaggerProperties() {
		return new Swagger2Properties();
	}

	@Bean
	public Docket api(Swagger2Properties swaggerProperties) {
		if (swaggerProperties.getBasePath().isEmpty()) {
			swaggerProperties.getBasePath().add(BASE_PATH);
		}
		List<Predicate<String>> basePath = new ArrayList();
		swaggerProperties.getBasePath().forEach(path -> basePath.add(PathSelectors.ant(path)));

		if (swaggerProperties.getExcludePath().isEmpty()) {
			swaggerProperties.getExcludePath().addAll(DEFAULT_EXCLUDE_PATH);
		}
		List<Predicate<String>> excludePath = new ArrayList<>();
		swaggerProperties.getExcludePath().forEach(path -> excludePath.add(PathSelectors.ant(path)));

		return new Docket(DocumentationType.SWAGGER_2)
			.host(swaggerProperties.getHost())
			.apiInfo(apiInfo(swaggerProperties)).select()
			.apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
			.paths(Predicates.and(Predicates.not(Predicates.or(excludePath)), Predicates.or(basePath)))
			.build()
			.pathMapping("/");
	}

	private ApiInfo apiInfo(Swagger2Properties swaggerProperties) {
		return new ApiInfoBuilder()
			.title(swaggerProperties.getTitle())
			.description(swaggerProperties.getDescription())
			.license(swaggerProperties.getLicense())
			.licenseUrl(swaggerProperties.getLicenseUrl())
			.contact(new Contact(swaggerProperties.getContact().getName(), swaggerProperties.getContact().getUrl(), swaggerProperties.getContact().getEmail()))
			.version(swaggerProperties.getVersion())
			.build();
	}

}
