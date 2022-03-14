package epl.amazonrelay;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon_Relay_BassClass {
	public static WebDriver driver;

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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public static void closeBrowser() {
		driver.quit();

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

	public static void address_Arrangements(String values, LinkedList<String> pickup, LinkedList<String> drop) {
		String substring = values.substring(6);
		String last111 = pickup.getLast();
		int indexOf1 = pickup.lastIndexOf(last111);
		String string2 = drop.get(indexOf1);
		if (!string2.equalsIgnoreCase(substring)) {
			drop.set(indexOf1, substring);
		}

	}
}
