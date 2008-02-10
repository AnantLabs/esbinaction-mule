package org.esb.chapter7.wsdl;

import java.util.List;

import javax.jws.WebService;

public interface CoCService {
    
    List<Company> findCompanies(String city, String streeName);
    Company getCompany(String companyName);
    void changeCompany(String companyName, Company companyInfo);

}
