package org.pinky83.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@PropertySources({
        @PropertySource("db/default.properties"),
        @PropertySource(value = "db/${spring.profiles.active}.properties")
})
@Slf4j
public class AppConfiguration {

    public static final String PACKAGE_TO_SCAN = "org.pinky83.pojo";
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
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .setName("quiz")
                .addScript("classpath:db/initDB_hsql.sql")
//                .addScript("classpath:jdbc/populateDB_hsql.sql")
                .build();
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

    public Connection getConnection() throws SQLException {

        return DriverManager.getConnection(dbUrl, username, password);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {

        return new PropertySourcesPlaceholderConfigurer();
    }

}
