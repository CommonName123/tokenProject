package net.proselyte.jwtappdemo.service;

import lombok.Data;

import java.net.URI;

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
}
