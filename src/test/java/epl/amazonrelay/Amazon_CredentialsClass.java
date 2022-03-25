package epl.amazonrelay;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Amazon_CredentialsClass {

	@SuppressWarnings("unused")
	public Properties credentials() {
		Properties proerty_credentials = new Properties();
		FileInputStream file = null;
		try {
			proerty_credentials
					.load(new FileInputStream(".//Amazon_Relay_Properties/Amazon_Relay_Credentials_Files.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return proerty_credentials;

	}

}
