package epl.amazonrelay;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Amazon_Relay_Runner_Class extends Amazon_Relay_Scrept_Class {
	@Test()
	private void amazon_relay_login() {

		try {
			launching();
			login();
		} catch (Exception e) {
			String errorMessage = e.toString();
			email_Notification(errorMessage);
		}
	}

	@AfterClass
	private void quit_Browser() {
		closeBrowser();

	}
}
