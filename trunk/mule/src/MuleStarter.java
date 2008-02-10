import org.mule.MuleServer;
import org.mule.routing.filters.xml.JXPathFilter;


public class MuleStarter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
       
	    
       
		try {
			MuleServer.main(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
