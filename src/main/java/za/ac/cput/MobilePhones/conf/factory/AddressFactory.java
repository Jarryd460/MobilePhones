package za.ac.cput.MobilePhones.conf.factory;

import za.ac.cput.MobilePhones.domain.Address;

/**
 * Created by student on 2015/10/18.
 */
public class AddressFactory {

    public static Address createAddress(String address, String city, String country, String zipCode) {
        return new Address.Builder(address).city(city).country(country).zipCode(zipCode).build();
    }

}
