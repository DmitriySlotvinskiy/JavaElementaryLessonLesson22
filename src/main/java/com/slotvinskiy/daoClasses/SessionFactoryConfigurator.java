package com.slotvinskiy.daoClasses;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SessionFactoryConfigurator {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactoryWithConfigurations() {
        if (sessionFactory != null) {
            return sessionFactory;
        }
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        String username = getNextProperty("username");
        String password = getNextProperty("password");
        configuration.getProperties().setProperty("hibernate.connection.username", username);
        configuration.getProperties().setProperty("hibernate.connection.password", password);
        sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }

    private static String getNextProperty(String someProperty) {
        Properties properties = getPropertiesFile();
        return properties.getProperty(someProperty);
    }

    private static Properties getPropertiesFile() {
        Properties properties = new Properties();
        String userHome = System.getProperty("user.home");
        String config = System.getProperty("configuration");
        File file = new File(userHome + File.separator + config);
        try {
            properties.load(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
