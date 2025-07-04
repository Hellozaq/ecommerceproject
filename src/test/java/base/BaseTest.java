package base;

import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class BaseTest {
    protected WebDriver driver;
    protected Properties credentials = new Properties();
    protected Properties config = new Properties();

    public BaseTest() {
        loadConfigurations();
    }

    private void loadConfigurations() {
        try (FileInputStream configFile = new FileInputStream("src/test/resources/config.properties");
             FileInputStream credentialsFile = new FileInputStream("src/test/resources/credentials.properties")) {
            config.load(configFile);
            credentials.load(credentialsFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration or credentials file: " + e.getMessage(), e);
        }
    }

    public String getValidUsername() {
        var username = System.getProperty("username");
        return username == null ? credentials.getProperty("username") : username;
    }

    public String getLockedUsername() {
        var username = System.getProperty("lockedUsername");
        return username == null ? credentials.getProperty("lockedUsername") : username;
    }

    public String getValidPassword() {
        var password = System.getProperty("password");
        return password == null ? credentials.getProperty("password") : password;
    }

    public String getStoreFrontCommerceUrl() {
        return  config.getProperty("storeFrontCommerceUrl");
    }

    protected Integer getRandomNumber() {
        return (int) (Math.random() * 100000000 + 1);
    }
}