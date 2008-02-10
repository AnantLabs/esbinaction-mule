
package org.esb.opensource.coc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for company complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="company">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="boardOfDirectors" type="{http://opensource.esb.org/CoC/}boardOfDirectors"/>
 *         &lt;element name="address" type="{http://opensource.esb.org/CoC/}address"/>
 *       &lt;/sequence>
 *       &lt;attribute name="cocID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "company", propOrder = {
    "name",
    "boardOfDirectors",
    "address"
})
public class Company {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected BoardOfDirectors boardOfDirectors;
    @XmlElement(required = true)
    protected Address address;
    @XmlAttribute
    protected String cocID;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the boardOfDirectors property.
     * 
     * @return
     *     possible object is
     *     {@link BoardOfDirectors }
     *     
     */
    public BoardOfDirectors getBoardOfDirectors() {
        return boardOfDirectors;
    }

    /**
     * Sets the value of the boardOfDirectors property.
     * 
     * @param value
     *     allowed object is
     *     {@link BoardOfDirectors }
     *     
     */
    public void setBoardOfDirectors(BoardOfDirectors value) {
        this.boardOfDirectors = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setAddress(Address value) {
        this.address = value;
    }

    /**
     * Gets the value of the cocID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCocID() {
        return cocID;
    }

    /**
     * Sets the value of the cocID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCocID(String value) {
        this.cocID = value;
    }

}
