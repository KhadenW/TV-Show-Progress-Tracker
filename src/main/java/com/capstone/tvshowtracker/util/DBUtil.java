package com.capstone.tvshowtracker.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

public class DBUtil {
    private static final String PROPERTIES_FILE = "/db.properties";

    public static Connection getConnection() throws Exception {
        Properties props = new Properties();
        try (InputStream input = DBUtil.class.getResourceAsStream(PROPERTIES_FILE)) {
            props.load(input);
        }
        String url = props.getProperty("db.url").trim();
        String username = props.getProperty("db.username").trim();
        String password = props.getProperty("db.password").trim();
        return DriverManager.getConnection(url, username, password);
    }
} 