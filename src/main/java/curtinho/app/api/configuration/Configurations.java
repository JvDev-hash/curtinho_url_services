package curtinho.app.api.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
        return http.csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).disable())
                .headers(headers -> headers
                    .frameOptions(FrameOptionsConfig::sameOrigin))
                .authorizeHttpRequests(req -> {

                    //Url endpoints
                    req.requestMatchers(HttpMethod.GET,"/url/**").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/url/**").permitAll();
                    req.requestMatchers(HttpMethod.DELETE, "/url/**").permitAll();

                    //QrCode endpoints
                    req.requestMatchers(HttpMethod.POST, "/qr/**").permitAll();

                    //ApiKey endpoints
                    req.requestMatchers(HttpMethod.POST, "/apiKey/**").permitAll();

                    //Development endpoint
                    req.requestMatchers("/h2-console/**").permitAll();
                })
                .build();
    }

}
