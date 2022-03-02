package epl.amazonrelay;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

@SuppressWarnings("unused")
public class Amazon_Relay_Runner_Class extends Amazon_Relay_Scrept_Class {
	@Test(dataProvider = "data", dataProviderClass = Amazon_Relay_POJO_Class.class)
	private void amazon_relay_login(String url, String userName, String Password) throws Exception {
		launching(url);
		login(userName, Password);
	}

	 @AfterClass
	 private void quit_Browser() {
	 closeBrowser();
	 }

}
