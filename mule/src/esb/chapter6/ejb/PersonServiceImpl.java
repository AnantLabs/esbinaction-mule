package esb.chapter6.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
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
        
        String searchFirst = query.getFirstName();
        String searchLast = query.getLastName();
        for (Person person : personList) {
               result.add(person);
        }
        return result;
    }
    
    public void ejbCreate() {
        System.out.println("In create");
      }
}
