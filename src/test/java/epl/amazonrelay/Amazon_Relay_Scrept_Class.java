
package epl.amazonrelay;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mashape.unirest.http.HttpResponse;
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

	/* Store all pickup & drop id */
	private static LinkedList<String> set_allPickup_ID = new LinkedList<String>();

	/* Separate id pick up */

	private static LinkedList<String> set_pickupId = new LinkedList<String>();

	/* store json format data */
	private static LinkedList<String> amazon_Json_Objects = new LinkedList<String>();

	/* Amazon relay Credentials Class */
	private static Amazon_CredentialsClass property = new Amazon_CredentialsClass();;

	public static void launching() {
		Properties launching_Credentials = property.credentials();
		launchBrowser(launching_Credentials.getProperty("Wow.chromeType").trim());
		launchUrl(launching_Credentials.getProperty("Wow.Amazon_Url").trim());

	}

	@SuppressWarnings({ "unchecked", "unused", })
	public static void login() {
		Properties login_Credentials = property.credentials();
		sleepTime();
		Amazon_Relay_POJO_Class p = new Amazon_Relay_POJO_Class();
		WebDriverWait wait_all_Elements = new WebDriverWait(driver, 20);
		clickTheButton(p.getUsername());
		fillTheText(p.getUsername(), login_Credentials.getProperty("Wow.Amazon_Username").trim());

		clickTheButton(p.getPassword());
		fillTheText(p.getPassword(), login_Credentials.getProperty("Wow.Amazon_UserPassword").trim());

		clickTheButton(p.getLogin());

		WebElement loadboard_Wait = wait_all_Elements.until(ExpectedConditions.elementToBeClickable(p.getLoadboard()));
		jsClick(loadboard_Wait);

		jsClick(p.getSearch());

		navigateRefresh();

		clickTheButton(p.getClickMoreBtn());

		clickTheButton(p.getstop_Point_Click());
		String drop_points = login_Credentials.getProperty("Wow.Multi_Drop_Filter").trim();
		switch (drop_points) {
		case "2":
			clickTheButton(p.getStop_Point_Two());
			break;
		case "3":
			clickTheButton(p.getStop_Point_Three());
			break;
		case "4":
			clickTheButton(p.getStop_Point_Four());
			break;
		case "5":
			clickTheButton(p.getStop_Point_Five());
			break;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("EEE dd MMM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		String property2 = login_Credentials.getProperty("Wow.Date_Filter_Decision").trim();

		Integer date_Decision = Integer.valueOf(property2);
		c.add(Calendar.DATE, date_Decision);
		final String Date_Compare_To_Click = sdf.format(c.getTime());

		List<WebElement> firstclick2 = p.getFirstclick();
		List<WebElement> date_CompareToClick = p.getDate_CompareToClick();
		/* scraping all pick arrival date from amazon relay */
		List<WebElement> PickUp_TimeStamp = p.getAll_PickUp_TimeStamp();
		/* scraping all price amount from amazon relay */
		List<WebElement> get_price = p.getPrice();

		/* Scraping all truck from amazon relay */
		List<WebElement> get_allTruck = p.getAll_Truck();

		/* Scraping TR Id From amazon relay */
		List<WebElement> get_all_TR = p.getAll_TR_ID();

		/* scraping pickup address from amazon relay */
		List<WebElement> get_all_Head_Number = p.getAll_Head_Num();
		List<WebElement> get_all_Head_Word = p.getAll_Head_word();

		/* scraping for pickup Id data */
		List<WebElement> get_pickup_ID_fourLetter = p.getPickup_ID_fourLetter();
		List<WebElement> get_pickup_price = p.getPickup_price();

		List<WebElement> clickFirst = p.getClickFirst();
		for (int i = 0; i < firstclick2.size(); i++) {

			WebElement webElement = firstclick2.get(i);
			WebElement webElement2 = date_CompareToClick.get(i);
			String text = webElement2.getText();
			String substring = text.substring(0, 10);
			if (Date_Compare_To_Click.equals(substring)) {
				break;
			} else {
				WebElement wait_First_Click = wait_all_Elements
						.until(ExpectedConditions.elementToBeClickable(webElement));
				jsClick(wait_First_Click);

				WebElement get_PickUp_TimeStamp = PickUp_TimeStamp.get(i);
				get_Pickup_TimeStamp(get_PickUp_TimeStamp, set_arrival_pickup_date);

				WebElement get_Price = get_price.get(i);
				get_Price(get_Price, set_price_alldata);

				WebElement getlength = get_allTruck.get(i);
				get_All_Truck_Length(getlength, set_pickup_truck);

				WebElement get_TR_Num = get_all_TR.get(i);
				get_All_TR_ID(get_TR_Num, set_TR_ID);

				WebElement all_Head_Number = get_all_Head_Number.get(i);
				WebElement all_Head_Word = get_all_Head_Word.get(i);
				get_Top_Row_Header_Pickup_ID(all_Head_Number, get_Price, all_Head_Word, set_allPickup_ID);

				WebElement pickup_ID_fourLetter = get_pickup_ID_fourLetter.get(i);
				WebElement pickup_price = get_pickup_price.get(i);
				get_Tr_Row_Header_Pickup_ID(pickup_ID_fourLetter, pickup_price, set_pickupId);

				String Head_Row_Pickup_ID = set_allPickup_ID.getLast();
				String Tr_Row_pickupId = set_pickupId.getLast();
				if (!Head_Row_Pickup_ID.equalsIgnoreCase(Tr_Row_pickupId)) {
					WebElement to_Click_First_Tr_Row = clickFirst.get(i);
					jsClick(to_Click_First_Tr_Row);
				}
			}

		}
		System.out.println("Arrival pickup date Size : " + set_arrival_pickup_date.size());

		System.out.println("Price all data Size : " + set_price_alldata.size());

		System.out.println("Pickup Truck Size : " + set_pickup_truck.size());

		System.out.println("All TR ID Size : " + set_TR_ID.size());

		System.out.println("All head Pickup ID Size : " + set_allPickup_ID.size());

		System.out.println("Pickup Id Size : " + set_pickupId.size());

		List<WebElement> clickSecond = p.getClickSecond();
		int tr_row_two = clickSecond.size();
		if (tr_row_two != 0) {
			System.out.println("tr_row_two : " + tr_row_two);
			for (int i = 0; i < clickSecond.size(); i++) {
				WebElement webElement = clickSecond.get(i);
				jsClick(webElement);
			}

			List<WebElement> clickthird = p.getClickthird();
			int tr_row_three = clickthird.size();
			if (tr_row_three != 0) {
				System.out.println("tr_row_three : " + tr_row_three);
				for (int i = 0; i < clickthird.size(); i++) {
					WebElement webElement = clickthird.get(i);
					jsClick(webElement);
				}

				List<WebElement> clickfourth = p.getClickfourth();
				int tr_row_four = clickfourth.size();
				if (tr_row_four != 0) {
					System.out.println("tr_row_four : " + tr_row_four);
					for (int i = 0; i < clickfourth.size(); i++) {
						WebElement webElement = clickfourth.get(i);
						jsClick(webElement);

					}
					List<WebElement> clickfive = p.getClick_five();
					int tr_row_five = clickfive.size();
					if (tr_row_five != 0) {
						System.out.println("tr_row_five : " + tr_row_five);
						for (int i = 0; i < clickfive.size(); i++) {
							WebElement webElement = clickfive.get(i);
							jsClick(webElement);

						}
						List<WebElement> clicksix = p.getClick_six();
						int tr_row_six = clicksix.size();
						if (tr_row_six != 0) {
							System.out.println("tr_row_six : " + tr_row_six);
							for (int i = 0; i < clicksix.size(); i++) {
								WebElement webElement = clicksix.get(i);
								jsClick(webElement);

							}
							List<WebElement> clickseven = p.getClick_seven();
							int tr_row_seven = clickseven.size();
							if (tr_row_seven != 0) {
								System.out.println("tr_row_seven : " + tr_row_seven);
								for (int i = 0; i < clickseven.size(); i++) {
									WebElement webElement = clickseven.get(i);
									jsClick(webElement);

								}
								List<WebElement> clickeight = p.getClick_eight();
								int tr_row_eight = clickeight.size();
								if (tr_row_eight != 0) {
									System.out.println("tr_row_eight : " + tr_row_eight);
									for (int i = 0; i < clickeight.size(); i++) {
										WebElement webElement = clickeight.get(i);
										jsClick(webElement);

									}
									List<WebElement> clicknine = p.getClick_nine();
									int tr_row_nine = clicknine.size();
									if (tr_row_nine != 0) {
										System.out.println("tr_row_nine : " + tr_row_nine);
										for (int i = 0; i < clicknine.size(); i++) {
											WebElement webElement = clicknine.get(i);
											jsClick(webElement);

										}
										List<WebElement> clickten = p.getClick_ten();
										int tr_row_ten = clickten.size();
										if (tr_row_ten != 0) {
											System.out.println("tr_row_ten : " + tr_row_ten);
											for (int i = 0; i < clickten.size(); i++) {
												WebElement webElement = clickten.get(i);
												jsClick(webElement);

											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		/* scraping all addresses like pickup, all drop from amazon */
		List<WebElement> get_pickup_allData = p.getPickup_allData();
		List<WebElement> getallData = p.getallData();

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

		/* Arrangement all Booking addresses */
		for (int i = 0; i < get_all_pickup_data.size(); i++) {
			String string = get_all_pickup_data.get(i);
			String substring2 = string.substring(0, 2);
			String address_Index = substring2.trim();
			switch (address_Index) {
			case "1":
				pickup_Address_Arrangements(string, set_pickup_address);
				break;
			case "2":
				drops_Address_Arrangements(string, set_pickup_address, set_drop1_address);
				break;
			case "3":
				drops_Address_Arrangements(string, set_pickup_address, set_drop2_address);
				break;
			case "4":
				drops_Address_Arrangements(string, set_pickup_address, set_drop3_address);
				break;
			case "5":
				drops_Address_Arrangements(string, set_pickup_address, set_drop4_address);
				break;
			case "6":
				drops_Address_Arrangements(string, set_pickup_address, set_drop5_address);
				break;
			case "7":
				drops_Address_Arrangements(string, set_pickup_address, set_drop6_address);
				break;
			case "8":
				drops_Address_Arrangements(string, set_pickup_address, set_drop7_address);
				break;
			case "9":
				drops_Address_Arrangements(string, set_pickup_address, set_drop8_address);
				break;
			case "10":
				drops_Address_Arrangements(string, set_pickup_address, set_drop9_address);
				break;
			case "11":
				drops_Address_Arrangements(string, set_pickup_address, set_drop10_address);
				break;
			case "12":
				drops_Address_Arrangements(string, set_pickup_address, set_drop11_address);
				break;
			case "13":
				drops_Address_Arrangements(string, set_pickup_address, set_drop12_address);
				break;
			case "14":
				drops_Address_Arrangements(string, set_pickup_address, set_drop13_address);
				break;
			case "15":
				drops_Address_Arrangements(string, set_pickup_address, set_drop14_address);
				break;
			case "16":
				drops_Address_Arrangements(string, set_pickup_address, set_drop15_address);
				break;
			default:
				System.out.println("More than 16 drops are there");
				break;
			}

		}

		try {
			for (int j = 0; j < set_arrival_pickup_date.size(); j++) {

				/* Amazon Relay Json Object Creation */
				JSONObject amazon_Json_array = new JSONObject();

				/* Pick up object and pick array Objects creations */
				LinkedHashMap<String, String> pickup_Object = new LinkedHashMap<>();
				JSONArray pickup_array = new JSONArray();

				String string2 = set_pickup_address.get(j);
				pickup_Object.put("pic_address", string2);
				pickup_array.add(pickup_Object);
				amazon_Json_array.put("pic_address", pickup_array);

				/* Drops object and Drops array Objects creations */
				LinkedHashMap<String, String> drops_Object = new LinkedHashMap<>();
				JSONArray drops_array = new JSONArray();

				String drop1 = set_drop1_address.get(j);
				Json_Drops_Arrangements(drop1, drops_Object, drops_array);

				String drop2 = set_drop2_address.get(j);
				Json_Drops_Arrangements(drop2, drops_Object, drops_array);

				String drop3 = set_drop3_address.get(j);
				Json_Drops_Arrangements(drop3, drops_Object, drops_array);

				String drop4 = set_drop4_address.get(j);
				Json_Drops_Arrangements(drop4, drops_Object, drops_array);

				String drop5 = set_drop5_address.get(j);
				Json_Drops_Arrangements(drop5, drops_Object, drops_array);

				String drop6 = set_drop6_address.get(j);
				Json_Drops_Arrangements(drop6, drops_Object, drops_array);
				String drop7 = set_drop7_address.get(j);

				Json_Drops_Arrangements(drop7, drops_Object, drops_array);
				String drop8 = set_drop8_address.get(j);

				Json_Drops_Arrangements(drop8, drops_Object, drops_array);
				String drop9 = set_drop9_address.get(j);

				Json_Drops_Arrangements(drop9, drops_Object, drops_array);
				String drop10 = set_drop10_address.get(j);

				Json_Drops_Arrangements(drop10, drops_Object, drops_array);
				String drop11 = set_drop11_address.get(j);

				Json_Drops_Arrangements(drop11, drops_Object, drops_array);
				String drop12 = set_drop12_address.get(j);

				Json_Drops_Arrangements(drop12, drops_Object, drops_array);
				String drop13 = set_drop13_address.get(j);

				Json_Drops_Arrangements(drop13, drops_Object, drops_array);
				String drop14 = set_drop14_address.get(j);

				Json_Drops_Arrangements(drop14, drops_Object, drops_array);
				String drop15 = set_drop15_address.get(j);

				Json_Drops_Arrangements(drop15, drops_Object, drops_array);
				amazon_Json_array.put("drop_address", drops_array);

				/* json pick_timestamp creating */
				String pick_timestamp = set_arrival_pickup_date.get(j);
				amazon_Json_array.put("pick_timestamp", pick_timestamp);

				/* json truck_length creating */
				String truck_length = set_pickup_truck.get(j);
				amazon_Json_array.put("length", truck_length);

				/* json customer_price creating */
				String customer_price = set_price_alldata.get(j);
				amazon_Json_array.put("customer_price", customer_price);

				/* json tr_number creating one or Two */
				JSONArray multiple_tr_Array = new JSONArray();
				String tr_number_One = set_TR_ID.get(j);
				multiple_tr_Array.add(tr_number_One);

				amazon_Json_array.put("tr_number", multiple_tr_Array);

				/* those all objects are one Booking Stores in amazon_Json_Objects */
				String jsonString = amazon_Json_array.toString();
				amazon_Json_Objects.add(jsonString);

				System.out.println(jsonString);
			}
		} catch (Exception e2) {

			System.out.println("Exception messages : " + e2.getMessage());
		}
		System.out.println("Json all Data Size : " + amazon_Json_Objects.size());

		String Response_Num = login_Credentials.getProperty("Wow.Amazon_API_return_response_status").trim();
		String booking_Status = login_Credentials.getProperty("Wow.Amazon_Booking_Click_Decision").trim();
		System.out.println("Booking Status : " + booking_Status);

		/* These are Steps For ApI Conventions */
		for (int i = 0; i < amazon_Json_Objects.size(); i++) {
			String datas = amazon_Json_Objects.get(i);
			try {

				/*
				 * HttpResponse<JsonNode> jsonresponse =
				 * Unirest.post(login_Credentials.getProperty("Wow.Amazon_API_Url").trim())
				 * .header("Content-Type", "application/json").body(datas).asJson();
				 */

				HttpResponse<String> jsonresponse = Unirest
						.post(login_Credentials.getProperty("Wow.Amazon_API_Url").trim())
						.header("Content-Type", "application/json").body(datas).asString();

				/* To Get into return API response body isn't Body Empty */
				String json_body = jsonresponse.getBody().toString();
				System.out.println("response body : " + json_body);

				if (!json_body.equals("{}")) {

					/*
					 * String status =
					 * jsonresponse.getBody().getObject().getJSONObject("response").get("status")
					 * .toString();
					 */

					/* to click return status code from API and Book button Click */
					org.json.JSONObject jbody = new org.json.JSONObject(json_body);
					String status = jbody.getJSONObject("response").get("status").toString();
					// System.out.println("Status : " + status);

					if (status.equals(Response_Num)) {

						/*
						 * org.json.JSONObject j = new org.json.JSONObject(datas); String string =
						 * j.get("tr_number").toString(); org.json.JSONArray j1 = new
						 * org.json.JSONArray(string); String string2 = j1.get(0).toString();
						 */

						String Json_Tr_Id = set_TR_ID.get(i);
						System.out.println("TR No : " + Json_Tr_Id);

						/* get booking actual index from amazon relay to click */
						int actual_booking_index = 0;
						List<WebElement> get_CureentTR_Appears = p.getAll_TR_ID();
						for (int i1 = 0; i1 < get_CureentTR_Appears.size(); i1++) {
							WebElement webElement1 = get_CureentTR_Appears.get(i1);
							String tRId = webElement1.getText();
							String current_TR_ID_On_Amazon = tRId.substring(3);

							if (current_TR_ID_On_Amazon.equalsIgnoreCase(Json_Tr_Id)) {
								actual_booking_index = i1;
								break;
							}
						}

						System.out.println("Booking_TR_Index : " + actual_booking_index);

						/* To check TR present or not */
						List<WebElement> wait_Booking_click = wait_all_Elements
								.until(ExpectedConditions.visibilityOfAllElements(p.getClickBooking()));
						WebElement Booking_Index = wait_Booking_click.get(actual_booking_index);

						/*
						 * scrollDown(Booking_Index); captureScreenshot(Json_Tr_Id);
						 */

						System.out.println("Booking button clicked : " + i);

						/* To Click Yes or No button for Booking Confirmation */
						if (booking_Status.equalsIgnoreCase("Yes")) {
							jsClick(Booking_Index);
							WebElement wait_no_button_click = wait_all_Elements
									.until(ExpectedConditions.elementToBeClickable(p.getNoClick()));
							String text = wait_no_button_click.getText();
							System.out.println("Booking confirm button clicked : " + text);
							jsClick(wait_no_button_click);

							/* captureScreenshot(Json_Tr_Id); */
						} else if (booking_Status.equalsIgnoreCase("No")) {
							jsClick(Booking_Index);
							WebElement wait_no_button_click = wait_all_Elements
									.until(ExpectedConditions.elementToBeClickable(p.getNoClick()));
							String text = wait_no_button_click.getText();
							System.out.println("Booking confirm button clicked : " + text);
							jsClick(wait_no_button_click);

							/* captureScreenshot(Json_Tr_Id); */
						} else {
							System.out.println("Do not want to click booking order on Amazon Relay");
						}

					}
				}

			} catch (

			UnirestException e11) {
				e11.printStackTrace();
			}
		}

		/* TO clear all Linked list */
		amazon_Json_Objects.clear();
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

	}

}
