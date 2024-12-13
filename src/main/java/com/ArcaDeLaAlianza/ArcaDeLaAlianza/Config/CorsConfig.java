package com.ArcaDeLaAlianza.ArcaDeLaAlianza.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("http://localhost:5173");
        corsConfig.addAllowedOrigin("https://ventadecampanasarcadelaalianza.onrender.com");
        corsConfig.addAllowedOrigin("https://arcadelaalianzaserver.onrender.com");
        corsConfig.addAllowedOrigin("https://arcadelaalianza-production.up.railway.app");
        corsConfig.addAllowedOrigin("https://ventadecampanas.onrender.com");
        corsConfig.addAllowedOrigin("https://arca-de-la-alianza-front-end.onrender.com");
        corsConfig.addAllowedMethod("*");
        corsConfig.addAllowedHeader("*");
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }
}
