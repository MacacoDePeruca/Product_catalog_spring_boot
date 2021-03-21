package com.br.vitornascimento.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	
	public void addCorsMapping(CorsRegistry registry) {
		
		registry.addMapping("/**")
		.allowedMethods("GET","POST","PUT","DELETE","PATCH","OPTION","HEAD","TRACE","CONNECT");
	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		
		
		//VIA EXTENSION
		/*
		 * configurer.favorParameter(false) .ignoreAcceptHeader(false)
		 * .defaultContentType(MediaType.APPLICATION_JSON) .mediaType("json",
		 * MediaType.APPLICATION_JSON) .mediaType("xml", MediaType.APPLICATION_XML);
		 */
		

		// VIA query param
//		configurer
//		.parameterName("mediaType")
//		.useRegisteredExtensionsOnly(false)
//		.favorPathExtension(false)
//		.favorParameter(true)
//		.ignoreAcceptHeader(false)
//		.defaultContentType(MediaType.APPLICATION_JSON)
//		.mediaType("json", MediaType.APPLICATION_JSON)
//		.mediaType("xml", MediaType.APPLICATION_XML);
		
		
		configurer
		.useRegisteredExtensionsOnly(false)
		.favorPathExtension(false)
		.favorParameter(false)
		.ignoreAcceptHeader(false)
		.defaultContentType(MediaType.APPLICATION_JSON)
		.mediaType("json", MediaType.APPLICATION_JSON)
		.mediaType("xml", MediaType.APPLICATION_XML);
	}
}
