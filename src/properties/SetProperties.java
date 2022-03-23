package properties;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class SetProperties {
	public String resource = "config/setting.properties";
	public Properties properties = null;
	
	public SetProperties() throws Exception{
		properties = new Properties();
		ClassLoader cl;
		
		cl = Thread.currentThread().getContextClassLoader();
		
		URL url = cl.getResource("config/setting.properties");
		
		try {
			properties.load(url.openStream());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
