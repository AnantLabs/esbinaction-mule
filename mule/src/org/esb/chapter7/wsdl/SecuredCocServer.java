package org.esb.chapter7.wsdl;

import org.codehaus.xfire.XFire;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.server.http.XFireHttpServer;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.codehaus.xfire.service.invoker.BeanInvoker;
import org.esb.opensource.coc.CoCPortType;
import org.esb.opensource.coc.CoCServiceImpl;

public class SecuredCocServer {
    
    XFireHttpServer server;
    
    public void start() throws Exception
    {
        CocServiceImpl impl = new CocServiceImpl();
        impl.init();
        
        CoCServiceImpl generated = new CoCServiceImpl();
        generated.setDelegate(impl);
        
        
        // Create an XFire Service
        ObjectServiceFactory serviceFactory = new ObjectServiceFactory();
        Service service = serviceFactory.create(CoCPortType.class);
        service.setInvoker(new BeanInvoker(generated));
        
        // Register the service in the ServiceRegistry
        XFire xfire = XFireFactory.newInstance().getXFire();
        xfire.getServiceRegistry().register(service);
        
        // Start the HTTP server
        server = new XFireHttpServer();
        server.setPort(8181);
        server.start();
    }
    
    public void stop() throws Exception {
        server.stop();
    }

    
    public static void main(String[] args) throws Exception {
        SecuredCocServer server = new SecuredCocServer();
        server.start();
    }

}
