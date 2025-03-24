package curtinho.app.api.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Configurations {

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> filterRegistrationBean(){
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthorizationFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.addUrlPatterns("/h2-console/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .headers(headers -> headers
                    .frameOptions().disable()
                    .xssProtection().disable()
                    .contentSecurityPolicy("frame-ancestors 'self'"))
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(HttpMethod.GET,"p/{shortUri}").permitAll();
                    req.requestMatchers(HttpMethod.POST, "s").permitAll();
                    req.requestMatchers(HttpMethod.POST, "qr").permitAll();
                    req.requestMatchers("/h2-console/**").permitAll();
                })
                .build();
    }

}
