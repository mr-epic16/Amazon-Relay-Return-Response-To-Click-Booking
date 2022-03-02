package epl.amazonrelay;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

@SuppressWarnings("unused")
public class Amazon_Relay_BassClass {
	public static WebDriver driver;

	public static Actions a;

	public static Select s;

	public static TakesScreenshot tk;

	public static JavascriptExecutor js;

	public static void launchBrowser(String Browser) {

		if (Browser.startsWith("chrome")) {

			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();

			driver.manage().window().maximize();
		}

		else if (Browser.startsWith("ff")) {

			WebDriverManager.firefoxdriver().setup();

			driver = new FirefoxDriver();

			driver.manage().window().maximize();
		}

		else {

			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
		}

	}

	public static void launchUrl(String Url) {

		driver.get(Url);
	}

	public static void fillTheText(WebElement e, String value) {

		e.sendKeys(value);

	}

	public static void clickTheButton(WebElement e) {

		e.click();
	}

	public static void getTheText(WebElement e) {

		String text = e.getText();
		System.out.println(text);

	}

	public static void getTheAttribute(WebElement e, String value) {

		String attribute = e.getAttribute(value);
		System.out.println(attribute);

	}

	public static void click() {
		a = new Actions(driver);
		a.click().perform();
	}

	public static void screenShot() throws IOException {

		tk = (TakesScreenshot) driver;
		File screenshotAs = tk.getScreenshotAs(OutputType.FILE);
		File perm = new File(".//ScreenShot/picture" + System.currentTimeMillis() + ".png");
		FileUtils.copyFile(screenshotAs, perm);

	}

	public static void navigateRefresh() {
		driver.navigate().refresh();
	}

	public static void sleepTime() {
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	}

	public static void closeBrowser() {
		driver.quit();

	}

	public static void threadSleep(long seconds) {

		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

	public static void jsClick(WebElement e) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", e);
	}

	public static ChromeOptions chromeOptions;

	public static void chromeHeadless() {
		WebDriverManager.chromedriver().setup();
		chromeOptions = new ChromeOptions();

		chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--disable-dev-shm-usage");
		chromeOptions.addArguments("--disable-gpu");
		chromeOptions.addArguments("window-size=1400,2100");

		driver = new ChromeDriver(chromeOptions);

	}
}
