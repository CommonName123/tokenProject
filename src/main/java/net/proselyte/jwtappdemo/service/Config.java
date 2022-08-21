package net.proselyte.jwtappdemo.service;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import lombok.Data;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Конфигурация
 */
@Data
@Service("core")
public class Config {
    private String jdbcUrl="jdbc:postgresql://localhost:5432/shop";

    private Integer sessionMaxInactivePeriod;

    private Integer authCookieLifetime;

    /**
     * Возврщает имя хоста для подключения к БД
     *
     * @return
     */
    public String getHost() {
        if (jdbcUrl == null || jdbcUrl.isEmpty()) {
            return null;
        }
        String clearURI = jdbcUrl.substring(5);
        URI uri = URI.create(clearURI);
        return uri.getHost();
    }

    /**
     * Возвроащает номер порта для подключения к БД
     *
     * @return
     */
    public Integer getPort() {
        if (jdbcUrl == null || jdbcUrl.isEmpty()) {
            return null;
        }
        String clearURI = jdbcUrl.substring(5);
        URI uri = URI.create(clearURI);
        Integer port = uri.getPort();
        return port == -1 ? 5432 : port;
    }


    @Bean
    public GroupedOpenApi publicUserApi() {
        return GroupedOpenApi.builder()
                .group("Category")
                .pathsToMatch("/category/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenApi(@Value("Token app")String appDescription,
                                 @Value("1.0")String appVersion) {
        Server service1 = new Server().url("http://localhost:8080")
                .description("Dev service");
        Server service2 = new Server().url("http://localhost:8082")
                .description("Beta service");

        return new OpenAPI().info(new Info().title("Application API")
                        .version(appVersion)
                        .description(appDescription)
                        .license(new License().name("Apache 2.0")
                                .url("http://springdoc.org"))
                        .contact(new Contact().name("username")
                                .email("test@gmail.com")))
                .servers(Arrays.asList(service1, service2));
    }
}
