package ru.netology.qa;

import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.netology.qa.screens.MainActivityPage;

import java.net.MalformedURLException;
import java.net.URL;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppiumChangeTest {

    private AppiumDriver driver;
    MainActivityPage page;
    String newText = "Any Text";

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("appium:deviceName", "Pixel 2 API 33");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723");

        driver = new AppiumDriver(remoteUrl, desiredCapabilities);
        page = new MainActivityPage(driver);
    }


    @Test
    public void SetAnEmptyStringTest() {

        page.userInput.isDisplayed();
        page.userInput.sendKeys(newText);

        page.buttonChange.isDisplayed();
        page.buttonChange.click();

        page.userInput.sendKeys(" ");
        page.buttonChange.click();

        Assertions.assertEquals(page.textToBeChanged.getText(), newText);

    }

    @Test
    public void OpenTextInANewActivityTest() {

        page.userInput.isDisplayed();
        page.userInput.sendKeys(newText);

        page.buttonActivity.isDisplayed();
        page.buttonActivity.click();

        page.activityText.isDisplayed();

        Assertions.assertEquals(page.activityText.getText(), newText);
    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
