package esb.chapter6.ejb;

import java.io.Serializable;

public class SearchQuery implements Serializable {

    private String firstName;
    private String lastName;
    
    public SearchQuery() {
        super();
    }

    public SearchQuery(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }    
}
