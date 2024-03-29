package esb.chapter7;

import java.util.List;

public interface ChamberOfCommerceService {

    public List<Company> findCompanies(String city, String streeName);

    public Company getCompany(String companyName);

    public void changeCompany(String companyName, Company companyInfo);

}
