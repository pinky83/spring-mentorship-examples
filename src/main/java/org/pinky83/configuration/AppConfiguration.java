package org.pinky83.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySources({
        @PropertySource("db/default.properties"),
        @PropertySource(value = "db/${spring.profiles.active}.properties")
})
public class AppConfiguration {

    @Autowired
    private Environment environment;

    @Value("${database.url}")
    private String dbUrl;

    @Value("${database.username}")
    private String username;

    @Value("${database.password}")
    private String password;

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("app");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(0);

        return messageSource;
    }

    @Bean
    @Profile("hsqldb")
    public DataSource hsqldbDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(dbUrl);
        dataSource.setDriverClassName(environment.getProperty("database.driverClassName"));
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    @Profile("postgres")
    public DataSource postgresDataSource() {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setUrl(dbUrl);
        dataSource.setDriverClassName(environment.getProperty("database.driverClassName"));
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean("entityManagerFactory")
    @Profile("hsqldb")
    public LocalContainerEntityManagerFactoryBean hsqldbEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(hsqldbDataSource());
        entityManagerFactoryBean.setPackagesToScan("org.pinky83.pojo");

        return entityManagerFactoryBean;
    }

    @Bean("entityManagerFactory")
    @Profile("postgres")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(postgresDataSource());
        entityManagerFactoryBean.setPackagesToScan("org.pinky83.pojo");

        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {

        return new PropertySourcesPlaceholderConfigurer();
    }

}
