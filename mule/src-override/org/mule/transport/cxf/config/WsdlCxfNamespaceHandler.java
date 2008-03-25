/*
 * $Id: CxfNamespaceHandler.java 10621 2008-01-30 12:15:16Z dirk.olmes $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the MuleSource MPL
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.transport.cxf.config;

import org.mule.config.spring.handlers.AbstractMuleNamespaceHandler;
import org.mule.transport.cxf.wsdl.CxfWsdlConnector;

public class WsdlCxfNamespaceHandler extends AbstractMuleNamespaceHandler
{

    public void init()
    {
        registerMetaTransportEndpoints("wsdl-cxf");
        registerConnectorDefinitionParser(CxfWsdlConnector.class);
        //registerBeanDefinitionParser(FeaturesDefinitionParser.FEATURES, new FeaturesDefinitionParser());
    }

}
