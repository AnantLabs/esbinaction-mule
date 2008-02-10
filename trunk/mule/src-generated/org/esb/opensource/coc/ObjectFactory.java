
package org.esb.opensource.coc;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.esb.opensource.coc package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.esb.opensource.coc
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BoardOfDirectors }
     * 
     */
    public BoardOfDirectors createBoardOfDirectors() {
        return new BoardOfDirectors();
    }

    /**
     * Create an instance of {@link ListOfCompanies }
     * 
     */
    public ListOfCompanies createListOfCompanies() {
        return new ListOfCompanies();
    }

    /**
     * Create an instance of {@link Director }
     * 
     */
    public Director createDirector() {
        return new Director();
    }

    /**
     * Create an instance of {@link ChangeCompany }
     * 
     */
    public ChangeCompany createChangeCompany() {
        return new ChangeCompany();
    }

    /**
     * Create an instance of {@link FindCompanies }
     * 
     */
    public FindCompanies createFindCompanies() {
        return new FindCompanies();
    }

    /**
     * Create an instance of {@link FindCompaniesResponse }
     * 
     */
    public FindCompaniesResponse createFindCompaniesResponse() {
        return new FindCompaniesResponse();
    }

    /**
     * Create an instance of {@link GetCompanyResponse }
     * 
     */
    public GetCompanyResponse createGetCompanyResponse() {
        return new GetCompanyResponse();
    }

    /**
     * Create an instance of {@link Company }
     * 
     */
    public Company createCompany() {
        return new Company();
    }

    /**
     * Create an instance of {@link Address }
     * 
     */
    public Address createAddress() {
        return new Address();
    }

    /**
     * Create an instance of {@link GetCompany }
     * 
     */
    public GetCompany createGetCompany() {
        return new GetCompany();
    }

}
