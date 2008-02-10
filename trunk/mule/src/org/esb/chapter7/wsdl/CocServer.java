package org.esb.chapter7.wsdl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSConstants;
import org.apache.ws.security.WSPasswordCallback;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.codehaus.xfire.XFire;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.security.wss4j.WSS4JInHandler;
import org.codehaus.xfire.security.wss4j.WSS4JOutHandler;
import org.codehaus.xfire.server.http.XFireHttpServer;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.codehaus.xfire.service.invoker.BeanInvoker;
import org.codehaus.xfire.util.dom.DOMInHandler;
import org.codehaus.xfire.util.dom.DOMOutHandler;
import org.esb.opensource.coc.CoCPortType;
import org.esb.opensource.coc.CoCServiceImpl;

/**
 * nl24167@nl24167-laptop:~/java/opensource-esb/workspace2/mule/src/org/esb/chapter7/wsdl$ keytool -genkey 
 *   -alias clientkey -keypass clientpass -keystore clientStore.jks -storepass storePass -dname "cn=clientKey" 
 *   -keyalg RSA
 * nl24167@nl24167-laptop:~/java/opensource-esb/workspace2/mule/src/org/esb/chapter7/wsdl$ keytool -genkey 
 *   -alias serverkey -keypass serverpass -keystore serverStore.jks -storepass storePass -dname "cn=serverKey" 
 *   -keyalg RSA
 * @author nl24167
 *
 */
public class CocServer {
    
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
        
        service.addInHandler(new DOMInHandler());
        service.addOutHandler(new DOMOutHandler());

        WSS4JInHandler wsIn = new WSS4JInHandler();
        wsIn.setProperty(WSHandlerConstants.SIG_PROP_FILE, "org/esb/chapter7/wsdl/server.crypto.properties");
        //wsIn.setProperty(WSHandlerConstants.DEC_PROP_FILE, "org/esb/chapter7/wsdl/server.enc.props");
        wsIn.setProperty(WSHandlerConstants.USER, "serverkey");
        wsIn.setProperty(WSHandlerConstants.PW_CALLBACK_CLASS, TestPwdCallback.class.getName());
        wsIn.setProperty(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE + " " + WSHandlerConstants.ENCRYPT);
        service.addInHandler(wsIn);
         
        
//        WSS4JOutHandler wsOut = new WSS4JOutHandler();
//        wsOut.setProperty(WSHandlerConstants.SIG_PROP_FILE, "org/esb/chapter7/wsdl/server.sig.props");
//        wsOut.setProperty(WSHandlerConstants.ENC_PROP_FILE, "org/esb/chapter7/wsdl/server.enc.props");
//        wsOut.setProperty(WSHandlerConstants.USER, "enckey");
//        wsOut.setProperty("password", "encPass");
//        wsOut.setProperty(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PASSWORD_TEXT);
//        wsOut.setProperty(WSHandlerConstants.PW_CALLBACK_CLASS, TestPwdCallback.class.getName());
//        wsOut.setProperty(WSHandlerConstants.ACTION, WSHandlerConstants.ENCRYPT);
//        service.addOutHandler(wsOut);
//        
//        WSS4JOutHandler wsOut2 = new WSS4JOutHandler();
//        wsOut2.setProperty(WSHandlerConstants.SIG_PROP_FILE, "org/esb/chapter7/wsdl/server.sig.props");
//        wsOut2.setProperty(WSHandlerConstants.ENC_PROP_FILE, "org/esb/chapter7/wsdl/server.enc.props");
//        wsOut2.setProperty(WSHandlerConstants.USER, "sigkey");
//        wsOut2.setProperty("password", "sigPass");
//        wsOut2.setProperty(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PASSWORD_TEXT);
//        wsOut2.setProperty(WSHandlerConstants.PW_CALLBACK_CLASS, TestPwdCallback.class.getName());
//        wsOut2.setProperty(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE);
//        service.addOutHandler(wsOut2);      
        
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
        CocServer server = new CocServer();
        server.start();
    }
    
}
