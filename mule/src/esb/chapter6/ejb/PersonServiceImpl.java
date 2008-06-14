package esb.chapter6.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class PersonServiceImpl implements PersonService {

    private static List<Person> personList = new ArrayList<Person>();
    
    {
        personList.add(new Person("John","Doe"));
        personList.add(new Person("Jan","Janssen"));
        personList.add(new Person("Jos","Dirksen"));
        personList.add(new Person("Tijs","Rademakers"));
    }
    
    @Override
    public List<Person> searchPersons(SearchQuery query) {
        ArrayList<Person> result = new ArrayList<Person>();
        for (Person person : personList) {
          if(person.getFirstName().equals(query.getFirstName()) &&
              person.getLastName().equals(query.getLastName()))
          
            result.add(person);
        }
        return result;
      }
}
