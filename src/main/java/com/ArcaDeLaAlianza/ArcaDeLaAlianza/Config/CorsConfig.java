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
        corsConfig.addAllowedOrigin("http://localhost:5173");  // Reemplaza por tu origen de frontend
        corsConfig.addAllowedMethod("*");  // Permite todos los métodos HTTP
        corsConfig.addAllowedHeader("*");  // Permite todos los encabezados
        corsConfig.setAllowCredentials(true);  // Permite el uso de cookies o credenciales

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);  // Aplica la configuración a todas las rutas
        return source;
    }
}
