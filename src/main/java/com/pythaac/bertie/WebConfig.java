package com.pythaac.bertie;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        CacheControl cacheControl = CacheControl
                .maxAge(60, TimeUnit.SECONDS)
                .mustRevalidate();

        registry.addResourceHandler("/")
                .addResourceLocations("classpath:/templates/")
                .setCacheControl(cacheControl);
    }
}
