
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

@SuppressWarnings("unused")
public class Amazon_Relay_Scrept_Class extends Amazon_Relay_POJO_Class {

	// all pickup d1 d2 d3 d4 addresses
	static LinkedList<String> get_all_pickup_data = new LinkedList<String>();

	// set pick up & d1 d2 d3 d4
	static LinkedList<String> set_pickup_address = new LinkedList<String>();
	static LinkedList<String> set_drop1_address = new LinkedList<String>();
	static LinkedList<String> set_drop2_address = new LinkedList<String>();
	static LinkedList<String> set_drop3_address = new LinkedList<String>();
	static LinkedList<String> set_drop4_address = new LinkedList<String>();
	static LinkedList<String> set_drop5_address = new LinkedList<String>();

	// set pickup truck length
	static LinkedList<String> set_pickup_truck = new LinkedList<String>();

	/// set pickup arrival date
	static LinkedList<String> set_arrival_pickup_date = new LinkedList<String>();

	// price all pickup ad drop
	static LinkedList<String> set_price_alldata = new LinkedList<String>();

	// TR Adding
	static LinkedList<String> set_TR_ID = new LinkedList<String>();
	static LinkedList<String> get_TR_ID_Two = new LinkedList<String>();
	static LinkedList<String> set_TR_ID_Two = new LinkedList<String>();

	// all pickup & drop id
	static LinkedList<String> set_allPickup_ID = new LinkedList<String>();

	// Separate id pick d1 d2 d3 d4
	;
	static LinkedList<String> set_pickupId = new LinkedList<String>();
	static LinkedList<Integer> set_click_BTN = new LinkedList<Integer>();

	public static void launching(String url) {
		// launchBrowser("chrome");
		chromeHeadless();
		launchUrl(url);
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation", "resource", })
	public static void login(String userName, String Password) throws Exception {
		sleepTime();
		Amazon_Relay_POJO_Class p = new Amazon_Relay_POJO_Class();

		clickTheButton(p.getUsername());
		fillTheText(p.getUsername(), userName);

		clickTheButton(p.getPassword());
		fillTheText(p.getPassword(), Password);

		clickTheButton(p.getLogin());
		threadSleep(2000);

		try {
			jsClick(p.getLoadboard());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

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
						"Drop02_Address", "Drop03_Address", "Drop04_Address", "Drop05_Address", "Truck_Length",
						"Price" });
		int rowid = 0;

		List<WebElement> firstclick2 = p.getFirstclick();
		WebDriverWait wait = new WebDriverWait(driver, 7);
		for (int i = 0; i < firstclick2.size(); i++) {
			WebElement webElement = firstclick2.get(i);
			WebElement until = wait.until(ExpectedConditions.elementToBeClickable(webElement));
			jsClick(until);
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

		set_drop1_address.addAll(set_arrival_pickup_date);

		for (int i = 0; i < set_drop1_address.size(); i++) {
			set_drop1_address.set(i, "N/A");
		}
		set_drop2_address.addAll(set_drop1_address);
		set_drop3_address.addAll(set_drop1_address);
		set_drop4_address.addAll(set_drop1_address);
		set_drop5_address.addAll(set_drop1_address);
		set_TR_ID_Two.addAll(set_drop1_address);

		for (int i = 0; i < get_all_pickup_data.size(); i++) {

			String string = get_all_pickup_data.get(i);

			if (string.startsWith("1")) {
				String substring = string.substring(6);
				set_pickup_address.add(substring);

			} else if (string.startsWith("2")) {
				String substring = string.substring(6);
				String last111 = set_pickup_address.getLast();
				int indexOf1 = set_pickup_address.lastIndexOf(last111);
				String string2 = set_drop1_address.get(indexOf1);
				if (!string2.equalsIgnoreCase(substring)) {
					set_drop1_address.set(indexOf1, substring);
				}

			} else if (string.startsWith("3")) {
				String substring = string.substring(6);
				String last111 = set_pickup_address.getLast();
				int indexOf1 = set_pickup_address.lastIndexOf(last111);
				String string2 = set_drop2_address.get(indexOf1);
				if (!string2.equalsIgnoreCase(substring)) {
					set_drop2_address.set(indexOf1, substring);
				}

			}

			else if (string.startsWith("4")) {
				String substring = string.substring(6);
				String last111 = set_pickup_address.getLast();
				int indexOf1 = set_pickup_address.lastIndexOf(last111);
				String string2 = set_drop3_address.get(indexOf1);
				if (!string2.equalsIgnoreCase(substring)) {
					set_drop3_address.set(indexOf1, substring);
				}
			} else if (string.startsWith("5")) {
				String substring = string.substring(6);
				String last111 = set_pickup_address.getLast();
				int indexOf1 = set_pickup_address.lastIndexOf(last111);
				String string2 = set_drop4_address.get(indexOf1);
				if (!string2.equalsIgnoreCase(substring)) {
					set_drop4_address.set(indexOf1, substring);
				}

			} else if (string.startsWith("6")) {
				String substring = string.substring(6);
				String last111 = set_pickup_address.getLast();
				int indexOf1 = set_pickup_address.lastIndexOf(last111);
				String string2 = set_drop5_address.get(indexOf1);
				if (!string2.equalsIgnoreCase(substring)) {

					set_drop5_address.set(indexOf1, substring);
				}

			}
		}

		for (int i = 0; i < set_click_BTN.size(); i++) {
			Integer integer = set_click_BTN.get(i);
			String string = get_TR_ID_Two.get(i);
			set_TR_ID_Two.set(integer, string);
		}
		System.out.println("Second Tr : " + set_TR_ID_Two);
		LinkedList<String> json_AllData = new LinkedList<String>();
		try {
			for (int j = 0; j < set_allPickup_ID.size(); j++) {

				JSONObject jo = new JSONObject();

				Map<String, String> m1 = new LinkedHashMap();
				JSONArray ja1 = new JSONArray();
				String string2 = set_pickup_address.get(j);
				m1.put("pic_address", string2);
				ja1.add(m1);
				jo.put("pic_address", ja1);

				Map m = new LinkedHashMap();

				JSONArray ja = new JSONArray();

				m = new LinkedHashMap(1);
				String string3 = set_drop1_address.get(j);
				if (string3 != "N/A") {
					m = new LinkedHashMap(1);
					m.put("drop_address", string3);
					ja.add(m);
				}
				String string4 = set_drop2_address.get(j);
				if (string4 != "N/A") {
					m = new LinkedHashMap(1);
					m.put("drop_address", string4);
					ja.add(m);
				}

				String string5 = set_drop3_address.get(j);
				if (string5 != "N/A") {
					m = new LinkedHashMap(1);
					m.put("drop_address", string5);
					ja.add(m);
				}

				String string6 = set_drop4_address.get(j);
				if (string6 != "N/A") {
					m = new LinkedHashMap(1);
					m.put("drop_address", string6);
					ja.add(m);
				}
				String string61 = set_drop5_address.get(j);
				if (string61 != "N/A") {
					m = new LinkedHashMap(1);
					m.put("drop_address", string61);
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
			String Truck_Length = set_pickup_truck.get(i);
			String Price = set_price_alldata.get(i);
			String valueOf = String.valueOf(i);
			amazonRelay_Excel.put(valueOf,
					new Object[] { TR_ID_One, TR_ID_Two, Time_Stamp, Pickup_Address, Drop01_Address, Drop02_Address,
							Drop03_Address, Drop04_Address, Drop05_Address, Truck_Length, Price });
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
					int actual_index = 0;
					String TR_State = null;
					String trState = "TR IS NOT THERE";
					List<WebElement> get_CureentTR_Appears = p.getAll_TR_ID();
					for (int i1 = 0; i1 < get_CureentTR_Appears.size(); i1++) {
						WebElement webElement1 = get_CureentTR_Appears.get(i1);
						String tRId = webElement1.getText();
						String substring = tRId.substring(3);

						if (substring.equalsIgnoreCase(string2)) {
							actual_index = i1;
							TR_State = "TR IS THERE";
							break;
						} else {
							TR_State = "TR IS NOT THERE";
						}
					}

					/* To check TR present or not */
					if (!TR_State.equals(trState)) {

						System.out.println("Booking_TR_Index : " + actual_index);

						Object object = jsonresponse.getBody().getObject().getJSONObject("response").get("status");
						String string12 = object.toString();

						/* to click return status code from API and Book button Click */
						if (string12.equals("0")) {
							WebDriverWait wait1 = new WebDriverWait(driver, 5);
							List<WebElement> ClickBooking = p.getClickBooking();
							List<WebElement> until = wait1
									.until(ExpectedConditions.visibilityOfAllElements(ClickBooking));
							WebElement webElement2 = until.get(actual_index);
							jsClick(webElement2);
							System.out.println("Clicked Booking Button : " + i);

							/* To Click Yes or No button for Booking Confirmation */
							if (true) {
								WebElement until2 = wait1
										.until(ExpectedConditions.elementToBeClickable(p.getNoClick()));
								jsClick(until2);
								String text = until2.getText();
								System.out.println("Confirm Booking Decision Button : " + text);

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
