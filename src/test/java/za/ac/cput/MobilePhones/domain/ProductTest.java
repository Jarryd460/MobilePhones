package za.ac.cput.MobilePhones.domain;

import org.junit.Assert;
import org.junit.Test;
import za.ac.cput.MobilePhones.conf.factory.ProductFactory;

import java.math.BigDecimal;

/**
 * Created by student on 2015/10/18.
 */
public class ProductTest {

    @Test
    public void testCreate() {
        Product product = ProductFactory.createProduct("Note 5", "Samsung", new BigDecimal(10500), "Android Lollipop 5.1", "120*50", "Yes", "15", "32", null, null, null);
        Assert.assertEquals(product.getName(), "Note 5");
        Assert.assertEquals(product.getManufacturer(), "Samsung");
    }

    @Test
    public void testUpdate() {
        Product product = ProductFactory.createProduct("Note 5", "Samsung", new BigDecimal(10500), "Android Lollipop 5.1", "120*50", "Yes", "15", "32", null, null, null);
        Product productCopy = new Product.Builder(product.getName()).copy(product).memory("64").build();
        Assert.assertEquals(productCopy.getName(), "Note 5");
        Assert.assertEquals(productCopy.getMemory(), "64");
    }

}
