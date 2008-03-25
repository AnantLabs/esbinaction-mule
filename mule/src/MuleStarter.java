import org.mule.MuleServer;


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
