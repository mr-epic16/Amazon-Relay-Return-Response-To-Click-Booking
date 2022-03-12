
package epl.amazonrelay;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Amazon_Relay_Scrept_Class extends Amazon_Relay_POJO_Class {

	/* Store all pickup d1 d2 d3 d4 d5 addresses */
	private static LinkedList<String> get_all_pickup_data = new LinkedList<String>();

	/* Create pick up & all drops lists and Store all values */
	private static LinkedList<String> set_pickup_address = new LinkedList<String>();
	private static LinkedList<String> set_drop1_address = new LinkedList<String>();
	private static LinkedList<String> set_drop2_address = new LinkedList<String>();
	private static LinkedList<String> set_drop3_address = new LinkedList<String>();
	private static LinkedList<String> set_drop4_address = new LinkedList<String>();
	private static LinkedList<String> set_drop5_address = new LinkedList<String>();
	private static LinkedList<String> set_drop6_address = new LinkedList<String>();
	private static LinkedList<String> set_drop7_address = new LinkedList<String>();
	private static LinkedList<String> set_drop8_address = new LinkedList<String>();
	private static LinkedList<String> set_drop9_address = new LinkedList<String>();
	private static LinkedList<String> set_drop10_address = new LinkedList<String>();
	private static LinkedList<String> set_drop11_address = new LinkedList<String>();
	private static LinkedList<String> set_drop12_address = new LinkedList<String>();
	private static LinkedList<String> set_drop13_address = new LinkedList<String>();
	private static LinkedList<String> set_drop14_address = new LinkedList<String>();
	private static LinkedList<String> set_drop15_address = new LinkedList<String>();

	/* Store pickup truck length */
	private static LinkedList<String> set_pickup_truck = new LinkedList<String>();

	/* Store pickup arrival date */
	private static LinkedList<String> set_arrival_pickup_date = new LinkedList<String>();

	/* Store price all pickup ad drop */
	private static LinkedList<String> set_price_alldata = new LinkedList<String>();

	/* TR Adding */
	private static LinkedList<String> set_TR_ID = new LinkedList<String>();
	private static LinkedList<String> get_TR_ID_Two = new LinkedList<String>();
	private static LinkedList<String> set_TR_ID_Two = new LinkedList<String>();

	/* Store all pickup & drop id */
	private static LinkedList<String> set_allPickup_ID = new LinkedList<String>();

	/* Separate id pick up */

	private static LinkedList<String> set_pickupId = new LinkedList<String>();

	/* Store Multiple TR index */
	private static LinkedList<Integer> set_click_BTN = new LinkedList<Integer>();

	/* store json format data */
	private static LinkedList<String> json_AllData = new LinkedList<String>();

	public static void launching(String url) {
		// launchBrowser("chrome");
		chromeHeadless();
		launchUrl(url);
	}

	@SuppressWarnings({ "unchecked", "deprecation", "resource", "unused", })
	public static void login(String userName, String Password) throws Exception {
		sleepTime();
		Amazon_Relay_POJO_Class p = new Amazon_Relay_POJO_Class();
		WebDriverWait wait_all_Elements = new WebDriverWait(driver, 10);
		clickTheButton(p.getUsername());
		fillTheText(p.getUsername(), userName);

		clickTheButton(p.getPassword());
		fillTheText(p.getPassword(), Password);

		clickTheButton(p.getLogin());

		WebElement loadboard_Wait = wait_all_Elements.until(ExpectedConditions.elementToBeClickable(p.getLoadboard()));
		jsClick(loadboard_Wait);

		jsClick(p.getSearch());
		navigateRefresh();

		jsClick(p.getClickMoreBtn());

		clickTheButton(p.getPickEndBtn());
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, 4);
		String output = sdf.format(c.getTime());

		fillTheText(p.getPickEndBtn(), output);

		WebElement pickEndBtn2 = p.getPickEndBtn();
		pickEndBtn2.sendKeys(Keys.RETURN);

		XSSFWorkbook workbook = new XSSFWorkbook();

		// spreadsheet object
		XSSFSheet spreadsheet = workbook.createSheet("Amazon_Booking_Order");
		// This data needs to be written (Object[])
		// creating a row object
		XSSFRow row;

		// these are steps lines Excel data convention

		LinkedHashMap<String, Object[]> amazonRelay_Excel = new LinkedHashMap<String, Object[]>();
		amazonRelay_Excel.put("",
				new Object[] { "TR_ID_One", "TR_ID_Two", "Time_Stamp", "Pickup_Address", "Drop01_Address",
						"Drop02_Address", "Drop03_Address", "Drop04_Address", "Drop05_Address", "Drop06_Address",
						"Drop07_Address", "Drop08_Address", "Drop09_Address", "Drop10_Address", "Drop11_Address",
						"Drop12_Address", "Drop13_Address", "Drop14_Address", "Drop15_Address", "Truck_Length",
						"Price" });
		int rowid = 0;

		List<WebElement> firstclick2 = p.getFirstclick();
		for (int i = 0; i < firstclick2.size(); i++) {
			WebElement webElement = firstclick2.get(i);
			WebElement wait_First_Click = wait_all_Elements.until(ExpectedConditions.elementToBeClickable(webElement));
			jsClick(wait_First_Click);
		}

		/* scraping pickup address from amazon relay */

		List<WebElement> get_all_Head_Number = p.getAll_Head_Num();
		List<WebElement> get_all_Head_Word = p.getAll_Head_word();
		List<WebElement> get_all_Head_price = p.getAll_Head_price();

		for (int i = 0; i < get_all_Head_Number.size(); i++) {
			WebElement webElement = get_all_Head_Number.get(i);
			String pickuptext = webElement.getText();

			WebElement webElement2 = get_all_Head_price.get(i);
			String text = webElement2.getText();
			String substring = text.substring(1);

			WebElement webElement3 = get_all_Head_Word.get(i);
			String text2 = webElement3.getText();
			String substring2 = text2.substring(0, 4);

			String concat2 = pickuptext.concat(substring2);

			String concat3 = concat2.concat(substring);
			set_allPickup_ID.add(concat3);

		}
		System.out.println("All head Pickup ID Size : " + set_allPickup_ID.size());

		// /* scraping for pickup Id data */

		List<WebElement> get_pickup_ID_fourLetter = p.getPickup_ID_fourLetter();
		List<WebElement> get_pickup_price = p.getPickup_price();

		for (int i1 = 0; i1 < get_pickup_ID_fourLetter.size(); i1++) {

			WebElement webElement = get_pickup_ID_fourLetter.get(i1);
			String pickuptext = webElement.getText();

			WebElement webElement2 = get_pickup_price.get(i1);
			String text = webElement2.getText();
			String substring = text.substring(1);
			String concat = pickuptext.concat(substring);
			set_pickupId.add(concat);
		}

		System.out.println("Pickup Id Size : " + set_pickupId.size());
		int a = 0;
		for (int i = 0; i < set_allPickup_ID.size(); i++) {
			String string = set_allPickup_ID.get(i);
			for (int j = a; j < set_pickupId.size(); j++) {
				String string2 = set_pickupId.get(j);
				if (!string.equalsIgnoreCase(string2)) {
					set_click_BTN.add(i);
					a++;
					break;
				} else {
					a++;
					break;
				}

			}
		}
		System.out.println("Want to Click : " + set_click_BTN);

		List<WebElement> clickFirst = p.getClickFirst();
		for (int i = 0; i < set_click_BTN.size(); i++) {
			Integer integer = set_click_BTN.get(i);
			WebElement webElement = clickFirst.get(integer);
			jsClick(webElement);

		}
		/* scraping all pick arrival date from amazon relay */

		List<WebElement> get_arrival_pickup_date = p.getArrival_pickup_date();

		for (int i111 = 0; i111 < get_arrival_pickup_date.size(); i111++) {
			WebElement webElement11 = get_arrival_pickup_date.get(i111);
			String arrivaldate = webElement11.getText();

			CharSequence subSequence2 = arrivaldate.subSequence(3, 5);
			String string = subSequence2.toString();

			String s1 = "/";
			String concat = string.concat(s1);

			CharSequence subSequence = arrivaldate.subSequence(0, 2);
			String string2 = subSequence.toString();
			String concat2 = concat.concat(string2);

			CharSequence subSequence3 = arrivaldate.subSequence(5, 11);

			String string3 = subSequence3.toString();
			String concat3 = concat2.concat(string3);

			Date date = new Date();
			int year = date.getYear();
			int currentYear = year + 1900;
			String valueOf = String.valueOf(currentYear);
			String concat4 = valueOf.concat(s1);
			String concat5 = concat4.concat(concat3);

			set_arrival_pickup_date.add(concat5);
		}
		System.out.println("Arrival pickup date Size : " + set_arrival_pickup_date.size());

		List<WebElement> clickSecond = p.getClickSecond();
		for (int i = 0; i < clickSecond.size(); i++) {
			WebElement webElement = clickSecond.get(i);
			jsClick(webElement);
		}

		List<WebElement> clickthird = p.getClickthird();
		for (int i = 0; i < clickthird.size(); i++) {
			WebElement webElement = clickthird.get(i);
			jsClick(webElement);
		}

		List<WebElement> clickfourth = p.getClickfourth();
		for (int i = 0; i < clickfourth.size(); i++) {
			WebElement webElement = clickfourth.get(i);
			jsClick(webElement);

		}
		List<WebElement> clickfive = p.getClick_five();
		for (int i = 0; i < clickfive.size(); i++) {
			WebElement webElement = clickfive.get(i);
			jsClick(webElement);

		}
		List<WebElement> clicksix = p.getClick_six();
		for (int i = 0; i < clicksix.size(); i++) {
			WebElement webElement = clicksix.get(i);
			jsClick(webElement);

		}
		List<WebElement> clickseven = p.getClick_seven();
		for (int i = 0; i < clickseven.size(); i++) {
			WebElement webElement = clickseven.get(i);
			jsClick(webElement);

		}
		List<WebElement> clickeight = p.getClick_eight();
		for (int i = 0; i < clickeight.size(); i++) {
			WebElement webElement = clickeight.get(i);
			jsClick(webElement);

		}
		List<WebElement> clicknine = p.getClick_nine();
		for (int i = 0; i < clicknine.size(); i++) {
			WebElement webElement = clicknine.get(i);
			jsClick(webElement);

		}
		List<WebElement> clickten = p.getClick_ten();
		for (int i = 0; i < clickten.size(); i++) {
			WebElement webElement = clickten.get(i);
			jsClick(webElement);

		}
		/* scraping all price amount from amazon relay */

		List<WebElement> get_price = p.getPrice();

		for (WebElement webElement : get_price) {
			String text = webElement.getText();
			String replace = text.replaceAll(",", "");
			String substring = replace.substring(1);
			set_price_alldata.add(substring);
		}

		System.out.println("Price all data Size : " + set_price_alldata.size());

		/* Scraping all truck from amazon relay */

		List<WebElement> get_allTruck = p.getAll_Truck();
		for (int i11 = 0; i11 < get_allTruck.size(); i11++) {
			WebElement tdata = get_allTruck.get(i11);
			String length = tdata.getText();

			if (length.equals("THIRTY_FOUR_FOOT_TRUCK")) {
				String s = "34' Truck";
				set_pickup_truck.add(s);

			} else if (length.equals("THIRTY_TWO_FOOT_TRUCK")) {
				String s = "32' Truck";
				set_pickup_truck.add(s);

			} else if (length.equalsIgnoreCase("TWENTY_FOOT_TRUCK_CNG")) {
				String s = "20' Truck";
				set_pickup_truck.add(s);

			} else if (length.equalsIgnoreCase("FOURTEEN_FOOT_TRUCK_CNG")) {
				String s = "14' Truck";
				set_pickup_truck.add(s);

			} else if (length.equalsIgnoreCase("SEVEN_FOOT_TRUCK_ELECTRIC_AMT")) {
				String s = "7' Truck";
				set_pickup_truck.add(s);
			} else {
				set_pickup_truck.add(length);
			}
		}
		System.out.println("Pickup Truck Size : " + set_pickup_truck.size());

		/* Scraping TR Id From amazon relay */
		try {
			List<WebElement> get_All_TR_ID = p.getAll_TR_ID();

			for (int i1 = 0; i1 < get_All_TR_ID.size(); i1++) {
				WebElement webElement1 = get_All_TR_ID.get(i1);
				String tRId = webElement1.getText();
				String substring = tRId.substring(3);
				set_TR_ID.add(substring);

			}
		} catch (Exception e3) {

			System.out.println("getAllTR_ID : " + e3.getMessage());
		}

		System.out.println("All TR ID Size : " + set_TR_ID.size());

		List<WebElement> tr_Two2 = p.getTR_Two();
		for (int i = 0; i < tr_Two2.size(); i++) {
			WebElement webElement = tr_Two2.get(i);
			String text = webElement.getText();
			String substring = text.substring(3);
			get_TR_ID_Two.add(substring);
		}

		/* scraping all addresses like pickup, all drop from amazon */
		List<WebElement> get_pickup_allData = p.getPickup_allData();

		for (int i = 0; i < get_pickup_allData.size(); i++) {
			WebElement get_all = get_pickup_allData.get(i);
			String get_all_pickup = get_all.getText();
			get_all_pickup_data.add(get_all_pickup);

		}

		/* dummy Size Fixed in the Lists */
		set_drop1_address.addAll(set_arrival_pickup_date);

		for (int i = 0; i < set_drop1_address.size(); i++) {
			set_drop1_address.set(i, "N/A");
		}
		System.out.println("set_drop1_address : " + set_drop1_address.size());
		set_drop2_address.addAll(set_drop1_address);
		set_drop3_address.addAll(set_drop1_address);
		set_drop4_address.addAll(set_drop1_address);
		set_drop5_address.addAll(set_drop1_address);
		set_drop6_address.addAll(set_drop1_address);
		set_drop7_address.addAll(set_drop1_address);
		set_drop8_address.addAll(set_drop1_address);
		set_drop9_address.addAll(set_drop1_address);
		set_drop10_address.addAll(set_drop1_address);
		set_drop11_address.addAll(set_drop1_address);
		set_drop12_address.addAll(set_drop1_address);
		set_drop13_address.addAll(set_drop1_address);
		set_drop14_address.addAll(set_drop1_address);
		set_drop15_address.addAll(set_drop1_address);
		set_TR_ID_Two.addAll(set_drop1_address);

		/* Arrangement all Booking addresses */
		for (int i = 0; i < get_all_pickup_data.size(); i++) {

			String string = get_all_pickup_data.get(i);
			String substring2 = string.substring(0, 2);
			String address_Index = substring2.trim();
			if (address_Index.equalsIgnoreCase("1")) {
				String substring = string.substring(6);
				set_pickup_address.add(substring);
			} else if (address_Index.equalsIgnoreCase("2")) {
				String substring = string.substring(6);
				String last111 = set_pickup_address.getLast();
				int indexOf1 = set_pickup_address.lastIndexOf(last111);
				String string2 = set_drop1_address.get(indexOf1);
				if (!string2.equalsIgnoreCase(substring)) {
					set_drop1_address.set(indexOf1, substring);
				}

			} else if (address_Index.equalsIgnoreCase("3")) {
				String substring = string.substring(6);
				String last111 = set_pickup_address.getLast();
				int indexOf1 = set_pickup_address.lastIndexOf(last111);
				String string2 = set_drop2_address.get(indexOf1);
				if (!string2.equalsIgnoreCase(substring)) {
					set_drop2_address.set(indexOf1, substring);
				}

			} else if (address_Index.equalsIgnoreCase("4")) {
				String substring = string.substring(6);
				String last111 = set_pickup_address.getLast();
				int indexOf1 = set_pickup_address.lastIndexOf(last111);
				String string2 = set_drop3_address.get(indexOf1);
				if (!string2.equalsIgnoreCase(substring)) {
					set_drop3_address.set(indexOf1, substring);
				}
			} else if (address_Index.equalsIgnoreCase("5")) {
				String substring = string.substring(6);
				String last111 = set_pickup_address.getLast();
				int indexOf1 = set_pickup_address.lastIndexOf(last111);
				String string2 = set_drop4_address.get(indexOf1);
				if (!string2.equalsIgnoreCase(substring)) {
					set_drop4_address.set(indexOf1, substring);
				}

			} else if (address_Index.equalsIgnoreCase("6")) {
				String substring = string.substring(6);
				String last111 = set_pickup_address.getLast();
				int indexOf1 = set_pickup_address.lastIndexOf(last111);
				String string2 = set_drop5_address.get(indexOf1);
				if (!string2.equalsIgnoreCase(substring)) {
					set_drop5_address.set(indexOf1, substring);
				}

			} else if (address_Index.equalsIgnoreCase("7")) {
				String substring = string.substring(6);
				String last111 = set_pickup_address.getLast();
				int indexOf1 = set_pickup_address.lastIndexOf(last111);
				String string2 = set_drop6_address.get(indexOf1);
				if (!string2.equalsIgnoreCase(substring)) {
					set_drop6_address.set(indexOf1, substring);
				}

			} else if (address_Index.equalsIgnoreCase("8")) {
				String substring = string.substring(6);
				String last111 = set_pickup_address.getLast();
				int indexOf1 = set_pickup_address.lastIndexOf(last111);
				String string2 = set_drop7_address.get(indexOf1);
				if (!string2.equalsIgnoreCase(substring)) {
					set_drop7_address.set(indexOf1, substring);
				}

			} else if (address_Index.equalsIgnoreCase("9")) {
				String substring = string.substring(6);
				String last111 = set_pickup_address.getLast();
				int indexOf1 = set_pickup_address.lastIndexOf(last111);
				String string2 = set_drop8_address.get(indexOf1);
				if (!string2.equalsIgnoreCase(substring)) {
					set_drop8_address.set(indexOf1, substring);
				}

			} else if (address_Index.equalsIgnoreCase("10")) {
				String substring = string.substring(6);
				String last111 = set_pickup_address.getLast();
				int indexOf1 = set_pickup_address.lastIndexOf(last111);
				String string2 = set_drop9_address.get(indexOf1);
				if (!string2.equalsIgnoreCase(substring)) {
					set_drop9_address.set(indexOf1, substring);
				}

			} else if (address_Index.equalsIgnoreCase("11")) {
				String substring = string.substring(6);
				String last111 = set_pickup_address.getLast();
				int indexOf1 = set_pickup_address.lastIndexOf(last111);
				String string2 = set_drop10_address.get(indexOf1);
				if (!string2.equalsIgnoreCase(substring)) {
					set_drop10_address.set(indexOf1, substring);
				}

			} else if (address_Index.equalsIgnoreCase("12")) {
				String substring = string.substring(6);
				String last111 = set_pickup_address.getLast();
				int indexOf1 = set_pickup_address.lastIndexOf(last111);
				String string2 = set_drop11_address.get(indexOf1);
				if (!string2.equalsIgnoreCase(substring)) {
					set_drop11_address.set(indexOf1, substring);
				}

			} else if (address_Index.equalsIgnoreCase("13")) {
				String substring = string.substring(6);
				String last111 = set_pickup_address.getLast();
				int indexOf1 = set_pickup_address.lastIndexOf(last111);
				String string2 = set_drop12_address.get(indexOf1);
				if (!string2.equalsIgnoreCase(substring)) {
					set_drop12_address.set(indexOf1, substring);
				}

			} else if (address_Index.equalsIgnoreCase("14")) {
				String substring = string.substring(6);
				String last111 = set_pickup_address.getLast();
				int indexOf1 = set_pickup_address.lastIndexOf(last111);
				String string2 = set_drop13_address.get(indexOf1);
				if (!string2.equalsIgnoreCase(substring)) {
					set_drop13_address.set(indexOf1, substring);
				}

			} else if (address_Index.equalsIgnoreCase("15")) {
				String substring = string.substring(6);
				String last111 = set_pickup_address.getLast();
				int indexOf1 = set_pickup_address.lastIndexOf(last111);
				String string2 = set_drop14_address.get(indexOf1);
				if (!string2.equalsIgnoreCase(substring)) {
					set_drop14_address.set(indexOf1, substring);
				}

			} else if (address_Index.equalsIgnoreCase("16")) {
				String substring = string.substring(6);
				String last111 = set_pickup_address.getLast();
				int indexOf1 = set_pickup_address.lastIndexOf(last111);
				String string2 = set_drop15_address.get(indexOf1);
				if (!string2.equalsIgnoreCase(substring)) {
					set_drop15_address.set(indexOf1, substring);
				}

			}
		}

		/* insert the TR Number in List */
		for (int i = 0; i < set_click_BTN.size(); i++) {
			Integer integer = set_click_BTN.get(i);
			String string = get_TR_ID_Two.get(i);
			set_TR_ID_Two.set(integer, string);
		}
		System.out.println("Second Tr : " + set_TR_ID_Two);
		/* Json Formating Convert to All Linked Lists */

		try {
			for (int j = 0; j < set_allPickup_ID.size(); j++) {

				JSONObject jo = new JSONObject();

				Map<String, String> m1 = new LinkedHashMap<>();
				JSONArray ja1 = new JSONArray();
				String string2 = set_pickup_address.get(j);
				m1.put("pic_address", string2);
				ja1.add(m1);
				jo.put("pic_address", ja1);

				Map<String, String> m = new LinkedHashMap<>();

				JSONArray ja = new JSONArray();

				m = new LinkedHashMap<>(1);
				String string1d = set_drop1_address.get(j);
				if (string1d != "N/A") {
					m = new LinkedHashMap<>(1);
					m.put("drop_address", string1d);
					ja.add(m);
				}
				String string2d = set_drop2_address.get(j);
				if (string2d != "N/A") {
					m = new LinkedHashMap<>(1);
					m.put("drop_address", string2d);
					ja.add(m);
				}

				String string3d = set_drop3_address.get(j);
				if (string3d != "N/A") {
					m = new LinkedHashMap<>(1);
					m.put("drop_address", string3d);
					ja.add(m);
				}

				String string4d = set_drop4_address.get(j);
				if (string4d != "N/A") {
					m = new LinkedHashMap<>(1);
					m.put("drop_address", string4d);
					ja.add(m);
				}
				String string5d = set_drop5_address.get(j);
				if (string5d != "N/A") {
					m = new LinkedHashMap<>(1);
					m.put("drop_address", string5d);
					ja.add(m);
				}
				String string6d = set_drop6_address.get(j);
				if (string6d != "N/A") {
					m = new LinkedHashMap<>(1);
					m.put("drop_address", string6d);
					ja.add(m);
				}
				String string7d = set_drop7_address.get(j);
				if (string7d != "N/A") {
					m = new LinkedHashMap<>(1);
					m.put("drop_address", string7d);
					ja.add(m);
				}
				String string8d = set_drop8_address.get(j);
				if (string8d != "N/A") {
					m = new LinkedHashMap<>(1);
					m.put("drop_address", string8d);
					ja.add(m);
				}
				String string9d = set_drop9_address.get(j);
				if (string9d != "N/A") {
					m = new LinkedHashMap<>(1);
					m.put("drop_address", string9d);
					ja.add(m);
				}
				String string10d = set_drop10_address.get(j);
				if (string10d != "N/A") {
					m = new LinkedHashMap<>(1);
					m.put("drop_address", string10d);
					ja.add(m);
				}
				String string11d = set_drop11_address.get(j);
				if (string11d != "N/A") {
					m = new LinkedHashMap<>(1);
					m.put("drop_address", string11d);
					ja.add(m);
				}
				String string12d = set_drop12_address.get(j);
				if (string12d != "N/A") {
					m = new LinkedHashMap<>(1);
					m.put("drop_address", string12d);
					ja.add(m);
				}
				String string13d = set_drop13_address.get(j);
				if (string13d != "N/A") {
					m = new LinkedHashMap<>(1);
					m.put("drop_address", string13d);
					ja.add(m);
				}
				String string14d = set_drop14_address.get(j);
				if (string14d != "N/A") {
					m = new LinkedHashMap<>(1);
					m.put("drop_address", string14d);
					ja.add(m);
				}
				String string15d = set_drop15_address.get(j);
				if (string15d != "N/A") {
					m = new LinkedHashMap<>(1);
					m.put("drop_address", string15d);
					ja.add(m);
				}
				jo.put("drop_address", ja);

				String string = set_arrival_pickup_date.get(j);
				jo.put("pick_timestamp", string);

				String string1 = set_pickup_truck.get(j);
				jo.put("length", string1);

				String string7 = set_price_alldata.get(j);
				jo.put("customer_price", string7);

				JSONArray tr = new JSONArray();
				String string8 = set_TR_ID.get(j);
				tr.add(string8);
				String string9 = set_TR_ID_Two.get(j);
				if (string9 != "N/A") {
					tr.add(string9);
				}
				jo.put("tr_number", tr);

				String jsonString = jo.toString();
				json_AllData.add(jsonString);

				System.out.println(jsonString);

			}
		} catch (Exception e2) {

			System.out.println("Exception messages : " + e2.getMessage());
		}

		System.out.println("Json all Data Size : " + json_AllData.size());

		for (int i = 0; i < set_pickup_address.size(); i++) {
			String TR_ID_One = set_TR_ID.get(i);
			String TR_ID_Two = set_TR_ID_Two.get(i);
			String Time_Stamp = set_arrival_pickup_date.get(i);
			String Pickup_Address = set_pickup_address.get(i);
			String Drop01_Address = set_drop1_address.get(i);
			String Drop02_Address = set_drop2_address.get(i);
			String Drop03_Address = set_drop3_address.get(i);
			String Drop04_Address = set_drop4_address.get(i);
			String Drop05_Address = set_drop5_address.get(i);
			String Drop06_Address = set_drop6_address.get(i);
			String Drop07_Address = set_drop7_address.get(i);
			String Drop08_Address = set_drop8_address.get(i);
			String Drop09_Address = set_drop9_address.get(i);
			String Drop10_Address = set_drop10_address.get(i);
			String Drop11_Address = set_drop11_address.get(i);
			String Drop12_Address = set_drop12_address.get(i);
			String Drop13_Address = set_drop13_address.get(i);
			String Drop14_Address = set_drop14_address.get(i);
			String Drop15_Address = set_drop15_address.get(i);
			String Truck_Length = set_pickup_truck.get(i);
			String Price = set_price_alldata.get(i);
			String valueOf = String.valueOf(i);
			amazonRelay_Excel.put(valueOf,
					new Object[] { TR_ID_One, TR_ID_Two, Time_Stamp, Pickup_Address, Drop01_Address, Drop02_Address,
							Drop03_Address, Drop04_Address, Drop05_Address, Drop06_Address, Drop07_Address,
							Drop08_Address, Drop09_Address, Drop10_Address, Drop11_Address, Drop12_Address,
							Drop13_Address, Drop14_Address, Drop15_Address, Truck_Length, Price });
		}

		Set<String> keyid = amazonRelay_Excel.keySet();

		// writing the data into the sheets...

		for (String key : keyid) {

			row = spreadsheet.createRow(rowid++);
			Object[] objectArr = amazonRelay_Excel.get(key);
			int cellid = 0;

			for (Object obj : objectArr) {
				XSSFCell cell = row.createCell(cellid++);
				cell.setCellValue((String) obj);
			}
		}

		try {
			/* These are Steps For ApI Conventions */
			for (int i = 0; i < json_AllData.size(); i++) {
				String datas = json_AllData.get(i);

				HttpResponse<JsonNode> jsonresponse = Unirest
						.post("https://admin-staging.wowtruck.in/webservice/amazonrelaytobookingconversion")
						.header("Content-Type", "application/json").body(datas).asJson();
				System.out.println("status code :" + jsonresponse.getStatus());
				System.out.println("response body :" + jsonresponse.getBody());

				/* To Get into return API response body isn't Body Empty */
				String body = jsonresponse.getBody().toString();
				if (!body.equals("{}")) {

					org.json.JSONObject j = new org.json.JSONObject(datas);
					String string = j.get("tr_number").toString();
					org.json.JSONArray j1 = new org.json.JSONArray(string);
					String string2 = j1.get(0).toString();
					System.out.println("TR No : " + string2);

					/* get booking actual index from amazon relay to click */
					int actual_booking_index = 0;
					String TR_State = null;
					String trState = "TR IS NOT THERE";
					List<WebElement> get_CureentTR_Appears = p.getAll_TR_ID();
					for (int i1 = 0; i1 < get_CureentTR_Appears.size(); i1++) {
						WebElement webElement1 = get_CureentTR_Appears.get(i1);
						String tRId = webElement1.getText();
						String substring = tRId.substring(3);

						if (substring.equalsIgnoreCase(string2)) {
							actual_booking_index = i1;
							TR_State = "TR IS THERE";
							break;
						} else {
							TR_State = "TR IS NOT THERE";
						}
					}

					/* To check TR present or not */
					if (!TR_State.equals(trState)) {

						System.out.println("Booking_TR_Index : " + actual_booking_index);

						Object object = jsonresponse.getBody().getObject().getJSONObject("response").get("status");
						String string12 = object.toString();

						/* to click return status code from API and Book button Click */
						if (string12.equals("1")) {

							List<WebElement> wait_Booking_click = wait_all_Elements
									.until(ExpectedConditions.visibilityOfAllElements(p.getClickBooking()));
							WebElement webElement2 = wait_Booking_click.get(actual_booking_index);
							jsClick(webElement2);
							System.out.println("Booking button clicked : " + i);

							/* To Click Yes or No button for Booking Confirmation */
							if (true) {
								WebElement wait_Yes_click = wait_all_Elements
										.until(ExpectedConditions.elementToBeClickable(p.getYesClick()));
								String text = wait_Yes_click.getText();
								System.out.println("Booking confirm button clicked : " + text);
								jsClick(p.getNoClick());

							}
						}
					}
				}
			}
		} catch (UnirestException e11) {

			e11.printStackTrace();
		}

		/* TO clear all Linked list */
		json_AllData.clear();
		amazonRelay_Excel.clear();
		get_all_pickup_data.clear();
		set_pickup_address.clear();
		set_drop1_address.clear();
		set_drop2_address.clear();
		set_drop3_address.clear();
		set_drop4_address.clear();
		set_drop5_address.clear();
		set_drop6_address.clear();
		set_drop7_address.clear();
		set_drop8_address.clear();
		set_drop9_address.clear();
		set_drop10_address.clear();
		set_drop11_address.clear();
		set_drop12_address.clear();
		set_drop13_address.clear();
		set_drop14_address.clear();
		set_drop15_address.clear();
		set_arrival_pickup_date.clear();
		set_pickup_truck.clear();
		set_TR_ID.clear();
		set_pickupId.clear();
		set_price_alldata.clear();
		set_allPickup_ID.clear();
		set_click_BTN.clear();
		get_TR_ID_Two.clear();
		set_TR_ID_Two.clear();

		FileOutputStream out = new FileOutputStream(
				new File(".//src/test/AmazonRelayBooking" + System.currentTimeMillis() + ".xlsx"));

		workbook.write(out);
		out.close();
	}

}
