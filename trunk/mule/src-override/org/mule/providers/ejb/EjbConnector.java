/*
 * $Id: EjbConnector.java 8077 2007-08-27 20:15:25Z aperepel $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.providers.ejb;

import org.apache.commons.collections.MapUtils;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.config.MuleProperties;
import org.mule.api.endpoint.EndpointURI;
import org.mule.api.endpoint.ImmutableEndpoint;
import org.mule.api.transport.DispatchException;
import org.mule.config.i18n.CoreMessages;
import org.mule.endpoint.MuleEndpoint;
import org.mule.endpoint.MuleEndpointURI;
import org.mule.transport.rmi.RmiConnector;
import org.mule.transport.rmi.i18n.RmiMessages;
import org.mule.util.ArrayUtils;
import org.mule.util.ClassUtils;

import java.lang.reflect.Method;
import java.net.UnknownHostException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJBObject;
import javax.rmi.PortableRemoteObject;

/**
 * Provides Connection configuration for EJB endpoints
 */

public class EjbConnector extends RmiConnector
{
    // Errorcodes
    public static final int EJB_SERVICECLASS_INVOCATION_FAILED = 2;

    public String getProtocol()
    {
        return "ejb";
    }
    
    public Method getEJBMethodObject(Object remoteObject, MuleEvent event)
    throws MuleException, NoSuchMethodException, ClassNotFoundException
{
    EndpointURI endpointUri = event.getEndpoint().getEndpointURI();

    String methodName = MapUtils.getString(endpointUri.getParams(), MuleProperties.MULE_METHOD_PROPERTY,
        null);

    if (null == methodName)
    {
        methodName = (String)event.getMessage().removeProperty(MuleProperties.MULE_METHOD_PROPERTY);

        if (null == methodName)
        {
            throw new DispatchException(
                RmiMessages.messageParamServiceMethodNotSet(), 
                event.getMessage(), event.getEndpoint());
        }
    }

    Class[] argTypes;

    // Parse method args
    Object args = event.getMessage().getProperty(RmiConnector.PROPERTY_SERVICE_METHOD_PARAM_TYPES);
    if (args instanceof List)
    {
        // MULE-1794 this used to take the first list entry as a string, splitting it
        // as for String below.
        argTypes = stringsToClasses((List) args);
    }
    else if (args instanceof String)
    {
        argTypes = stringsToClasses(Arrays.asList(((String) args).split(",")));
    }
    else
    {
        argTypes = ClassUtils.getClassTypes(event.transformMessage());
    }

    try
    {
        return remoteObject.getClass().getMethod(methodName, argTypes);
    }
    catch (NoSuchMethodException e)
    {
        // if no method can be found, we finally try to invoke the method based on name
        // instead of on arguments.
        Method foundMethod = getMethodbyName(remoteObject,methodName);
        
        if (foundMethod == null) {
            
            throw new NoSuchMethodException(
                CoreMessages.methodWithParamsNotFoundOnObject(methodName, ArrayUtils.toString(argTypes),
                    remoteObject.getClass()).toString());
        } else {
            return foundMethod;
        }

    }
    catch (SecurityException e)
    {
        throw e;
    }
}
    

    public Object getEJB3Object(ImmutableEndpoint endpoint) throws RemoteException, UnknownHostException
    {
        try
        {
            Object ref = getRemoteRef(endpoint);            
            return ref;
        }
        catch (Exception e)
        {
            throw new RemoteException("Remote EJBObject lookup failed for '" + endpoint.getEndpointURI(), e);
        }
    }
    
    private Method getMethodbyName(Object remoteObject, String methodName) {
        Method[] methods = remoteObject.getClass().getMethods();
        Method foundMethod = null;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                foundMethod = method;
                break;
            }
        }
        
        return foundMethod;
    }
}
