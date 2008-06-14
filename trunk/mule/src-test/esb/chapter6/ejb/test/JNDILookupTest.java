package esb.chapter6.ejb.test;

import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;

import org.junit.Test;

import esb.chapter6.ejb.Person;
import esb.chapter6.ejb.PersonService;
import esb.chapter6.ejb.SearchQuery;

public class JNDILookupTest {
	
	@Test
	public void lookupJNDI() throws Exception {
		Properties p = new Properties();
		p.put("java.naming.factory.initial", "org.apache.openejb.client.RemoteInitialContextFactory");
		p.put("java.naming.provider.url", "ejbd://localhost:4201");
		// user and pass optional
		//p.put("java.naming.security.principal", "daniel");
		//p.put("java.naming.security.credentials", "password");

		InitialContext ctx = new InitialContext(p);
		try {
			PersonService service = (PersonService) ctx.lookup("PersonServiceImplRemote");
			List<Person> personList = service.searchPersons(new SearchQuery("Test", "Test"));
			System.out.println("personList " + personList);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
