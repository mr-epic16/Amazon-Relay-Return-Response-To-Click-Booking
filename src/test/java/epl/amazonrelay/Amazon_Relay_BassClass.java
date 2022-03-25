package epl.amazonrelay;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon_Relay_BassClass {
	public static WebDriver driver;

	public static TakesScreenshot tk;

	public static JavascriptExecutor js;

	public static void launchBrowser(String Browser) {

		if (Browser.equalsIgnoreCase("head")) {

			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();

			driver.manage().window().maximize();
		}

		else if (Browser.equalsIgnoreCase("headless")) {

			WebDriverManager.chromedriver().setup();
			chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("--disable-dev-shm-usage");
			chromeOptions.addArguments("--disable-gpu");
			chromeOptions.addArguments("window-size=1366,612");
			driver = new ChromeDriver(chromeOptions);

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

	public static void captureScreenshot(String trNum) {

		tk = (TakesScreenshot) driver;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
		String FileDate = sdf.format(timestamp).toString() + ".png";
		File screenshotAs = tk.getScreenshotAs(OutputType.FILE);
		File perm = new File(".//AmazonRelayBooking/" + trNum + "_" + FileDate);
		try {
			FileUtils.copyFile(screenshotAs, perm);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void navigateRefresh() {
		driver.navigate().refresh();
	}

	public static void sleepTime() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public static void closeBrowser() {
		driver.quit();

	}

	public static void jsClick(WebElement e) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", e);
	}

	public static ChromeOptions chromeOptions;

	public static void drops_Address_Arrangements(String values, LinkedList<String> pickup, LinkedList<String> drop) {
		String substring = values.substring(6);
		String last111 = pickup.getLast();
		int indexOf1 = pickup.lastIndexOf(last111);
		String string2 = drop.get(indexOf1);
		if (!string2.equalsIgnoreCase(substring)) {
			drop.set(indexOf1, substring);
		}

	}

	public static void pickup_Address_Arrangements(String values, LinkedList<String> pickup) {
		String substring = values.substring(6);
		pickup.add(substring);
	}

	@SuppressWarnings("unchecked")
	public static void Json_Drops_Arrangements(String drops, LinkedHashMap<String, String> drops_Object,
			JSONArray drops_array) {
		if (drops != "N/A") {
			drops_Object = new LinkedHashMap<>(1);
			drops_Object.put("drop_address", drops);
			drops_array.add(drops_Object);
		}
	}

	public static void scrollDown(WebElement e) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", e);

	}

	// @SuppressWarnings("deprecation")
	// public static void get_Arrival_Pickup_Date(WebElement arrival_pickup_date,
	// LinkedList<String> set_arrival_pickup_date) {
	// String arrivaldate = arrival_pickup_date.getText();
	//
	// CharSequence subSequence2 = arrivaldate.subSequence(3, 5);
	// String string = subSequence2.toString();
	//
	// String s1 = "/";
	// String concat = string.concat(s1);
	//
	// CharSequence subSequence = arrivaldate.subSequence(0, 2);
	// String string2 = subSequence.toString();
	// String concat2 = concat.concat(string2);
	//
	// CharSequence subSequence3 = arrivaldate.subSequence(5, 11);
	//
	// String string3 = subSequence3.toString();
	// String concat3 = concat2.concat(string3);
	//
	// Date date = new Date();
	// int year = date.getYear();
	// int currentYear = year + 1900;
	// String valueOf = String.valueOf(currentYear);
	// String concat4 = valueOf.concat(s1);
	// String concat5 = concat4.concat(concat3);
	//
	// set_arrival_pickup_date.add(concat5);
	// }

	public static void get_Price(WebElement get_Price, LinkedList<String> set_price_alldata) {

		String text = get_Price.getText();
		String replace = text.replaceAll(",", "");
		String substring = replace.substring(1);
		// if (head_Click == i) {
		// break;
		// }
		set_price_alldata.add(substring);
	}

	public static void get_All_Truck_Length(WebElement getlength, LinkedList<String> set_pickup_truck) {
		String length = getlength.getText();

		// if (head_Click == i11) {
		// break;
		// }
		switch (length) {
		case "THIRTY_FOUR_FOOT_TRUCK":
			String Truck_34 = "34' Truck";
			set_pickup_truck.add(Truck_34);
			break;
		case "THIRTY_TWO_FOOT_TRUCK":
			String Truck_32 = "32' Truck";
			set_pickup_truck.add(Truck_32);
			break;
		case "TWENTY_FOOT_TRUCK_CNG":
			String Truck_20 = "20' Truck";
			set_pickup_truck.add(Truck_20);
			break;
		case "FOURTEEN_FOOT_TRUCK_CNG":
			String Truck_14 = "14' Truck";
			set_pickup_truck.add(Truck_14);
			break;
		case "SEVEN_FOOT_TRUCK_ELECTRIC_AMT":
			String Truck_7 = "7' Truck";
			set_pickup_truck.add(Truck_7);
			break;
		case "TEN_FOOT_TRUCK_AMT":
			String Truck_10 = "10' Truck";
			set_pickup_truck.add(Truck_10);
			break;
		default:
			set_pickup_truck.add(length);
			break;

		}
	}

	public static void get_All_TR_ID(WebElement get_TR_Num, LinkedList<String> set_TR_ID) {
		String tRId = get_TR_Num.getText();
		String substring = tRId.substring(3);
		set_TR_ID.add(substring);
	}

	public static void get_Tr_Row_Header_Pickup_ID(WebElement pickup_ID_fourLetter, WebElement pickup_price,
			LinkedList<String> set_pickupId) {
		String pickuptext = pickup_ID_fourLetter.getText();
		String substring2 = pickuptext.substring(0, 5);
		String text = pickup_price.getText();
		String substring = text.substring(1);
		String concat = substring2.concat(substring);
		set_pickupId.add(concat);
	}

	public static void get_Top_Row_Header_Pickup_ID(WebElement all_Head_Number, WebElement all_Head_price,
			WebElement all_Head_Word, LinkedList<String> set_allPickup_ID) {
		String pickuptext = all_Head_Number.getText();
		String text = all_Head_price.getText();
		String substring = text.substring(1);
		String text2 = all_Head_Word.getText();
		String substring2 = text2.substring(0, 4);
		String concat2 = pickuptext.concat(substring2);
		String concat3 = concat2.concat(substring);
		set_allPickup_ID.add(concat3);
	}

	@SuppressWarnings("deprecation")
	public static void get_Pickup_TimeStamp(WebElement get_PickUp_TimeStamp,
			LinkedList<String> set_arrival_pickup_date) {
		// TODO Auto-generated method stub
		String time_stamp = get_PickUp_TimeStamp.getText();
		String substring = time_stamp.substring(4, 16);
		String replaceFirst = substring.replaceFirst(" ", "/");
		Date date = new Date();
		int year = date.getYear();
		int currentYear = year + 1900;
		String valueOf = String.valueOf(currentYear);
		String d = "/";
		String concat = valueOf.concat(d);
		String concat2 = concat.concat(replaceFirst);
		// 2022/24/Mar 10:32
		SimpleDateFormat fromUser = new SimpleDateFormat("yyyy/dd/MMM HH:mm");
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		try {
			String reformattedStr = myFormat.format(fromUser.parse(concat2));
			// 2022/03/24 10:32
			set_arrival_pickup_date.add(reformattedStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
