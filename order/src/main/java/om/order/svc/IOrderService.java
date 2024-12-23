package om.order.svc;

import om.order.dto.OrderReq;

public interface IOrderService {

    public void createOrder(OrderReq orderReq);

    /*
    ** To check items from shopping card out **
    * 1. View the list of items I am buying.
    * 2. Enter my billing address and shipping address.
    * 3. View the total amount (price in gross) due for payment.
    * 4. Confirm and proceed to checkout stage.
    * 5. A notification email should be received by me (customer).
     */
    //checkout functionality may be added separately in Cart microservice
}
