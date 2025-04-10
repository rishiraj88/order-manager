package om.order.service;

import om.order.dto.OrderReq;
import om.order.dto.OrderResp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    public void createOrder(OrderReq orderReq);

    public Page<OrderResp> getOrders(Pageable pageable);

    /*
    ** To check items from shopping card out **
    * 1. View the list of items I am buying.
    * 2. Enter my billing address and shipping address.
    * 3. View the total amount (price in gross) due for payment.
    * 4. Confirm and proceed to checkout stage.
    * 5. A notification email should be received by me (customer).
     */
    //Checkout functionality may be added separately in Cart microservice
}
