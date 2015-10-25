package za.ac.cput.MobilePhones.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by student on 2015/10/14.
 */

@Embeddable
public class Address implements Serializable {

    private String address;
    private String city;
    private String country;
    private String zipCode;

    private Address(){}

    public Address(Builder builder) {
        this.address = builder.address;
        this.city = builder.city;
        this.country = builder.country;
        this.zipCode = builder.zipCode;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public static class Builder {

        private String address;
        private String city;
        private String country;
        private String zipCode;

        public Builder(String address) {
            this.address = address;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder copy(Address address) {
            this.address = address.address;
            this.city = address.city;
            this.country = address.country;
            this.zipCode = address.zipCode;
            return this;
        }

        public Address build() {
            return new Address(this);
        }

    }

    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }

}
