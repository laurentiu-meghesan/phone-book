package org.fasttrackit.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfiguration {

    public static Connection getConnection() throws IOException, SQLException {

        InputStream dbProperties = DatabaseConfiguration.class.getClassLoader().
                getResourceAsStream("db.properties");

        try {
            Properties properties = new Properties();
            properties.load(dbProperties);

            return DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password"));
        } finally {
            if (dbProperties != null) {
                dbProperties.close();
            }
        }
    }
}
