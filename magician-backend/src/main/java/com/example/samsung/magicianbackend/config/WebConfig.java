package com.example.samsung.magicianbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{

//	@Bean
//	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
//		return new RequestMappingHandlerMapping() {
//			private final static String API_BASE_PATH = "api";
//			
//			@Override
//			protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
//				Class<?> beanType = method.getDeclaringClass();
//				if (AnnotationUtils.findAnnotation(beanType, RestController.class) != null) {
//					PatternsRequestCondition apiPattern = new PatternsRequestCondition(API_BASE_PATH)
//							.combine(mapping.getPatternsCondition());
//					mapping = new RequestMappingInfo(mapping.getName(), apiPattern,
//                            mapping.getMethodsCondition(), mapping.getParamsCondition(),
//                            mapping.getHeadersCondition(), mapping.getConsumesCondition(),
//                            mapping.getProducesCondition(), mapping.getCustomCondition());
//				}
//				super.registerHandlerMethod(handler, method, mapping);
//			}
//		};
//	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedMethods(
						HttpMethod.GET.name(),
						HttpMethod.PUT.name(),
						HttpMethod.DELETE.name(),
						HttpMethod.PATCH.name(),
						HttpMethod.HEAD.name());
	}
//	@Override
//	public void addResourceHandler(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/docs/**").addResourceLocations("classpath:/static/docs");
//	}
}
