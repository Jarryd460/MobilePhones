package za.ac.cput.MobilePhones.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import za.ac.cput.MobilePhones.App;
import za.ac.cput.MobilePhones.conf.factory.*;
import za.ac.cput.MobilePhones.domain.Customer;
import za.ac.cput.MobilePhones.domain.Orders;
import za.ac.cput.MobilePhones.repository.CustomerRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/10/18.
 */

@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class CustomerServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private CustomerService service;
    private Long id;
    @Autowired
    private CustomerRepository repository;

    @Test
    public void create() throws Exception {
        repository.deleteAll();
        Orders order = OrdersFactory.createOrders("Confirmed", "2015-10-10", "2015-10-10", new BigDecimal(200), null);
        List<Orders> ordersList = new ArrayList<Orders>();
        ordersList.add(order);
        Customer customer = CustomerFactory.createCustomer(NameFactory.createName("Sally", "Lee", "Abrahams"), "Female", "2015-10-13", ContactFactory.createContact("0219807439", "0829431236"), AddressFactory.createAddress("452 Sasol Street", "", "", ""), ordersList, null, null, null);
        service.create(customer);
        id = customer.getId();
        Assert.assertNotNull(customer);
    }

    @Test(dependsOnMethods = "create")
    public void testGetCustomer() throws Exception {
        Customer customer = service.findById(id);
        Assert.assertEquals(customer.getName().getFirstName(), "Sally");
    }

    @Test(dependsOnMethods = "testGetCustomer")
    public void testGetCustomers() throws Exception {
        List<Customer> customerList = service.findAll();
        Assert.assertEquals(customerList.size(), 1);
    }

    @Test(dependsOnMethods = "testGetCustomers")
    public void testGetOrders() throws Exception {
        List<Orders> ordersList = service.getOrders(id);
        Assert.assertEquals(ordersList.size(), 1);
    }

    @Test(dependsOnMethods = "testGetCustomers")
    public void testEditCustomer() throws Exception {
        Customer customer = repository.findOne(id);
        Customer updatedCustomer = new Customer.Builder(customer.getName()).copy(customer).address(AddressFactory.createAddress("43 Dragon Street", "", "", "")).build();
        service.edit(updatedCustomer);
        Customer newCustomer = repository.findOne(id);
        Assert.assertEquals(newCustomer.getAddress().getAddress(), "43 Dragon Street");
    }

    @Test(dependsOnMethods = "testEditCustomer")
    public void testDeleteCustomer() throws Exception {
        Customer customer = repository.findOne(id);
        service.delete(customer);
        Customer newCustomer = repository.findOne(id);
        Assert.assertNull(newCustomer);
    }

}
