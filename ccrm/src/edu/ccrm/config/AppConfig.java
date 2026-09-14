package edu.ccrm.config;

public class AppConfig {
    private static final AppConfig INSTANCE = new AppConfig();

    private AppConfig() {}

    public static AppConfig getInstance() {
        return INSTANCE;
    }

    public void loadConfigurations() {
        System.out.println("Application configurations loaded successfully.");
    }
}