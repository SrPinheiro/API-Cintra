package com.cintra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Ignorar o caseSensitive URL
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive(false);
        configurer.setPathMatcher(matcher);
        new ThreadConfig().start();
    }

    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    //     registry.addMapping("/**")
    //     .allowedOrigins("http://srpinheiro.com:3000") // substitua pelo seu domínio
    //     .allowedMethods("GET", "POST", "PUT", "DELETE")
    //     .allowedHeaders("*");
    // }

    // @Bean
    // public CorsFilter corsFilter() {
    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     CorsConfiguration config = new CorsConfiguration();
    //     config.addAllowedOrigin("*");
    //     config.addAllowedMethod("*");
    //     config.addAllowedHeader("*");
    //     source.registerCorsConfiguration("/**", config);
    //     return new CorsFilter(source);
    // }
    

    private class ThreadConfig extends Thread{
        @Override
        public void run() {
            //
        }
    }

}