package com.gad.ecommerce.common.config;

import com.gad.ecommerce.common.logging.FeignClientInterceptor;
import com.gad.ecommerce.common.logging.MdcFilter;
import com.gad.ecommerce.common.security.CurrentUserIdArgumentResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Slf4j
@AutoConfiguration
@ConditionalOnClass(WebMvcConfigurer.class)
public class CommonAutoConfiguration implements WebMvcConfigurer {
    public CommonAutoConfiguration() {
        log.info("Common library auto-configuration activated");
    }

    @Bean
    public CurrentUserIdArgumentResolver currentUserIdArgumentResolver() {
        log.info("Registering CurrentUserIdArgumentResolver");
        return new CurrentUserIdArgumentResolver();
    }

    @Bean
    public FilterRegistrationBean<MdcFilter> mdcFilter() {
        log.info("Registering MdcFilter");
        FilterRegistrationBean<MdcFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MdcFilter());
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentUserIdArgumentResolver());
    }
}
