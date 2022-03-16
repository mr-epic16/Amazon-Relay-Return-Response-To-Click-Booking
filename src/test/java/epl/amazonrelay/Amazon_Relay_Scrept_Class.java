
package epl.amazonrelay;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
	private static LinkedList<String> amazon_Json_Objects = new LinkedList<String>();

	public static void launching(String url) {
		// launchBrowser("chrome");
		chromeHeadless();
		launchUrl(url);
	}

	@SuppressWarnings({ "unchecked", "deprecation", "unused", })
	public static void login(String userName, String Password) throws Exception {

		sleepTime();
		Amazon_Relay_POJO_Class p = new Amazon_Relay_POJO_Class();
		WebDriverWait wait_all_Elements = new WebDriverWait(driver, 20);
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

		SimpleDateFormat sdf = new SimpleDateFormat("EEE dd MMM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, 2);
		final String Date_Compare_To_Click = sdf.format(c.getTime());

		int head_Click = 0;
		List<WebElement> firstclick2 = p.getFirstclick();
		List<WebElement> date_CompareToClick = p.getDate_CompareToClick();

		for (int i = 0; i < firstclick2.size(); i++) {

			WebElement webElement = firstclick2.get(i);
			WebElement webElement2 = date_CompareToClick.get(i);
			String text = webElement2.getText();
			String substring = text.substring(0, 10);
			if (Date_Compare_To_Click.equals(substring)) {
				head_Click = i;
				break;
			}
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
			if (head_Click == i) {
				break;
			}
			set_allPickup_ID.add(concat3);

		}
		System.out.println("All head Pickup ID Size : " + set_allPickup_ID.size());

		/* scraping for pickup Id data */
		List<WebElement> get_pickup_ID_fourLetter = p.getPickup_ID_fourLetter();
		List<WebElement> get_pickup_price = p.getPickup_price();

		for (int i1 = 0; i1 < get_pickup_ID_fourLetter.size(); i1++) {

			WebElement webElement = get_pickup_ID_fourLetter.get(i1);
			String pickuptext = webElement.getText();
			String substring2 = pickuptext.substring(0, 5);
			WebElement webElement2 = get_pickup_price.get(i1);
			String text = webElement2.getText();
			String substring = text.substring(1);
			String concat = substring2.concat(substring);
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
		System.out.println("Multiple TR Are There You Want to Click : " + set_click_BTN);

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
		int tr_row_two = clickSecond.size();
		if (tr_row_two != 0) {

			for (int i = 0; i < clickSecond.size(); i++) {
				WebElement webElement = clickSecond.get(i);
				jsClick(webElement);
			}

			List<WebElement> clickthird = p.getClickthird();
			int tr_row_three = clickthird.size();
			if (tr_row_three != 0) {
				for (int i = 0; i < clickthird.size(); i++) {
					WebElement webElement = clickthird.get(i);
					jsClick(webElement);
				}

				List<WebElement> clickfourth = p.getClickfourth();
				int tr_row_four = clickfourth.size();
				if (tr_row_four != 0) {
					for (int i = 0; i < clickfourth.size(); i++) {
						WebElement webElement = clickfourth.get(i);
						jsClick(webElement);

					}
					List<WebElement> clickfive = p.getClick_five();
					int tr_row_five = clickfive.size();
					if (tr_row_five != 0) {
						for (int i = 0; i < clickfive.size(); i++) {
							WebElement webElement = clickfive.get(i);
							jsClick(webElement);

						}
						List<WebElement> clicksix = p.getClick_six();
						int tr_row_six = clicksix.size();
						if (tr_row_six != 0) {
							for (int i = 0; i < clicksix.size(); i++) {
								WebElement webElement = clicksix.get(i);
								jsClick(webElement);

							}
							List<WebElement> clickseven = p.getClick_seven();
							int tr_row_seven = clickseven.size();
							if (tr_row_seven != 0) {
								for (int i = 0; i < clickseven.size(); i++) {
									WebElement webElement = clickseven.get(i);
									jsClick(webElement);

								}
								List<WebElement> clickeight = p.getClick_eight();
								int tr_row_eight = clickeight.size();
								if (tr_row_eight != 0) {
									for (int i = 0; i < clickeight.size(); i++) {
										WebElement webElement = clickeight.get(i);
										jsClick(webElement);

									}
									List<WebElement> clicknine = p.getClick_nine();
									int tr_row_nine = clicknine.size();
									if (tr_row_nine != 0) {
										for (int i = 0; i < clicknine.size(); i++) {
											WebElement webElement = clicknine.get(i);
											jsClick(webElement);

										}
										List<WebElement> clickten = p.getClick_ten();
										int tr_row_ten = clickten.size();
										if (tr_row_ten != 0) {
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

		/* scraping all price amount from amazon relay */
		List<WebElement> get_price = p.getPrice();

		for (int i = 0; i < get_price.size(); i++) {

			WebElement webElement = get_price.get(i);

			String text = webElement.getText();
			String replace = text.replaceAll(",", "");
			String substring = replace.substring(1);
			if (head_Click == i) {
				break;
			}
			set_price_alldata.add(substring);
		}

		System.out.println("Price all data Size : " + set_price_alldata.size());

		/* Scraping all truck from amazon relay */

		List<WebElement> get_allTruck = p.getAll_Truck();
		for (int i11 = 0; i11 < get_allTruck.size(); i11++) {
			WebElement tdata = get_allTruck.get(i11);
			String length = tdata.getText();

			if (head_Click == i11) {
				break;
			}
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
			} else if (length.equalsIgnoreCase("TEN_FOOT_TRUCK_AMT")) {
				String s = "10' Truck";
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

				address_Arrangements(string, set_pickup_address, set_drop1_address);

			} else if (address_Index.equalsIgnoreCase("3")) {

				address_Arrangements(string, set_pickup_address, set_drop2_address);

			} else if (address_Index.equalsIgnoreCase("4")) {

				address_Arrangements(string, set_pickup_address, set_drop3_address);

			} else if (address_Index.equalsIgnoreCase("5")) {

				address_Arrangements(string, set_pickup_address, set_drop4_address);

			} else if (address_Index.equalsIgnoreCase("6")) {

				address_Arrangements(string, set_pickup_address, set_drop5_address);

			} else if (address_Index.equalsIgnoreCase("7")) {

				address_Arrangements(string, set_pickup_address, set_drop6_address);

			} else if (address_Index.equalsIgnoreCase("8")) {

				address_Arrangements(string, set_pickup_address, set_drop7_address);

			} else if (address_Index.equalsIgnoreCase("9")) {

				address_Arrangements(string, set_pickup_address, set_drop8_address);

			} else if (address_Index.equalsIgnoreCase("10")) {

				address_Arrangements(string, set_pickup_address, set_drop9_address);

			} else if (address_Index.equalsIgnoreCase("11")) {

				address_Arrangements(string, set_pickup_address, set_drop10_address);

			} else if (address_Index.equalsIgnoreCase("12")) {

				address_Arrangements(string, set_pickup_address, set_drop11_address);

			} else if (address_Index.equalsIgnoreCase("13")) {

				address_Arrangements(string, set_pickup_address, set_drop12_address);

			} else if (address_Index.equalsIgnoreCase("14")) {

				address_Arrangements(string, set_pickup_address, set_drop13_address);

			} else if (address_Index.equalsIgnoreCase("15")) {

				address_Arrangements(string, set_pickup_address, set_drop14_address);

			} else if (address_Index.equalsIgnoreCase("16")) {

				address_Arrangements(string, set_pickup_address, set_drop15_address);
			}
		}

		/* insert the TR Number in List */
		for (int i = 0; i < set_click_BTN.size(); i++) {
			Integer integer = set_click_BTN.get(i);
			String string = get_TR_ID_Two.get(i);
			int size2 = set_arrival_pickup_date.size();
			set_TR_ID_Two.set(integer, string);
		}
		System.out.println("Second Tr : " + set_TR_ID_Two);

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

				String tr_number_Two = set_TR_ID_Two.get(j);
				if (tr_number_Two != "N/A") {
					multiple_tr_Array.add(tr_number_Two);
				}
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

		try {
			/* These are Steps For ApI Conventions */
			for (int i = 0; i < amazon_Json_Objects.size(); i++) {
				String datas = amazon_Json_Objects.get(i);

				HttpResponse<JsonNode> jsonresponse = Unirest
						.post("https://admin-staging.wowtruck.in/webservice/amazonrelaytobookingconversion")
						.header("Content-Type", "application/json").body(datas).asJson();
				System.out.println("status code :" + jsonresponse.getStatus());
				System.out.println("response body :" + jsonresponse.getBody());

				/* To Get into return API response body isn't Body Empty */
				String json_body = jsonresponse.getBody().toString();
				if (!json_body.equals("{}")) {

					org.json.JSONObject j = new org.json.JSONObject(datas);
					String string = j.get("tr_number").toString();
					org.json.JSONArray j1 = new org.json.JSONArray(string);
					String string2 = j1.get(0).toString();
					System.out.println("TR No : " + string2);

					/* get booking actual index from amazon relay to click */
					int actual_booking_index = 0;
					String TR_State = null;
					final String trState = "TR IS NOT THERE";
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

						String status = jsonresponse.getBody().getObject().getJSONObject("response").get("status")
								.toString();

						/* to click return status code from API and Book button Click */
						final String compare_return_response_status = "1";
						if (status.equals(compare_return_response_status)) {
							List<WebElement> wait_Booking_click = wait_all_Elements
									.until(ExpectedConditions.visibilityOfAllElements(p.getClickBooking()));
							WebElement webElement2 = wait_Booking_click.get(actual_booking_index);
							jsClick(webElement2);
							System.out.println("Booking button clicked : " + i);

							/* To Click Yes or No button for Booking Confirmation */
							if (true) {
								WebElement wait_yes_button_click = wait_all_Elements
										.until(ExpectedConditions.elementToBeClickable(p.getYesClick()));
								String text = wait_yes_button_click.getText();
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
		set_click_BTN.clear();
		get_TR_ID_Two.clear();
		set_TR_ID_Two.clear();

	}

}
