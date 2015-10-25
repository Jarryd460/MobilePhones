package za.ac.cput.MobilePhones.services;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.SpringApplicationConfiguration;
        import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
        import org.springframework.test.context.web.WebAppConfiguration;
        import org.testng.Assert;
        import org.testng.annotations.Test;
        import za.ac.cput.MobilePhones.App;
        import za.ac.cput.MobilePhones.conf.factory.OrderProductFactory;
        import za.ac.cput.MobilePhones.conf.factory.OrdersFactory;
        import za.ac.cput.MobilePhones.domain.OrderProduct;
        import za.ac.cput.MobilePhones.domain.Orders;
        import za.ac.cput.MobilePhones.repository.OrdersRepository;

        import java.math.BigDecimal;
        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Kristen on 25/10/2015.
 */

@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class OrdersServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private OrdersService service;
    private Long id;
    @Autowired
    private OrdersRepository repository;

    @Test
    public void create() throws Exception {
        repository.deleteAll();
        OrderProduct orderProduct = OrderProductFactory.createOrderProduct(20);
        List<OrderProduct> orderProductList = new ArrayList<OrderProduct>();
        orderProductList.add(orderProduct);
        Orders order = OrdersFactory.createOrders("Confirmed", "2015-10-10", "2015-10-10", new BigDecimal(200), orderProductList);
        service.create(order);
        id = order.getId();
        Assert.assertNotNull(order);
    }

    @Test(dependsOnMethods = "create")
    public void testGetOrder() throws Exception {
        Orders order = service.findById(id);
        Assert.assertEquals(order.getOrderStatus(), "Confirmed");
    }

    @Test(dependsOnMethods = "testGetOrder")
    public void testGetOrders() throws Exception {
        List<Orders> ordersList = service.findAll();
        Assert.assertEquals(ordersList.size(), 1);
    }

    @Test(dependsOnMethods = "testGetOrders")
    public void testGetOrderProducts() throws Exception {
        List<OrderProduct> orderProductList = service.findById(id).getOrderProductList();
        Assert.assertEquals(orderProductList.size(), 1);
    }

    @Test(dependsOnMethods = "testGetOrders")
    public void testEditOrder() throws Exception {
        Orders order = repository.findOne(id);
        Orders updatedOrder = new Orders.Builder(order.getOrderStatus()).copy(order).dateOrderPaid("2015-10-15").build();
        service.edit(updatedOrder);
        Orders newOrder = repository.findOne(id);
        Assert.assertEquals(newOrder.getDateOrderPaid(), "2015-10-15");
    }

    @Test(dependsOnMethods = "testEditOrder")
    public void testDeleteCustomer() throws Exception {
        Orders order = repository.findOne(id);
        service.delete(order);
        Orders newOrder = repository.findOne(id);
        Assert.assertNull(newOrder);
    }

}

