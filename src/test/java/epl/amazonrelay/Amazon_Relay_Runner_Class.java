package epl.amazonrelay;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Amazon_Relay_Runner_Class extends Amazon_Relay_Scrept_Class {
	@Test()
	private void amazon_relay_login() throws Exception {
		launching();
		login();
	}

	@AfterClass
	private void quit_Browser() {
		closeBrowser();

	}
}
