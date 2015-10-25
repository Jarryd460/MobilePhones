package za.ac.cput.MobilePhones.services;

        import za.ac.cput.MobilePhones.domain.OrderProduct;
        import za.ac.cput.MobilePhones.domain.Orders;

        import java.util.List;

/**
 * Created by Kristen on 25/10/2015.
 */
public interface OrdersService extends Services<Orders, Long>
{
    List<OrderProduct> getOrderProducts(Long id);
}

