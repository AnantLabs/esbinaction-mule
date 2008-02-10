package org.esb.opensource.coc;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.jws.WebService;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.beanutils.BeanUtils;
import org.esb.chapter7.wsdl.CocServiceImpl;

@WebService(serviceName = "CoCService", targetNamespace = "http://opensource.esb.org/CoC/", endpointInterface = "org.esb.opensource.coc.CoCPortType")
public class CoCServiceImpl implements CoCPortType {
    
    private CocServiceImpl delegate;

    //------------------------------
    // Interface implementation
    //------------------------------
    
    /**
     * Return a company
     */
    public Company getCompany(String companyName) {
        org.esb.chapter7.wsdl.Company company = delegate
                .getCompany(companyName);

        return copyCompanyFromDomain(company);
    }

    /**
     * Change a companies registration
     */
    public void changeCompany(String companyName, Company companyInfo) {
        delegate.changeCompany(companyName, copyCompanyToDomain(companyInfo));
    }

    /**
     * List companies based on city / streetname
     */
    public ListOfCompanies findCompanies(String city, String streetName) {
        List<org.esb.chapter7.wsdl.Company> companies = delegate
        .findCompanies(city, streetName);
        
        ListOfCompanies result = new ListOfCompanies();
        for (org.esb.chapter7.wsdl.Company foundCompany : companies) {
            result.getCompany().add(copyCompanyFromDomain(foundCompany));    
        }
        
        return result;
    }
    
    //------------------------------
    // Public getters and setters
    //------------------------------

    public CocServiceImpl getDelegate() {
        return delegate;
    }

    public void setDelegate(CocServiceImpl delegate) {
        this.delegate = delegate;
    }

    //------------------------------
    // Some private helper classes
    //------------------------------
    
    private Company copyCompanyFromDomain(org.esb.chapter7.wsdl.Company company) {
        Company result = new Company();

        if (company != null) {
            // copy the address
            result.setAddress(copyAddressFromDomain(company.getAddress()));
            // some basic values
            result.setCocID(company.getCocID());
            result.setName(company.getName());
            
            // and all the directors
            BoardOfDirectors directors = new BoardOfDirectors();
            result.setBoardOfDirectors(directors);
            for (org.esb.chapter7.wsdl.Director director : company.getBoardOfDirectors()) {
                result.getBoardOfDirectors().getDirector().add(copyDirectoryFromDomain(director));
            } 
        }

        
        return result;
    }

    private Address copyAddressFromDomain(org.esb.chapter7.wsdl.Address address) {
        Address result = new Address();
        try {
            BeanUtils.copyProperties(result, address);
            result.setZipcode(address.getZipCode());
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        return result;
    }

    private Director copyDirectoryFromDomain(org.esb.chapter7.wsdl.Director director) {
        Director result = new Director();
        
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(director.getDateOfBirth());
        XMLGregorianCalendar calendar;
        try {
            calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (DatatypeConfigurationException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        
        result.setDateOfBirth(calendar);
        result.setFirstName(director.getFirstName());
        result.setLastName(director.getLastName());
        result.setAddress(copyAddressFromDomain(director.getAddress()));
        
        return result;
    }
    
    private org.esb.chapter7.wsdl.Company copyCompanyToDomain(Company company) {
        org.esb.chapter7.wsdl.Company result = new org.esb.chapter7.wsdl.Company();

        // copy the address
        result.setAddress(copyAddressToDomain(company.getAddress()));
        // some basic values
        result.setCocID(company.getCocID());
        result.setName(company.getName());
        
        // and all the directors
        result.setBoardOfDirectors(new ArrayList<org.esb.chapter7.wsdl.Director>());
        for (Director director : company.getBoardOfDirectors().getDirector()) {
            result.getBoardOfDirectors().add(copyDirectorFromDomain(director));
        }
        
        return result;
    }    
    
    
    private org.esb.chapter7.wsdl.Address copyAddressToDomain(Address address) {
        org.esb.chapter7.wsdl.Address result = new org.esb.chapter7.wsdl.Address();
        try {
            BeanUtils.copyProperties(result, address);
            result.setZipCode(address.getZipcode());
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        return result;
    }   
    
    private org.esb.chapter7.wsdl.Director copyDirectorFromDomain(Director director) {
        org.esb.chapter7.wsdl.Director result = new org.esb.chapter7.wsdl.Director();
                
        result.setDateOfBirth(director.getDateOfBirth().toGregorianCalendar().getTime());
        result.setFirstName(director.getFirstName());
        result.setLastName(director.getLastName());
        result.setAddress(copyAddressToDomain(director.getAddress()));
        
        return result;
    }    
    
    
}
