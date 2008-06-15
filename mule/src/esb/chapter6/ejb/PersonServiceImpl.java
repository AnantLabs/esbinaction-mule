package esb.chapter6.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Init;
import javax.ejb.RemoteHome;
import javax.ejb.Remove;
import javax.ejb.Stateless;

@Stateless
@RemoteHome(PersonServiceEJBHome.class)
public class PersonServiceImpl implements PersonService {

    private static List<Person> personList = new ArrayList<Person>();
    
    {
        personList.add(new Person("Jos","Dirksen"));
        personList.add(new Person("Tijs","Rademakers"));
        personList.add(new Person("Jos","de Vries"));
        personList.add(new Person("Gijs","van Dongen"));
    }
    
    
    public List<Person> searchPersons(SearchQuery query) {
        
        ArrayList<Person> result = new ArrayList<Person>();
        
        for (Person person : personList) {
               result.add(person);
        }
        return result;
    }
    
    public void ejbCreate() {}
    
    /**
     * If you do not have an EJBHome or EJBLocalHome interface, this method
     * can be deleted.
     */
    @Init
    public void create(){}

    /**
     * This method corresponds to the following methods:
     *  - EJBObject.remove()
     *  - EJBHome.remove(ejbObject)
     *  - EJBLocalObject.remove()
     *  - EJBLocalHome.remove(ejbObject)
     *
     * If you do not have an EJBHome or EJBLocalHome interface, this method
     * can be deleted.
     */
    @Remove
    public void remove(){}    
}
