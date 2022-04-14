package org.pinky83.configuration;

import lombok.extern.slf4j.Slf4j;
import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@PropertySources({
        @PropertySource("db/default.properties"),
        @PropertySource(value = "db/${spring.profiles.active}.properties")
})
@Slf4j
public class AppConfiguration implements InitializingBean {

    static {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException e) {
            log.info("JDBC driver not found", e);
        }
    }

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

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException e) {
            log.info("Error while trying to get DB connection", e);
        }

        return connection;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {

        return new PropertySourcesPlaceholderConfigurer();
    }

    @Override
    public void afterPropertiesSet() throws SQLException {
        try (Connection connection = getConnection()) {
            //TODO replace to resource with relative path
            SqlFile sf = new SqlFile(new File("/Users/dmytroyakovenko/Projects/spring-mentorship-examples/src/main/resources/db/initDB_hsql.sql"));
            sf.setConnection(connection);
            sf.execute();
        } catch (SqlToolError e) {
            log.error("Sql Tool error", e);
        } catch (SQLException e) {
            log.info("Error while trying to execute initial DB script", e);
        } catch (IOException e) {
            log.info("Error while trying to get DB connection", e);
        }
    }
}
