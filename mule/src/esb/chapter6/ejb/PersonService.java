package esb.chapter6.ejb;

import java.util.List;

import javax.ejb.Remote;

/**
 * Remote interface
 * 
 * @author nl24167
 */
@Remote
public interface PersonService {
    
    public List<Person> searchPersons(SearchQuery query);

}
