/*
 * $Id: EjbMessageDispatcher.java 8077 2007-08-27 20:15:25Z aperepel $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.providers.ejb;

import java.rmi.RMISecurityManager;
import java.util.Collections;

import org.mule.DefaultMuleMessage;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.endpoint.MuleEndpoint;
import org.mule.transport.rmi.RmiMessageDispatcher;
;

/**
 * Invokes a method on an EJB object stored in Jndi. A dispatcher is created for each
 * type of object invoked
 */
public class EjbMessageDispatcher extends RmiMessageDispatcher
{
    private Object ejbObject;

    public EjbMessageDispatcher(MuleEndpoint endpoint)
    {
        super(endpoint);
    }
    
    protected void doConnect() throws Exception
    {
        if (ejbObject == null)
        {
            // Shouldn't all this be in the connector?
            String rmiPolicyPath = ((EjbConnector)super.getConnector()).getSecurityPolicy();
            System.setProperty("java.security.policy", rmiPolicyPath);

            // Set security manager
            if (System.getSecurityManager() == null)
            {
                System.setSecurityManager(new RMISecurityManager());
            }

            ejbObject = ((EjbConnector)super.getConnector()).getEJB3Object(endpoint);
        }
    }    
    
    protected void doDispatch(MuleEvent event) throws Exception
    {
        Object[] arguments = getArgs(event);
        if (invokedMethod == null)
        {
            invokedMethod = ((EjbConnector)super.getConnector()).getEJBMethodObject(ejbObject, event);
        }
        invokedMethod.invoke(ejbObject, arguments);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mule.umo.provider.UMOConnectorSession#send(org.mule.umo.UMOEvent)
     */
    public MuleMessage doSend(MuleEvent event) throws Exception
    {
        if (invokedMethod == null)
        {
            invokedMethod = ((EjbConnector)super.getConnector()).getEJBMethodObject(ejbObject, event);
        }

        Object[] arguments = getArgs(event);
        Object result = invokedMethod.invoke(ejbObject, arguments);

        if (result == null)
        {
            return null;
        }
        else
        {
            return new DefaultMuleMessage(super.getConnector().getMessageAdapter(result).getPayload(), Collections.EMPTY_MAP);
        }
    }
    
    private Object[] getArgs(MuleEvent event) throws TransformerException
    {
        Object payload = event.transformMessage();
        if (payload instanceof Object[])
        {
            return (Object[])payload;
        }
        else
        {
            return new Object[]{payload};
        }
    }
}