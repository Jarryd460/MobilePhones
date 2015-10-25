package za.ac.cput.MobilePhones.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import za.ac.cput.MobilePhones.App;
import za.ac.cput.MobilePhones.conf.factory.OrderProductFactory;
import za.ac.cput.MobilePhones.domain.OrderProduct;

/**
 * Created by Matt on 2015/10/25.
 */

@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class OrderProductCrudTest extends AbstractTestNGSpringContextTests {

    private Long id;

    @Autowired
    OrderProductRepository repository;

    @Test
    public void testCreate() throws Exception {
        repository.deleteAll();
        OrderProduct orderProduct = OrderProductFactory.createOrderProduct(20);
        repository.save(orderProduct);
        id = orderProduct.getId();
        Assert.assertNotNull(orderProduct.getId());
    }

    @Test(dependsOnMethods = "testCreate")
    public void testRead() throws Exception {
        OrderProduct orderProduct = repository.findOne(id);
        Assert.assertEquals(orderProduct.getQuantity(), 20);
    }

    @Test(dependsOnMethods = "testRead")
    public void testUpdate() throws Exception {
        OrderProduct orderProduct = repository.findOne(id);
        OrderProduct newOrderProduct = new OrderProduct.Builder(orderProduct.getQuantity()).copy(orderProduct).quantity(15).build();
        repository.save(newOrderProduct);
        OrderProduct updatedOrderProduct = repository.findOne(id);
        Assert.assertEquals(updatedOrderProduct.getQuantity(), 15);
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() throws Exception {
        OrderProduct orderProduct = repository.findOne(id);
        repository.delete(orderProduct);
        OrderProduct newOrderProduct = repository.findOne(id);
        Assert.assertNull(newOrderProduct);
    }

}
