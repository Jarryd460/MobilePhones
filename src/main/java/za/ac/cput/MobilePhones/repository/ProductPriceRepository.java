package za.ac.cput.MobilePhones.repository;

import org.springframework.data.repository.CrudRepository;
import za.ac.cput.MobilePhones.domain.ProductPrice;


public interface ProductPriceRepository extends CrudRepository<ProductPrice,Long> {
}
