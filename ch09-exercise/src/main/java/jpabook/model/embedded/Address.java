package jpabook.model.embedded;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipcode;


    public Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }

    @Override
    public boolean equals(Object obj) {
        Address temp=(Address)obj;
        if(this.city==temp.getCity() && this.street==temp.getStreet() && temp.getZipcode() == temp.getZipcode())
            return true;
        else
            return false;

    }
}
