package za.ac.cput.MobilePhones.repository;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.SpringApplicationConfiguration;
        import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
        import org.springframework.test.context.web.WebAppConfiguration;
        import org.testng.Assert;
        import org.testng.annotations.Test;
        import za.ac.cput.MobilePhones.App;
        import za.ac.cput.MobilePhones.conf.factory.OrdersFactory;
        import za.ac.cput.MobilePhones.domain.Orders;

        import java.math.BigDecimal;

/**
 * Created by Kristen on 25/10/2015.
 */

@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class OrdersCrudTest extends AbstractTestNGSpringContextTests {

    private Long id;

    @Autowired
    OrdersRepository repository;

    @Test
    public void testCreate() throws Exception {
        repository.deleteAll();
        Orders order = OrdersFactory.createOrders("Confirmed", "2015-10-10", "2015-10-10", new BigDecimal(200), null);
        repository.save(order);
        id = order.getId();
        Assert.assertNotNull(order.getId());
    }

    @Test(dependsOnMethods = "testCreate")
    public void testRead() throws Exception {
        Orders order = repository.findOne(id);
        Assert.assertEquals(order.getOrderStatus(), "Confirmed");
    }

    @Test(dependsOnMethods = "testRead")
    public void testUpdate() throws Exception {
        Orders order = repository.findOne(id);
        Orders newOrder = new Orders.Builder(order.getOrderStatus()).copy(order).dateOrderPaid("2015-10-15").build();
        repository.save(newOrder);
        Orders updatedOrder = repository.findOne(id);
        Assert.assertEquals(updatedOrder.getDateOrderPaid(), "2015-10-15");
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() throws Exception {
        Orders order = repository.findOne(id);
        repository.delete(order);
        Orders newOrder = repository.findOne(id);
        Assert.assertNull(newOrder);
    }

}

