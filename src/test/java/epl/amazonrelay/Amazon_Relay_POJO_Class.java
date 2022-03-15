package epl.amazonrelay;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;

public class Amazon_Relay_POJO_Class extends Amazon_Relay_BassClass {

	public Amazon_Relay_POJO_Class() {

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@class='css-btlcoa']/div/div/span/span/div/span/span")
	private List<WebElement> Price;

	public List<WebElement> getPrice() {
		return Price;
	}

	@FindBy(id = "ap_email")
	private WebElement username;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(id = "signInSubmit")
	private WebElement login;

	@FindBy(id = "load-board-header")
	private WebElement loadboard;

	@FindBy(xpath = "//*[@id=\"rlb-top-nav-bar\"]/div/label[1]")
	private WebElement search;

	@FindBy(xpath = "//*[@class='css-9sd94s']/div[1]/div/div/div[1]")
	private List<WebElement> firstclick;

	/* separation data */
	@FindBy(className = "css-13cnl61")
	private List<WebElement> pickupdrop;

	public List<WebElement> getPickupdrop() {
		return pickupdrop;
	}

	public List<WebElement> getFirstclick() {
		return firstclick;
	}

	public WebElement getSearch() {
		return search;
	}

	public WebElement getLoadboard() {
		return loadboard;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getLogin() {
		return login;
	}

	public WebElement getUsername() {
		return username;
	}

	public List<WebElement> getAll_Head_Num() {
		return all_Head_Num;
	}

	public List<WebElement> getAll_Head_word() {
		return all_Head_word;
	}

	public List<WebElement> getAll_Head_price() {
		return all_Head_price;
	}

	@FindBy(xpath = "//*[@class='wo-card-header']/div/div/div[1]/div[1]/p")
	private List<WebElement> all_Head_Num;
	@FindBy(xpath = "//*[@class='wo-card-header']/div/div/div[1]/div[2]/span/p/span")
	private List<WebElement> all_Head_word;
	@FindBy(xpath = "//*[@class='css-btlcoa']/div/div/span/span/div/span/span")
	private List<WebElement> all_Head_price;

	@FindBy(xpath = "//*[@class='css-uz15kn']/div/div[2]/div/div[1]/div/div[1]/p")
	private List<WebElement> Pickup_ID_Number;

	public List<WebElement> getPickup_ID_Number() {
		return Pickup_ID_Number;
	}

	public List<WebElement> getPickup_ID_fourLetter() {
		return pickup_ID_fourLetter;
	}

	public List<WebElement> getPickup_price() {
		return pickup_price;
	}

	@FindBy(xpath = "//*[@class='wo-card-details']/div[1]/div/div/div[2]/div/div[1]")
	private List<WebElement> pickup_ID_fourLetter;
	@FindBy(xpath = "//*[@class='wo-card-details']/div[1]/div/div/div[6]/span/span/div/span/span")
	private List<WebElement> pickup_price;

	@FindBy(xpath = "//*[@class='wo-card-details']/div[1]/div/div/span/span")
	private List<WebElement> clickFirst;

	public List<WebElement> getClickFirst() {
		return clickFirst;
	}

	public List<WebElement> getClickSecond() {
		return clickSecond;
	}

	public List<WebElement> getClickthird() {
		return clickthird;
	}

	public List<WebElement> getClickfourth() {
		return clickfourth;
	}

	@FindBy(xpath = "//*[@class='wo-card-details']/div[2]/div/div/span/span")
	private List<WebElement> clickSecond;
	@FindBy(xpath = "//*[@class='wo-card-details']/div[3]/div/div/span/span")
	private List<WebElement> clickthird;
	@FindBy(xpath = "//*[@class='wo-card-details']/div[4]/div/div/span/span")
	private List<WebElement> clickfourth;
	@FindBy(xpath = "//*[@class='wo-card-details']/div[5]/div/div/span/span")
	private List<WebElement> click_five;

	public List<WebElement> getClick_five() {
		return click_five;
	}

	public List<WebElement> getClick_six() {
		return click_six;
	}

	public List<WebElement> getClick_seven() {
		return click_seven;
	}

	public List<WebElement> getClick_eight() {
		return click_eight;
	}

	@FindBy(xpath = "//*[@class='wo-card-details']/div[6]/div/div/span/span")
	private List<WebElement> click_six;
	@FindBy(xpath = "//*[@class='wo-card-details']/div[7]/div/div/span/span")
	private List<WebElement> click_seven;
	@FindBy(xpath = "//*[@class='wo-card-details']/div[8]/div/div/span/span")
	private List<WebElement> click_eight;
	@FindBy(xpath = "//*[@class='wo-card-details']/div[9]/div/div/span/span")
	private List<WebElement> click_nine;

	public List<WebElement> getClick_nine() {
		return click_nine;
	}

	public List<WebElement> getClick_ten() {
		return click_ten;
	}

	@FindBy(xpath = "//*[@class='wo-card-details']/div[10]/div/div/span/span")
	private List<WebElement> click_ten;

	@FindBy(xpath = "//*[@class='wo-card-header']/div/div/div[4]/div/span/span/div[1]/span/div")
	private List<WebElement> all_Truck;

	public List<WebElement> getTR_Two() {
		return TR_Two;
	}

	@FindBy(xpath = "//*[@class='wo-card-details']/div[2]/div/div/div[1]/div/b")
	private List<WebElement> TR_Two;

	public List<WebElement> getAll_Truck() {
		return all_Truck;
	}

	public List<WebElement> getArrival_pickup_date() {
		return arrival_pickup_date;
	}

	@FindBy(xpath = "//*[@class='css-uz15kn']/div/div[2]/div[1]/div[3]//span/span")
	private List<WebElement> arrival_pickup_date;

	public List<WebElement> getDate_CompareToClick() {
		return date_compare_click;
	}

	@FindBy(xpath = "//*[@class='wo-card']/div/div/div/div/div[1]/div[2]/p/span")
	private List<WebElement> date_compare_click;

	public List<WebElement> getAll_TR_ID() {
		return All_TR_ID;
	}

	@FindBy(xpath = "//*[@class='css-9sd94s']/div[2]/div[1]/div/div/div[1]/div/b")
	private List<WebElement> All_TR_ID;

	public List<WebElement> getPickup_allData() {
		return pickup_allData;
	}

	@FindBy(xpath = "//*[@class='stop-detail-row css-1mtwub4']/div[1]/div")
	private List<WebElement> pickup_allData;
	// *[@class='load-accordion__row']/div[2]/div/div/div/div/div[3]

	public List<WebElement> getallData() {
		return allData;
	}

	@FindBy(xpath = "//*[@class='load-accordion__row']/div[2]/div/div/div/div/div[3]/div/span/span")
	private List<WebElement> allData;

	@FindBy(xpath = "//*[@class='css-hgtzg0']/button[1]/span")
	private WebElement NoClick;

	public WebElement getNoClick() {
		return NoClick;
	}

	public List<WebElement> getClickBooking() {
		return ClickBooking;
	}

	@FindBy(xpath = "//*[@class='css-6pca0d']/span[2]/button/span")
	private List<WebElement> ClickBooking;
	@FindBy(xpath = "//*[@class='css-hgtzg0']/button[2]/span")
	private WebElement YesClick;

	public WebElement getYesClick() {
		return YesClick;
	}

	@DataProvider(name = "data")
	private Object[][] credentials() {
		return new Object[][] { {
				"https://www.amazon.in/ap/signin?openid.return_to=https%3A%2F%2Frelay.amazon.in%2F&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=amzn_relay_desktop_in&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&pageId=amzn_relay_desktop_in&language=en_IN",
				"mohanavelk@wowtruck.in", "wowtruck" } };

	}

	@FindBy(xpath = "(//*[@class='css-mah6cr'])[3]")
	private WebElement clickMoreBtn;

	public WebElement getClickMoreBtn() {
		return clickMoreBtn;
	}

	@FindBy(xpath = "//*[@class='css-dsf1ob']/div[3]/div[1]/div[1]/fieldset/div[1]/div/div/div/div/div/input")
	private WebElement PickEndBtn;

	public WebElement getPickEndBtn() {
		return PickEndBtn;
	}

}
