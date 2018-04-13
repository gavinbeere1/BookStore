package Paddys.Patterns.BookStore.Service;

import Paddys.Patterns.BookStore.model.Book;

public class PurchaseServiceFacadeImpl implements PurchaseServiceFacade {

	@Override
	public boolean placeOrder(Long id) {
	
		boolean orderFulfilled=false;
        Book book=new Book();
        id=book.getId();
        
        if(StockService.isAvailable(book))
        {
            boolean paymentConfirmed= PaymentService.makePayment();
            if(paymentConfirmed){
              
                orderFulfilled=true;
            }
        }
        return orderFulfilled;
	}



}
