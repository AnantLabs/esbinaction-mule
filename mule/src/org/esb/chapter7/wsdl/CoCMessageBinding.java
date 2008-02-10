package org.esb.chapter7.wsdl;

import javax.xml.stream.XMLStreamReader;

public class CoCMessageBinding {

    public XMLStreamReader invoke(XMLStreamReader reader) {
        // do something with the stream;
        System.out.println("reader:" + reader);
        
        return reader;
    }
}
