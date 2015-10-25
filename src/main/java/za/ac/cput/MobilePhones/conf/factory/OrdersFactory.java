package za.ac.cput.MobilePhones.conf.factory;

        import za.ac.cput.MobilePhones.domain.OrderProduct;
        import za.ac.cput.MobilePhones.domain.Orders;

        import java.math.BigDecimal;
        import java.util.List;

/**
 * Created by Kristen on 25/10/2015.
 */
public class OrdersFactory
{
    public static Orders createOrders(String orderStatus, String dateOrderPlaced, String dateOrderPaid,
                                      BigDecimal totalOrderPrice, List<OrderProduct> orderProductList)
    {
        return new Orders.Builder(orderStatus)
                .dateOrderPlaced(dateOrderPlaced)
                .dateOrderPaid(dateOrderPaid)
                .totalOrderPrice(totalOrderPrice)
                .orderProductList(orderProductList)
                .build();
    }
}
