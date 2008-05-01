package esb.chapter8.security.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

public class AuthorizationTest {

	@Test
	public void authorize() throws Exception {
		PostMethod method = new PostMethod("http://localhost:8888/authorization");
        method.setRequestEntity(new InputStreamRequestEntity(new ByteArrayInputStream(
        		"hello".getBytes())));
        HttpClient httpClient = new HttpClient();
        httpClient.getState().setCredentials(
        		new AuthScope("localhost", 8888, "mule-realm"), 
        		new UsernamePasswordCredentials("aeinstein", "secret"));
        method.setDoAuthentication(true);
        int state = httpClient.executeMethod(method);
        assertEquals(HttpServletResponse.SC_OK, state);
        System.out.println("received response " +  method.getResponseBodyAsString());
	}
	
	@Test
	public void failAuthorize() throws Exception {
		PostMethod method = new PostMethod("http://localhost:8888/authorization");
        method.setRequestEntity(new InputStreamRequestEntity(new ByteArrayInputStream(
        		"hello".getBytes())));
        HttpClient httpClient = new HttpClient();
        httpClient.getState().setCredentials(
        		new AuthScope("localhost", 8888, "mule-realm"), 
        		new UsernamePasswordCredentials("mborn", "secret"));
        method.setDoAuthentication(true);
        int state = httpClient.executeMethod(method);
        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, state);
        System.out.println("received response " +  method.getStatusLine() + method.getStatusText());
	}
	
}
