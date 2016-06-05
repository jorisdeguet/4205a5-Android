package org.deguet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {

    // TODO
    public static int getTokenValidity() {return 15;};
    public static int getUserValidityMargin() { return 1;}
    public static int getQuestionDuration() { return 14;}

    public static long getMaxNumberOfAccounts() { return Long.MAX_VALUE;}

    private final Properties fileProps;

    private AppConfig() {
        fileProps = new Properties();
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
        try {
            fileProps.load(in);
        } catch (IOException e) {
            throw new RuntimeException("application.properties cannot be loaded");
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException("application.properties cannot be loaded");
            }
        }
    }

    private static AppConfig INSTANCE = new AppConfig();

    public static Properties get(){
        return INSTANCE.fileProps;
    }



}
