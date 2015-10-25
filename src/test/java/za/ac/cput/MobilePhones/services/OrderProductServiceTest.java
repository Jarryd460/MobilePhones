package za.ac.cput.MobilePhones.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import za.ac.cput.MobilePhones.App;
import za.ac.cput.MobilePhones.conf.factory.OrderProductFactory;
import za.ac.cput.MobilePhones.domain.OrderProduct;
import za.ac.cput.MobilePhones.repository.OrderProductRepository;

import java.util.List;

/**
 * Created by Matt on 2015/10/25.
 */

@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class OrderProductServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private OrderProductService service;
    private Long id;
    @Autowired
    private OrderProductRepository repository;

    @Test
    public void create() throws Exception {
        repository.deleteAll();
        OrderProduct orderProduct = OrderProductFactory.createOrderProduct(20);
        service.create(orderProduct);
        id = orderProduct.getId();
        Assert.assertNotNull(orderProduct);
    }

    @Test(dependsOnMethods = "create")
    public void testGetOrderProduct() throws Exception {
        OrderProduct orderProduct = service.findById(id);
        Assert.assertEquals(orderProduct.getQuantity(), 20);
    }

    @Test(dependsOnMethods = "testGetOrderProduct")
    public void testGetOrderProducts() throws Exception {
        List<OrderProduct> orderProductList = service.findAll();
        Assert.assertEquals(orderProductList.size(), 1);
    }

    @Test(dependsOnMethods = "testGetOrderProducts")
    public void testEditOrderProduct() throws Exception {
        OrderProduct orderProduct = repository.findOne(id);
        OrderProduct updatedOrderProduct = new OrderProduct.Builder(orderProduct.getQuantity()).copy(orderProduct).quantity(15).build();
        service.edit(updatedOrderProduct);
        OrderProduct newOrderProduct = repository.findOne(id);
        Assert.assertEquals(newOrderProduct.getQuantity(), 15);
    }

    @Test(dependsOnMethods = "testEditOrderProduct")
    public void testDeleteCustomer() throws Exception {
        OrderProduct orderProduct = repository.findOne(id);
        service.delete(orderProduct);
        OrderProduct newOrderProduct = repository.findOne(id);
        Assert.assertNull(newOrderProduct);
    }

}
