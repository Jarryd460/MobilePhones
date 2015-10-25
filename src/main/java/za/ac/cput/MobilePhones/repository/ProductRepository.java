package za.ac.cput.MobilePhones.repository;

import org.springframework.data.repository.CrudRepository;
import za.ac.cput.MobilePhones.domain.Product;

/**
 * Created by student on 2015/10/18.
 */
public interface ProductRepository extends CrudRepository<Product,Long> {
}
