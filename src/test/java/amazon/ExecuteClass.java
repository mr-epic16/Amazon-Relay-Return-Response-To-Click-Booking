package amazon;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ExecuteClass extends AmazonProduction {

	@Test
	@Parameters({ "username", "password" })
	private void amazon_relay(String userName, String Password) throws Exception {

		launching();
		login(userName, Password);

	}

}
