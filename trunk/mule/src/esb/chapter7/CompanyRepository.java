package esb.chapter7;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;

/**
 * Simple class that serves as the backend where the companies are stored.
 * 
 * @author nl24167
 */
public class CompanyRepository{
    
    private Hashtable<String, Company> registry = new Hashtable<String, Company>();
    
    public void init() {
        addCompany1();
        addCompany2();
        adddCompany3();
    }
    
    public Company getCompany(String companyName) {
        return registry.get(companyName);
    }
    
    public List<Company> findCompanies(String city, String streetname) {
        List<Company> result = new ArrayList<Company>();
        for (Company company : registry.values()) {
            if ((company.getAddress().getCity().contains(city) || (city == null || city.length() == 0)) && 
                (company.getAddress().getStreet().contains(streetname) || (streetname == null || streetname.length() == 0))) {
                result.add(company);
            }
        }
        
        return result;
    }
    
    public void changeCompany(String companyName, Company newCompany) {
        if (registry.containsKey(companyName)) {
            registry.put(companyName, newCompany );
        }
    }
    
    private void addCompany1() {
        List<Director> directors = new ArrayList<Director>();
        Address address1 = new Address("Fourth Street.",117,"Los Angeles","CA","91475","USA");
        directors.add(new Director("John","Eckel",(new GregorianCalendar(1942,Calendar.JULY,6)).getTime(),address1));
        Address address2 = new Address("Fifth Street.",80,"New York","NY","58874","USA");
        directors.add(new Director("Jeff","Johnson",(new GregorianCalendar(1946,Calendar.SEPTEMBER,12)).getTime(),address2));
        
        Address companyAddress = new Address("Oceangate",100,"Los Angeles","CA","91150","USA");
        Company company = new Company(directors,"1","Holthouse Carlin & Van Tright LLP (HCVT)",companyAddress);
        registry.put(company.getName(), company);
    }
    
    private void addCompany2() {
        List<Director> directors = new ArrayList<Director>();
        Address address1 = new Address("First Street.",117,"Chicago","CA","80554","USA");
        directors.add(new Director("Jeff","Jameson",(new GregorianCalendar(1950,Calendar.JULY,12)).getTime(),address1));
        Address address2 = new Address("Church lane.",80,"London","LN","77887","UK");
        directors.add(new Director("Jack","Jones",(new GregorianCalendar(1951,Calendar.OCTOBER,28)).getTime(),address2));
        
        Address companyAddress = new Address("Artesia Blv.",8822,"Los Angeles","CA","90706","USA");
        Company company = new Company(directors,"2","TAXCO Payroll & Business Services",companyAddress);
        registry.put(company.getName(), company);
    }
    
    private void adddCompany3() {
        List<Director> directors = new ArrayList<Director>();
        Address address1 = new Address("Hope Street.",117,"Los Angeles","CA","90071","USA");
        directors.add(new Director("Pete","Jackson",(new GregorianCalendar(1973,Calendar.AUGUST,5)).getTime(),address1));
        Address address2 = new Address("South grand avenue.",80,"Los Angeles","LN","90101","USA");
        directors.add(new Director("Jill","Manson",(new GregorianCalendar(1972,Calendar.DECEMBER,10)).getTime(),address2));
        
        Address companyAddress = new Address("Flower Street",1700,"Los Angeles","CA","90071","USA");
        Company company = new Company(directors,"2","Gibson, Dunn & Crutcher LLP",companyAddress);
        registry.put(company.getName(), company);
    }    
}
