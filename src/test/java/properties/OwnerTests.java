package properties;

import config.CredentialsConfig;

import config.DriverConfig;
import config.ProjectConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;

public class OwnerTests {
    @Test
    @Tag("owner1")
    void ownerTest() {
        CredentialsConfig credentials = ConfigFactory.create(CredentialsConfig.class);

        String user = credentials.user();
        String password = credentials.password();

        System.out.println(format("Login with %s and %s", user, password));
    }

    @Test
    void systemProperty6Test() {
        DriverConfig driverConfig = ConfigFactory.create(DriverConfig.class);

        String browser = driverConfig.browserName();
        String browserVersion = driverConfig.browserVersion();
        String browserSize = driverConfig.browserSize();

        String browserConfig = String.format("Browser: %s, version: %s, window size: %s",
                browser, browserVersion, browserSize);

        // gradle property2_test -Dbrowser=mozilla -Dbrowser_version=96.0 -Dbrowser_size=1080x720
        System.out.println(browserConfig);
    }

    @Test
    @Tag("owner3")
    void systemProperty7Test() {
        System.setProperty("environment", System.getProperty("environment", "stage"));

        ProjectConfig projectConfig = ConfigFactory.create(ProjectConfig.class);

        String environment = System.getProperty("environment");
        String webUrl = projectConfig.webUrl();
        String apiUrl = projectConfig.apiUrl();
        String apiPort = projectConfig.apiPort();

        String browserConfig = String.format("We run tests on: %s\nwebUrl: %s\napiUrl: %s\napiPort: %s",
                environment, webUrl, apiUrl, apiPort);

        // gradle property2_test -Dbrowser=mozilla -Dbrowser_version=96.0 -Dbrowser_size=1080x720
        System.out.println(browserConfig);
    }
}