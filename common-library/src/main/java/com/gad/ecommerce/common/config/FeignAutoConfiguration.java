package com.gad.ecommerce.common.config;

import com.gad.ecommerce.common.logging.FeignClientInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

@Slf4j
@AutoConfiguration
@ConditionalOnClass(name = "feign.RequestInterceptor")
public class FeignAutoConfiguration {
    public FeignAutoConfiguration() {
        log.info("Feign auto-configuration activated");
    }

    @Bean
    public FeignClientInterceptor feignClientInterceptor() {
        log.info("Registering FeignClientInterceptor");
        return new FeignClientInterceptor();
    }
}
