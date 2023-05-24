package config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionConfig {

    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();

        try (InputStream inputStream = ConnectionConfig.class
                .getClassLoader()
                .getResourceAsStream("application.properties")) {
            properties.load(inputStream);
            String url = properties.getProperty("datasource.url");
            String username = properties.getProperty("datasource.username");
            String password = properties.getProperty("datasource.password");
            return DriverManager.getConnection(url, username, password);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
