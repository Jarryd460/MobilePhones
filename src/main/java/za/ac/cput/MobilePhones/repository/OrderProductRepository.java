package za.ac.cput.MobilePhones.repository;

import org.springframework.data.repository.CrudRepository;
import za.ac.cput.MobilePhones.domain.OrderProduct;

/**
 * Created by Matt on 2015/10/25.
 */
public interface OrderProductRepository extends CrudRepository<OrderProduct,Long> {
}
