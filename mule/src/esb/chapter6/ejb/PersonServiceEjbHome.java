package esb.chapter6.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface PersonServiceEjbHome extends EJBHome {
    PersonServiceEJBObject create() throws CreateException, RemoteException;
}
