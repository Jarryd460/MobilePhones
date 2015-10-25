package za.ac.cput.MobilePhones.domain;

import org.junit.Assert;
import org.junit.Test;
import za.ac.cput.MobilePhones.conf.factory.AddressFactory;

/**
 * Created by student on 2015/10/18.
 */
public class AddressTest {

    @Test
    public void testCreate() {
        Address address = AddressFactory.createAddress("34 My Road", "Johannesburg", "South Africa", "3522");
        Assert.assertEquals(address.getAddress(), "34 My Road");
        Assert.assertEquals(address.getCity(), "Johannesburg");
        Assert.assertEquals(address.getZipCode(), "3522");
    }

    @Test
    public void testUpdate() {
        Address address = AddressFactory.createAddress("34 My Road", "Johannesburg", "South Africa", "3522");
        Address addressCopy = new Address.Builder(address.getAddress()).copy(address).city("Cape Town").zipCode("4321").build();
        Assert.assertEquals(addressCopy.getAddress(), "34 My Road");
        Assert.assertEquals(addressCopy.getCity(), "Cape Town");
        Assert.assertEquals(addressCopy.getZipCode(), "4321");
    }

}
