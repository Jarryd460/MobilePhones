package za.ac.cput.MobilePhones.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import za.ac.cput.MobilePhones.App;
import za.ac.cput.MobilePhones.conf.factory.ProductPriceFactory;
import za.ac.cput.MobilePhones.domain.ProductPrice;

import java.math.BigDecimal;

@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class ProductPriceCrudTest extends AbstractTestNGSpringContextTests {

    private Long id;

    @Autowired
    ProductPriceRepository repository;

    @Test
    public void testCreate() throws Exception {
        repository.deleteAll();
        ProductPrice productPrice = ProductPriceFactory.createProductPrice("2015-09-23", new BigDecimal(11000));
        repository.save(productPrice);
        id = productPrice.getId();
        Assert.assertNotNull(productPrice.getId());
    }

    @Test(dependsOnMethods = "testCreate")
    public void testRead() throws Exception {
        ProductPrice productPrice = repository.findOne(id);
        Assert.assertEquals(productPrice.getDateFrom(), "2015-09-23");
    }

    @Test(dependsOnMethods = "testRead")
    public void testUpdate() throws Exception {
        ProductPrice productPrice = repository.findOne(id);
        ProductPrice newProductPrice = new ProductPrice.Builder(productPrice.getProductPrice()).copy(productPrice).dateFrom("2015-09-25").build();
        repository.save(newProductPrice);
        ProductPrice updatedProductPrice = repository.findOne(id);
        Assert.assertEquals(updatedProductPrice.getDateFrom(), "2015-09-25");
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() throws Exception {
        ProductPrice productPrice = repository.findOne(id);
        repository.delete(productPrice);
        ProductPrice newProductPrice = repository.findOne(id);
        Assert.assertNull(newProductPrice);
    }

}
