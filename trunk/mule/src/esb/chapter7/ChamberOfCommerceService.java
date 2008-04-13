package esb.chapter7;

import java.util.List;

import javax.jws.WebService;

public interface ChamberOfCommerceService {
    
    List<Company> findCompanies(String city, String streeName);
    Company getCompany(String companyName);
    void changeCompany(String companyName, Company companyInfo);

}
