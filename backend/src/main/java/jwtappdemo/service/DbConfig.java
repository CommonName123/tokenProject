package jwtappdemo.service;

import jwtappdemo.service.utils.DataBaseUtils;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import java.util.HashMap;
import java.util.Map;

import com.jolbox.bonecp.BoneCPDataSource;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.UserCredentialsDataSourceAdapter;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Конфигурация БД приложения
 */
@Configuration
@EnableJpaRepositories(basePackages = "jwtappdemo.repository")
@EnableTransactionManagement
public class DbConfig {

    @Autowired
    private Config config;

    @Bean("dataSource")
    public BoneCPDataSource dataSource() {
        return DataBaseUtils.createDefaultDataSource(config.getJdbcUrl());
    }

    @Autowired
    @Bean("dataSourceSecure")
    public UserCredentialsDataSourceAdapter dataSourceSecure(DataSource dataSource) {
        UserCredentialsDataSourceAdapter userCredentialsDataSourceAdapter = new UserCredentialsDataSourceAdapter();
        userCredentialsDataSourceAdapter.setTargetDataSource(dataSource);

        return userCredentialsDataSourceAdapter;
    }

    @Bean
    @Autowired
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(UserCredentialsDataSourceAdapter dataSourceSecure,
                                                                       MultiTenantConnectionProvider multiTenantConnectionProvider,
                                                                       CurrentTenantIdentifierResolver tenantIdentifierResolver) {
        LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        containerEntityManagerFactoryBean.setDataSource(dataSourceSecure);
        containerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        containerEntityManagerFactoryBean.setPackagesToScan("jwtappdemo.domain");

        Map<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put(Environment.DIALECT, "jwtappdemo.repository.CustomPostgreSQL93Dialect");

        jpaProperties.put(Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA);
        jpaProperties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
        jpaProperties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, tenantIdentifierResolver);


        jpaProperties.put(Environment.NON_CONTEXTUAL_LOB_CREATION, true);
        jpaProperties.put(Environment.STATEMENT_FETCH_SIZE, 50);
        jpaProperties.put(Environment.STATEMENT_BATCH_SIZE, 20);


//        jpaProperties.put(Environment.SHOW_SQL, true);
//        jpaProperties.put(Environment.FORMAT_SQL, true);
//        jpaProperties.put(Environment.HBM2DDL_AUTO, "update");
//        jpaProperties.put(Environment.HBM2DLL_CREATE_NAMESPACES, true);
//        jpaProperties.put(Environment.HBM2DDL_DATABASE_ACTION, "update");
//        jpaProperties.put(Environment.HBM2DLL_CREATE_SCHEMAS, true);
//        jpaProperties.put(Environment.HBM2DDL_AUTO, "create");

        containerEntityManagerFactoryBean.setJpaPropertyMap(jpaProperties);

        return containerEntityManagerFactoryBean;


    }

    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

//    @Bean
//    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
//        return new PersistenceExceptionTranslationPostProcessor();
//    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

//    @Bean
//    public MethodValidationPostProcessor methodValidationPostProcessor() {
//        return new MethodValidationPostProcessor();
//    }
}
