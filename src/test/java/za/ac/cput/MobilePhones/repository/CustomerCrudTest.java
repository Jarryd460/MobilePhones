package za.ac.cput.MobilePhones.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import za.ac.cput.MobilePhones.App;
import za.ac.cput.MobilePhones.conf.factory.AddressFactory;
import za.ac.cput.MobilePhones.conf.factory.ContactFactory;
import za.ac.cput.MobilePhones.conf.factory.CustomerFactory;
import za.ac.cput.MobilePhones.conf.factory.NameFactory;
import za.ac.cput.MobilePhones.domain.Customer;

/**
 * Created by student on 2015/10/18.
 */

@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class CustomerCrudTest extends AbstractTestNGSpringContextTests {

    private Long id;

    @Autowired
    CustomerRepository repository;

    @Test
    public void testCreate() throws Exception {
        repository.deleteAll();
        Customer customer = CustomerFactory.createCustomer(NameFactory.createName("Sally", "Lee", "Abrahams"), "Female", "2015-10-13", ContactFactory.createContact("0219807439", "0829431236"), AddressFactory.createAddress("452 Sasol Street", "", "", ""), null, null, null, null);
        repository.save(customer);
        id = customer.getId();
        Assert.assertNotNull(customer.getId());
    }

    @Test(dependsOnMethods = "testCreate")
    public void testRead() throws Exception {
        Customer customer = repository.findOne(id);
        Assert.assertEquals(customer.getName().getFirstName(), "Sally");
    }

    @Test(dependsOnMethods = "testRead")
    public void testUpdate() throws Exception {
        Customer customer = repository.findOne(id);
        Customer newCustomer = new Customer.Builder(customer.getName()).copy(customer).address(AddressFactory.createAddress("43 Dragon Street", "", "", "")).build();
        repository.save(newCustomer);
        Customer updatedCustomer = repository.findOne(id);
        Assert.assertEquals(updatedCustomer.getAddress().getAddress(), "43 Dragon Street");
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() throws Exception {
        Customer customer = repository.findOne(id);
        repository.delete(customer);
        Customer newCustomer = repository.findOne(id);
        Assert.assertNull(newCustomer);
    }

}
