
package org.esb.opensource.coc;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import org.esb.opensource.coc.CoCPortType;

/**
 * This class was generated by Apache CXF (incubator) 2.0.5-incubator
 * Sun Apr 13 14:50:56 CEST 2008
 * Generated source version: 2.0.5-incubator
 * 
 */

@WebServiceClient(name = "CoCService", targetNamespace = "http://opensource.esb.org/CoC/", wsdlLocation = "file:/home/nl24167/java/book/workspace/workspace-mule/mule/ivy-test/exampleTarget/workspace/workspace-mule/mule/resources/chapter7/provide-wsdl-top-down/CoC.wsdl")
public class CoCService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://opensource.esb.org/CoC/", "CoCService");
    public final static QName CoCSoap = new QName("http://opensource.esb.org/CoC/", "CoCSoap");
    static {
        URL url = null;
        try {
            url = new URL("file:/home/nl24167/java/book/workspace/workspace-mule/mule/ivy-test/exampleTarget/workspace/workspace-mule/mule/resources/chapter7/provide-wsdl-top-down/CoC.wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from file:/home/nl24167/java/book/workspace/workspace-mule/mule/ivy-test/exampleTarget/workspace/workspace-mule/mule/resources/chapter7/provide-wsdl-top-down/CoC.wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public CoCService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public CoCService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CoCService() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns CoCSoap
     */
    @WebEndpoint(name = "CoCSoap")
    public CoCPortType getCoCSoap() {
        return super.getPort(CoCSoap, CoCPortType.class);
    }

}
