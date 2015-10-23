package za.ac.cput.MobilePhones.domain;

import org.junit.Assert;
import org.junit.Test;
import za.ac.cput.MobilePhones.conf.factory.ProductPriceFactory;

import java.math.BigDecimal;

public class ProductPriceTest {

    @Test
    public void testCreate() {
        ProductPrice productPrice = ProductPriceFactory.createProductPrice("2015-09-23", new BigDecimal(11000));
        Assert.assertEquals(productPrice.getDateFrom(), "2015-09-23");
        Assert.assertEquals(productPrice.getProductPrice(), new BigDecimal(11000));
    }

    @Test
    public void testUpdate() {
        ProductPrice productPrice = ProductPriceFactory.createProductPrice("2015-09-23", new BigDecimal(11000));
        ProductPrice productPriceCopy = new ProductPrice.Builder(productPrice.getProductPrice()).copy(productPrice).productPrice(new BigDecimal(10000)).build();
        Assert.assertEquals(productPriceCopy.getDateFrom(), "2015-09-23");
        Assert.assertEquals(productPriceCopy.getProductPrice(), new BigDecimal(10000));
    }
}
