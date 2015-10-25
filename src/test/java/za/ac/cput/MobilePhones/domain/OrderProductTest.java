package za.ac.cput.MobilePhones.domain;

import org.junit.Assert;
import org.junit.Test;
import za.ac.cput.MobilePhones.conf.factory.OrderProductFactory;

public class OrderProductTest {

    @Test
    public void testCreate() {
        OrderProduct orderProduct = OrderProductFactory.createOrderProduct(20);
        Assert.assertEquals(orderProduct.getQuantity(), 20);
    }

    @Test
    public void testUpdate() {
        OrderProduct orderProduct = OrderProductFactory.createOrderProduct(20);
        OrderProduct orderProductCopy = new OrderProduct.Builder(15).build();
        Assert.assertEquals(orderProductCopy.getQuantity(), 15);
    }

}
