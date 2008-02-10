
package org.esb.opensource.coc;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "CoCPortType", targetNamespace = "http://opensource.esb.org/CoC/")
@SOAPBinding(use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface CoCPortType {


    @WebMethod(operationName = "getCompany", action = "http://opensource.esb.org/CoC/getCompany")
    @WebResult(name = "company", targetNamespace = "")
    public Company getCompany(
        @WebParam(name = "companyName", targetNamespace = "")
        String companyName);

    @WebMethod(operationName = "changeCompany", action = "http://opensource.esb.org/CoC/changeCompany")
    @Oneway
    public void changeCompany(
        @WebParam(name = "companyName", targetNamespace = "")
        String companyName,
        @WebParam(name = "companyInfo", targetNamespace = "")
        Company companyInfo);

    @WebMethod(operationName = "findCompanies", action = "http://opensource.esb.org/CoC/findCompanies")
    @WebResult(name = "companies", targetNamespace = "")
    public ListOfCompanies findCompanies(
        @WebParam(name = "city", targetNamespace = "")
        String city,
        @WebParam(name = "streetName", targetNamespace = "")
        String streetName);

}
