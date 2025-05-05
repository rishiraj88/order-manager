package om.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.List;

@EnableWebSecurity
public class SecurityConfig {
    // list the URLs which should be allowed access without any need for authentication
    private final String[] openUrls = {
      "/doc/webui","/doc/json/**","/gw/**","/doc/swagger-ui/index.html","/doc/swagger-ui/**","/actuator/prometheus"
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

  //  @Bean
   // CorsConfigurationSource corsConfigurationSource() {
        /* // Kept for development purpose. Will be removed later on.
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.applyPermitDefaultValues();
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
        */
    /*
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;*/
  //  }

   public void addCorsMappings(CorsRegistry registry ) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowedHeaders("*");
    }
}
