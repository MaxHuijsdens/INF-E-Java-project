/*
 * (C) 2013 42 bv (www.42.nl). All rights reserved.
 */
package nl.koekoek;

import static org.springframework.http.converter.json.MappingJackson2HttpMessageConverter.DEFAULT_CHARSET;

import java.util.List;

import nl.koekoek.support.CustomObjectMapper;
import nl.koekoek.support.GenericExceptionResolver;
import nl.koekoek.support.StringToGraphTypeConverter;
import nl.koekoek.support.StringToLocalDateTimeConverter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 *
 * @author jeroen
 * @since Aug 22, 2014
 */
@EnableWebMvc
@Configuration
@ComponentScan(
        basePackageClasses = ApplicationConfig.class,
        includeFilters = @Filter(Controller.class),
        excludeFilters = @Filter({ Configuration.class, Service.class }))
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**").addResourceLocations("/");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new StringHttpMessageConverter(DEFAULT_CHARSET));
        converters.add(mappingJacksonHttpMessageConverter());
    }

    private MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new CustomObjectMapper());
        return converter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToLocalDateTimeConverter());
        registry.addConverter(new StringToGraphTypeConverter());
    }

    @Bean
    public DomainClassConverter<DefaultFormattingConversionService> domainClassConverter(DefaultFormattingConversionService conversionService) {
        return new DomainClassConverter<>(conversionService);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
    }

    @Bean
    public GenericExceptionResolver genericExceptionResolver() {
        return new GenericExceptionResolver();
    }

}
