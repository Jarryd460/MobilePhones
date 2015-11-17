package za.ac.cput.MobilePhones.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import za.ac.cput.MobilePhones.App;
import za.ac.cput.MobilePhones.conf.factory.ProductFactory;
import za.ac.cput.MobilePhones.domain.Product;

import java.math.BigDecimal;

/**
 * Created by student on 2015/10/18.
 */

@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class ProductCrudTest extends AbstractTestNGSpringContextTests {

    private Long id;

    @Autowired
    ProductRepository repository;

    @Test
    public void testCreate() throws Exception {
        repository.deleteAll();
        Product product = ProductFactory.createProduct("Note 5", "Samsung", new BigDecimal(10500), "Android Lollipop 5.1", "120*50", "Yes", "15", "32", null, null, null, null);
        repository.save(product);
        id = product.getId();
        Assert.assertNotNull(product.getId());
    }

    @Test(dependsOnMethods = "testCreate")
    public void testRead() throws Exception {
        Product product = repository.findOne(id);
        Assert.assertEquals(product.getName(), "Note 5");
    }

    @Test(dependsOnMethods = "testRead")
    public void testUpdate() throws Exception {
        Product product = repository.findOne(id);
        Product newProduct = new Product.Builder(product.getName()).copy(product).memory("64").build();
        repository.save(newProduct);
        Product updatedProduct = repository.findOne(id);
        Assert.assertEquals(updatedProduct.getMemory(), "64");
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() throws Exception {
        Product product = repository.findOne(id);
        repository.delete(product);
        Product newProduct = repository.findOne(id);
        Assert.assertNull(newProduct);
    }

}
