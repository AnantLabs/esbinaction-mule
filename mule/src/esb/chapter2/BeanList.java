/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package esb.chapter2;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author nl24167
 */
public class BeanList extends ArrayList implements Serializable {
    
    
    private static final long serialVersionUID = 13787489327489L;
    
    {
        super.add(new Customer());
        super.add(new Customer());
        super.add(new Customer());
        
        super.add(new Item());
        super.add(new Item());
        
        super.add(new Order());
        super.add(new Order());
        super.add(new Order());
    }

    public BeanList() {
    }
    
    public String getResultcode() {
        // always return success
        return "success";
    }
    
    public void setResultcode(String resultCode) {
        // noop
    }
}
