package om.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final String[] openUrls = {
      "/doc/webui","/doc/json/**","/gw/**","/doc/swagger-ui/index.html","/doc/swagger-ui/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(authorize ->authorize
                .requestMatchers(openUrls).permitAll()
                .anyRequest().authenticated()
        ).oauth2ResourceServer(
                oauth2 -> oauth2.jwt(Customizer.withDefaults())
        ).build();
    }
}
