package esb.chapter8.security;

import java.io.ByteArrayInputStream;

import javax.servlet.http.HttpServletResponse;

import junit.framework.TestCase;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;

public class AuthorizationTest extends TestCase {

	public void testAuthorize() throws Exception {
		PostMethod method = new PostMethod("http://localhost:8888/AuthorizationHTTPService");
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
	
	public void testFailAuthorize() throws Exception {
		PostMethod method = new PostMethod("http://localhost:8888/AuthorizationHTTPService");
        method.setRequestEntity(new InputStreamRequestEntity(new ByteArrayInputStream(
        		"hello".getBytes())));
        HttpClient httpClient = new HttpClient();
        httpClient.getState().setCredentials(
        		new AuthScope("localhost", 8888, "mule-realm"), 
        		new UsernamePasswordCredentials("mborn", "secret"));
        method.setDoAuthentication(true);
        int state = httpClient.executeMethod(method);
        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, state);
	}
	
}
