package com.spring.rest.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Logger;

@PropertySource({"classpath:properties/persistence-mysql.properties"})
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan("com.spring.crm.rest")
@Configuration
public class DemoAppConfig implements WebMvcConfigurer {

    private final Environment environment;
    private final Logger logger = Logger.getLogger(getClass().getName());

    public DemoAppConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() {

        // create connection pool
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        // set the jdbc driver class
        try {
            dataSource.setDriverClass(environment.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }

        // set database connection props
        dataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
        dataSource.setUser(environment.getProperty("jdbc.user"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));

        // log the connection props
        logger.info(">>> jdbc.url=" + dataSource.getJdbcUrl());
        logger.info(">>> jdbc.user=" + dataSource.getUser());

        // set connection pool props
        dataSource.setInitialPoolSize(getValue("connection.pool.initialPoolSize"));
        dataSource.setMinPoolSize(getValue("connection.pool.minPoolSize"));
        dataSource.setMaxPoolSize(getValue("connection.pool.maxPoolSize"));
        dataSource.setMaxIdleTime(getValue("connection.pool.maxIdleTime"));

        return dataSource;

    }

    @Bean
    public LocalSessionFactoryBean localSessionFactoryBean() {

        // create session factorise
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();

        // set the properties
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan(environment.getProperty("hibernate.packagesToScan"));
        sessionFactoryBean.setHibernateProperties(getHibernateProperties());

        return sessionFactoryBean;

    }

    @Bean
    public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {

        // setup transaction manager based on session factory
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory);

        return hibernateTransactionManager;

    }

    private Properties getHibernateProperties() {

        // set hibernate properties
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));

        return properties;

    }

    private int getValue(String key) {
        return Integer.parseInt(Objects.requireNonNull(environment.getProperty(key), key + " null"));
    }

}
