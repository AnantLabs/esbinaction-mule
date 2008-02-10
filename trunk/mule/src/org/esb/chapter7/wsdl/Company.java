package org.esb.chapter7.wsdl;

import java.util.List;

public class Company {

    private List<Director> boardOfDirectors;
    private String cocID;
    private String name;
    private Address address;
    
    public Company(List<Director> boardOfDirectors, String cocID, String name,
            Address address) {
        super();
        this.boardOfDirectors = boardOfDirectors;
        this.cocID = cocID;
        this.name = name;
        this.address = address;
    }
    
    public Company() {
        // TODO Auto-generated constructor stub
    }

    public List<Director> getBoardOfDirectors() {
        return boardOfDirectors;
    }
    public void setBoardOfDirectors(List<Director> boardOfDirectors) {
        this.boardOfDirectors = boardOfDirectors;
    }
    public String getCocID() {
        return cocID;
    }
    public void setCocID(String cocID) {
        this.cocID = cocID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

}
