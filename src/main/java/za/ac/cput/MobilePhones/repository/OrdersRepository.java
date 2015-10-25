package za.ac.cput.MobilePhones.repository;

        import org.springframework.data.repository.CrudRepository;
        import za.ac.cput.MobilePhones.domain.Orders;

/**
 * Created by Kristen on 25/10/2015.
 */
public interface OrdersRepository extends CrudRepository<Orders,Long>
{
}

