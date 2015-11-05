package za.ac.cput.MobilePhones.domain;

import org.junit.Assert;
import org.junit.Test;
import za.ac.cput.MobilePhones.conf.factory.AddressFactory;
import za.ac.cput.MobilePhones.conf.factory.ContactFactory;
import za.ac.cput.MobilePhones.conf.factory.CustomerFactory;
import za.ac.cput.MobilePhones.conf.factory.NameFactory;

/**
 * Created by student on 2015/10/18.
 */
public class CustomerTest {

    @Test
    public void testCreate() {
        Customer customer = CustomerFactory.createCustomer(NameFactory.createName("Sally", "Lee", "Abrahams"), "Female", "2015-10-13", ContactFactory.createContact("0219807439", "0829431236"), AddressFactory.createAddress("452 Sasol Street", "", "", ""), null, null);
        Assert.assertEquals(customer.getName().getFirstName(), "Sally");
        Assert.assertEquals(customer.getContact().getHomePhoneNumber(), "0219807439");
    }

    @Test
    public void testUpdate() {
        Customer customer = CustomerFactory.createCustomer(NameFactory.createName("Sally", "Lee", "Abrahams"), "Female", "2015-10-13", ContactFactory.createContact("0219807439", "0829431236"), AddressFactory.createAddress("452 Sasol Street", "", "", ""), null, null);
        Customer customerCopy = new Customer.Builder(customer.getName()).copy(customer).address(AddressFactory.createAddress("43 Dragon Street", "", "", "")).build();
        Assert.assertEquals(customer.getName().getFirstName(), "Sally");
        Assert.assertEquals(customerCopy.getAddress().getAddress(), "43 Dragon Street");
    }

}
