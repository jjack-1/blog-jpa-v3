package shop.mtcoding.blog._core.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import shop.mtcoding.blog._core.filter.AuthorizationFilter;
import shop.mtcoding.blog._core.filter.CorsFilter;
import shop.mtcoding.blog._core.filter.LogFilter;

@RequiredArgsConstructor
@Configuration
public class FilterConfig {
    
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CorsFilter());
        registrationBean.addUrlPatterns("/*"); // 모든 요청에 적용
        registrationBean.setOrder(1); // 필터 순서 설정
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> authorizationFilter() {
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthorizationFilter());
        registrationBean.addUrlPatterns("/s/*"); // 모든 요청에 적용
        registrationBean.setOrder(2); // 필터 순서 설정
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<LogFilter> loggingFilter() {
        FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LogFilter());
        registrationBean.addUrlPatterns("/*"); // 모든 요청에 적용
        registrationBean.setOrder(3); // 필터 순서 설정
        return registrationBean;
    }
}
