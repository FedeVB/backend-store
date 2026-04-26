package com.retail.tienda.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Esto aplica a todos los endpoints de tu API (/movimientos, /usuarios, etc.)
                        .allowedOrigins("https://frontend-store-lsmx.vercel.app") // 🔥 PON TU URL DE VERCEL AQUÍ
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Los métodos HTTP que Angular va a usar
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}