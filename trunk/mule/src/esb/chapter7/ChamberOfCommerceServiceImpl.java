package esb.chapter7;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "http://opensource.esb.org/CoC/" )
public class ChamberOfCommerceServiceImpl implements ChamberOfCommerceService {   
    
    private CompanyRepository repo = new CompanyRepository();
    
    public void init() {
        repo.init();
    }

    @WebMethod
    public void changeCompany(String companyName, Company companyInfo) {
        repo.changeCompany(companyName, companyInfo);
    }

    @WebMethod
    public List<Company> findCompanies(String city, String streetName) {
        return repo.findCompanies(city, streetName);
    }

    @WebMethod
    public Company getCompany(String companyName) {
        return repo.getCompany(companyName);
    }

}
