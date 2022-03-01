package amazon;

import java.awt.AWTException;
import java.awt.Robot;
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
public class Baseclass {
	public static WebDriver driver;

	public static Actions a;

	public static Select s;

	public static TakesScreenshot tk;

	public static Alert t;

	public static Robot r;

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

	public static void getTheTitle() {

		String title = driver.getTitle();
		System.out.println(title);

	}

	public static void getUrTheUrl() {

		String currentUrl = driver.getCurrentUrl();
		System.out.println(currentUrl);

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

	public static void dragAndDrop(WebElement source, WebElement target) {

		a = new Actions(driver);
		a.dragAndDrop(source, target).perform();
	}

	public static void moveToElement(WebElement target) {
		a = new Actions(driver);
		a.moveToElement(target).perform();

	}

	public static void doubleClick() {
		a = new Actions(driver);
		a.doubleClick().perform();

	}

	public static void contextClick() {
		a = new Actions(driver);
		a.contextClick().perform();
	}

	public static void click() {
		a = new Actions(driver);
		a.click().perform();
	}

	public static void selectIndex(WebElement e, int value) {
		s = new Select(e);
		s.selectByIndex(value);
	}

	public static void selectValue(WebElement e, String value) {
		s = new Select(e);
		s.selectByValue(value);
	}

	public static void selectVisibleText(WebElement e, String value) {
		s = new Select(e);
		s.selectByVisibleText(value);
	}

	@SuppressWarnings("unused")
	private void getFirstSelectOption(WebElement e) {
		s = new Select(e);
		WebElement firstSelectedOption = s.getFirstSelectedOption();
		String text = firstSelectedOption.getText();
		System.out.println(text);
	}

	public static void getAllSelectedOption(WebElement e) {
		s = new Select(e);
		List<WebElement> allSelectedOptions = s.getAllSelectedOptions();
		for (WebElement all : allSelectedOptions) {
			String attribute = all.getAttribute("innerText");
			System.out.println(attribute);
		}
	}

	public static void getOption(WebElement e) {
		s = new Select(e);
		List<WebElement> options = s.getOptions();
		for (WebElement w : options) {
			String text = w.getText();
			System.out.println(text);
		}
	}

	public static void isMultiple(WebElement e) {
		s = new Select(e);
		boolean multiple = s.isMultiple();
		System.out.println(multiple);
	}

	public static void deSelectedIndex(WebElement e, int index) {
		s = new Select(e);
		s.deselectByIndex(index);

	}

	public static void deSelectedValue(WebElement e, String value) {
		s = new Select(e);
		s.deselectByValue(value);
	}

	public static void deSelectedVisibleText(WebElement e, String value) {
		s = new Select(e);
		s.deselectByVisibleText(value);
	}

	public static void deSelectAll(WebElement e) {
		s = new Select(e);
		s.deselectAll();
	}

	public static void screenShot() throws IOException {

		tk = (TakesScreenshot) driver;
		File screenshotAs = tk.getScreenshotAs(OutputType.FILE);
		File perm = new File(".//ScreenShot/picture" + System.currentTimeMillis() + ".png");
		FileUtils.copyFile(screenshotAs, perm);

	}

	public static void alertAccept() {

		driver.switchTo().alert();
		t.accept();
	}

	public static void alertDismiss() {
		driver.switchTo().alert();
		t.dismiss();
	}

	public static void alertSendkeys(String value) {
		driver.switchTo().alert();
		t.sendKeys(value);
	}

	public static void alertGetText() {
		driver.switchTo().alert();
		String text = t.getText();
		System.out.println(text);
	}

	public static void navigateUrl(String value) {
		driver.navigate().to(value);
	}

	public static void navigateBack() {
		driver.navigate().back();
	}

	public static void navigateforward() {
		driver.navigate().forward();
	}

	public static void navigateRefresh() {
		driver.navigate().refresh();
	}

	public static void keyPress(int value) throws AWTException {
		r = new Robot();
		r.keyPress(value);

	}

	public static void keyRelease(int value) throws AWTException {
		r = new Robot();
		r.keyRelease(value);
	}

	public static void frameId(String value) {
		driver.switchTo().frame(value);
	}

	public static void frameWebElement(WebElement e) {
		driver.switchTo().frame(e);
	}

	public static void frameIndex(int value) {
		driver.switchTo().frame(value);
	}

	public static void parentFrame() {
		driver.switchTo().parentFrame();
	}

	public static void defaultFrame() {
		driver.switchTo().defaultContent();
	}

	public static void date() {
		Date d = new Date();
		System.out.println(d);
	}

	public static void sleepTime() {
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	}

	public static void scrollDown(WebElement e) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", e);

	}

	public static void scrollUp(WebElement e) {

		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false)", e);

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

	public static void jsFefresh() {
		js = (JavascriptExecutor) driver;
		js.executeScript("history.go(0)");
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
