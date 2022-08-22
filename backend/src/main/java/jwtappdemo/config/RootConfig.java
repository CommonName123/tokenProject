package jwtappdemo.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Конфигуратор "серверной" части приложения
 */
@Configuration
@ComponentScan(basePackages = {"jwtappdemo.rest",})
@ImportResource(value = {"classpath:context.xml"})
public class RootConfig {

    @Bean
    public TaskScheduler taskScheduler(){
        return new ConcurrentTaskScheduler();
    }

    @Bean
    public Executor taskExecutor(){
        return new SimpleAsyncTaskExecutor();
    }
}
