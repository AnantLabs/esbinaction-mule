package org.esb.opensource.coc;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.jws.WebService;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.beanutils.BeanUtils;

import esb.chapter7.ChamberOfCommerceServiceImpl;

@WebService(serviceName = "CoCService", targetNamespace = "http://opensource.esb.org/CoC/", endpointInterface = "org.esb.opensource.coc.CoCPortType")
public class CoCServiceImpl implements CoCPortType {
    
    private ChamberOfCommerceServiceImpl delegate;

    //------------------------------
    // Interface implementation
    //------------------------------
    
    /**
     * Return a company
     */
    public Company getCompany(String companyName) {
        esb.chapter7.Company company = delegate
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
        List<esb.chapter7.Company> companies = delegate
        .findCompanies(city, streetName);
        
        ListOfCompanies result = new ListOfCompanies();
        for (esb.chapter7.Company foundCompany : companies) {
            result.getCompany().add(copyCompanyFromDomain(foundCompany));    
        }
        
        return result;
    }
    
    //------------------------------
    // Public getters and setters
    //------------------------------

    public ChamberOfCommerceServiceImpl getDelegate() {
        return delegate;
    }

    public void setDelegate(ChamberOfCommerceServiceImpl delegate) {
        this.delegate = delegate;
    }

    //------------------------------
    // Some private helper classes
    //------------------------------
    
    private Company copyCompanyFromDomain(esb.chapter7.Company company) {
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
            for (esb.chapter7.Director director : company.getBoardOfDirectors()) {
                result.getBoardOfDirectors().getDirector().add(copyDirectoryFromDomain(director));
            } 
        }

        
        return result;
    }

    private Address copyAddressFromDomain(esb.chapter7.Address address) {
        Address result = new Address();
        try {
            BeanUtils.copyProperties(result, address);
            result.setZipcode(address.getZipCode());
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        return result;
    }

    private Director copyDirectoryFromDomain(esb.chapter7.Director director) {
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
    
    private esb.chapter7.Company copyCompanyToDomain(Company company) {
        esb.chapter7.Company result = new esb.chapter7.Company();

        // copy the address
        result.setAddress(copyAddressToDomain(company.getAddress()));
        // some basic values
        result.setCocID(company.getCocID());
        result.setName(company.getName());
        
        // and all the directors
        result.setBoardOfDirectors(new ArrayList<esb.chapter7.Director>());
        for (Director director : company.getBoardOfDirectors().getDirector()) {
            result.getBoardOfDirectors().add(copyDirectorFromDomain(director));
        }
        
        return result;
    }    
    
    
    private esb.chapter7.Address copyAddressToDomain(Address address) {
        esb.chapter7.Address result = new esb.chapter7.Address();
        try {
            BeanUtils.copyProperties(result, address);
            result.setZipCode(address.getZipcode());
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        return result;
    }   
    
    private esb.chapter7.Director copyDirectorFromDomain(Director director) {
        esb.chapter7.Director result = new esb.chapter7.Director();
                
        result.setDateOfBirth(director.getDateOfBirth().toGregorianCalendar().getTime());
        result.setFirstName(director.getFirstName());
        result.setLastName(director.getLastName());
        result.setAddress(copyAddressToDomain(director.getAddress()));
        
        return result;
    } 
}
