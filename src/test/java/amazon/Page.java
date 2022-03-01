package amazon;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Page extends Baseclass {

	public Page() {

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

	@FindBy(xpath = "(//*[@class='css-mllder'])[1]")
	private WebElement first;

	@FindBy(xpath = "//*[@class='css-mllder']")
	private WebElement data1;

	@FindBy(xpath = "//*[@class='css-12x9kb6']")
	private WebElement btn;

	@FindBy(xpath = "(//*[@class='css-uz15kn'])[2]")
	private WebElement pick;

	@FindBy(xpath = "//*[@id=\"filter-summary-panel\"]/div[3]/div[1]/span/button")
	private WebElement scroll;

	@FindBy(xpath = "//*[text()='Saved searches']")
	private WebElement up;

	@FindBy(xpath = "//*[@class='css-9sd94s']/div[1]/div/div/div[1]")
	private List<WebElement> firstclick;

	@FindBy(className = "load-accordion-chevron")
	private List<WebElement> secondclk;

	@FindBy(className = "wo-card")
	private List<WebElement> finaldata;

	@FindBy(className = "css-4xwq1e")
	private List<WebElement> overallData;

	@FindBy(className = "css-uz15kn")
	private List<WebElement> headerdatafile;

	// new code elements

	@FindBy(className = "css-ly5121")
	private List<WebElement> newheader;

	@FindBy(className = "css-1mpo2bh")
	private List<WebElement> newrow;

	@FindBy(className = "css-uioxam")
	private List<WebElement> newstop;

	@FindBy(className = "css-13cnl61")
	private List<WebElement> stopdata;

	@FindBy(className = "css-1mpo2bh")
	private List<WebElement> stopnextdata;

	// seperation data

	@FindBy(className = "css-13cnl61")
	private List<WebElement> pickupdrop;

	public List<WebElement> getPickupdrop() {
		return pickupdrop;
	}

	public List<WebElement> getNewheader() {
		return newheader;
	}

	public List<WebElement> getNewrow() {
		return newrow;
	}

	public List<WebElement> getNewstop() {
		return newstop;
	}

	public List<WebElement> getStopdata() {
		return stopdata;
	}

	public List<WebElement> getStopnextdata() {
		return stopnextdata;
	}

	public List<WebElement> getHeaderdatafile() {
		return headerdatafile;
	}

	public List<WebElement> getOverallData() {
		return overallData;
	}

	public List<WebElement> getFinaldata() {
		return finaldata;
	}

	public List<WebElement> getSecondclk() {
		return secondclk;
	}

	public List<WebElement> getFirstclick() {
		return firstclick;
	}

	public WebElement getUp() {
		return up;
	}

	public WebElement getScroll() {
		return scroll;
	}

	public WebElement getPick() {
		return pick;
	}

	public WebElement getBtn() {
		return btn;
	}

	public WebElement getData1() {
		return data1;
	}

	public WebElement getFirst() {
		return first;
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

	@FindBy(className = "css-hhceur")
	private WebElement NoClick;

	public WebElement getNoClick() {
		return NoClick;
	}

	public List<WebElement> getClickBooking() {
		return ClickBooking;
	}

	@FindBy(xpath = "//*[@class='css-6pca0d']/span[2]/button/span")
	private List<WebElement> ClickBooking;
	@FindBy(className = "css-123rhkh")
	private WebElement YesClick;

	public WebElement getYesClick() {
		return YesClick;
	}

	@FindBy(xpath = "//*[@class='css-1yhpeek']")
	private List<WebElement> Pagination;

	public List<WebElement> getPagination() {
		return Pagination;
	}

}
