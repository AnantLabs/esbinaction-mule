package org.esb.chapter7.wsdl;

import java.util.Hashtable;
import java.util.List;

import javax.jws.WebService;

@WebService(targetNamespace = "http://opensource.esb.org/CoC/" )
public class CocServiceImpl implements CoCService {   
    
    private CompanyRepository repo = new CompanyRepository();
    
    public void init() {
        repo.init();
    }

    public void changeCompany(String companyName, Company companyInfo) {
        repo.changeCompany(companyName, companyInfo);
    }

    public List<Company> findCompanies(String city, String streetName) {
        return repo.findCompanies(city, streetName);
    }

    public Company getCompany(String companyName) {
        return repo.getCompany(companyName);
    }

}
