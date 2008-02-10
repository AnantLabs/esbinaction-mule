package esb.chapter10;

import org.mule.api.MuleMessage;
import org.mule.api.routing.filter.Filter;


public class DetourFilter implements Filter {

    private static boolean enabled = false;
    
    public static boolean getEnabled() {
        return enabled;
    }

    public static void setEnabled(boolean enabled) {
        DetourFilter.enabled = enabled;
    }

    public boolean accept(MuleMessage message) {
        return enabled;
    }

}
