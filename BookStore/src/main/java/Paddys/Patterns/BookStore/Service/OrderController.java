package Paddys.Patterns.BookStore.Service;

public class OrderController {

	 public PurchaseServiceFacade facade;
	    public boolean orderFulfilled=false;
	    public void orderProduct(Long id) {
	        orderFulfilled=facade.placeOrder(id);
	    }
	
}
