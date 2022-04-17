package com.inepp.security.service.config;

import com.inepp.security.service.interceptor.AuthorityInterceptor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class WebSecurityMvcConfiguration implements WebMvcConfigurer {

    @Bean
    public AuthorityInterceptor authorityInterceptor(){
        return new AuthorityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorityInterceptor())
                .addPathPatterns("/api/residents/**")
                .excludePathPatterns("/api/residents/login")
                .excludePathPatterns("/api/residents/registry");

    }


        @Override
        public void addFormatters(FormatterRegistry registry) {
            registry.addConverter(new Converter<String, Date>() {
                @Override
                @SneakyThrows  //自动插入try...catch(lombok的插件)
                public Date convert(String source) {
                    if(source==null || "".equals(source)){
                        return new Date();
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    return  sdf.parse(source);
                }
            });
        }
    }

