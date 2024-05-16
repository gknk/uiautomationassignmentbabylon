package uicommon;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * UITestConfiguration implements web driver invocation, reads property file
 * from class path
 *
 * @author Gopal
 */
public class UITestConfiguration {
	WebDriver driver;
	Properties property;
	String propertyFileName = "config.properties";
	InputStream inputStream = null;

	public UITestConfiguration() {
	}

	/**
	 * Reads the property file from class path
	 * 
	 * @param No parameter.
	 * @return Properties instance which contains the property file values.
	 */
	public Properties readPropertyFile() {
		property = new Properties();
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
			if (inputStream != null) {
				property.load(inputStream);
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return property;
	}

	/**
	 * Creates the WebDriver instance based on the browser mentioned in the property
	 * file
	 * 
	 * @param No parameter.
	 * @return A reference to the WebDriver instance created.
	 */
	public WebDriver createDriver() {
		if (property.getProperty("browser").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", property.getProperty("chromedriverlocation"));
			driver = new ChromeDriver();
		} else if (property.getProperty("browser").equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", property.getProperty("firefoxdriverlocation"));
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

}
