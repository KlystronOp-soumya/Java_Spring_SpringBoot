package com.demo.hospitalapi.config.dev;

import static com.google.common.base.Predicates.or;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//@Configuration(value = "swaggerConfiguration" , proxyBeanMethods = false)
//@Profile("dev")
//@EnableSwagger2
public class SwaggerConfig {
	/*@Autowired
	private  javax.servlet.ServletContext servletContext;
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
				.apiInfo(apiInfo()).pathProvider(new springfox.documentation.spring.web.paths.RelativePathProvider(servletContext) {
	                  @Override
	                  protected String applicationPath() {
	                      return "/SimpleHospitalApi";
	                  }
	            }).select().paths(PathSelectors.ant("/api/*")).build() ;
	}
	
	@Deprecated
	private com.google.common.base.Predicate<String> postPaths() {
		final String path1 ="/api/*" ;
		com.google.common.base.Predicate<String> checkPathPredicate = ( String checkPath)->{
		 return checkPath.equals(path1) ;
			
 		} ;
		return or(PathSelectors.regex(path1), PathSelectors.regex(path1)) ;
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Simple Hospital API")
				.description("Simple Hospital Api For Spring Boot Developers")
				.termsOfServiceUrl("http://soumyascope.com")
				.contact("john.doe@gmail.com").license("Soumyadeep License")
				.licenseUrl("john.doe@gmail.com").version("1.0").build();
	}*/
}
