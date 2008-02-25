package esb.chapter6.ejb;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.EJBObject;

public interface PersonServiceEJBObject extends EJBObject {

	 public List<Person> searchPersons(SearchQuery query) throws RemoteException ;
}
