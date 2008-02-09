package esb.chapter8.security.ssl.service;

import java.util.Iterator;
import java.util.Properties;

public class SystemService {
	
	public String hello(String name) {
		System.out.println("received message with name " + name);
		Properties properties = System.getProperties();
		Iterator itProp = properties.keySet().iterator();
		while(itProp.hasNext()) {
			String propName = (String) itProp.next();
			System.out.println("property " + propName + " has value " + properties.getProperty(propName));
		}
		return name;
	}

}
