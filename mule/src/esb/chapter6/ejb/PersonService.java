package esb.chapter6.ejb;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface PersonService {
    
    public List<Person> searchPersons(SearchQuery query);

}
