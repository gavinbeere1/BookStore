package Paddys.Patterns.BookStore.controller;

import static java.lang.Math.toIntExact;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;







import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Paddys.Patterns.BookStore.Service.CloneFactory;
import Paddys.Patterns.BookStore.Service.OrderController;
import Paddys.Patterns.BookStore.Service.PurchaseServiceFacadeImpl;
import Paddys.Patterns.BookStore.Service.User;
import Paddys.Patterns.BookStore.model.Book;
import Paddys.Patterns.BookStore.model.Discount;
import Paddys.Patterns.BookStore.model.PaymentDetails;
import Paddys.Patterns.BookStore.model.Rating;
import Paddys.Patterns.BookStore.model.Role;
import Paddys.Patterns.BookStore.repository.UserLoginRepository;
import Paddys.Patterns.BookStore.model.UserLogin;
import Paddys.Patterns.BookStore.repository.BookRepository;

@Controller
	public class BookController {
	
	
	
	@Autowired
	UserLoginRepository uR;

	@Autowired
	BookRepository bookRepository;
	
	   @RequestMapping(value={"/login"})
	   public String login(){
	       return "login";
	   }
	  

	    @GetMapping("/")
	    public String welcome() {
	        return "bookstore";
	    }
	

@RequestMapping(value="/registeruser", method=RequestMethod.GET)
	public String index(Model model) {
//		SecurityContextHolder.getContext().setAuthentication(null);
		
		model.addAttribute("user", new UserLogin()); //add model to view

		 return "index";
	}
@RequestMapping(value = "/registeruser", method = RequestMethod.POST)
public String addNewPost(@Valid UserLogin user, Model model, BindingResult errors) {
	
	Role role1 = new Role();
	Role role2 = new Role();

	role1.setRole("USER");
	role2.setRole("ADMIN");
	System.out.print(role1);
	user.setUserStatus(true);
	user.addRole(role1);
	
	uR.save(user);
	
	model.addAttribute("username", user.getUserName());
	
	
	
	return "result";
	
}


@RequestMapping(value={"/welcome"})
public String welcome2(){
	  
	      Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	      String email = loggedInUser.getName(); // getName() is springs way to get the logged in user name, which in my case is their email (i.e what they login with)

	      UserLogin user = uR.findByUserName(email);
	      
	      String result = null;
	      
	      if (user.getUserName().equals("Admin@gmail.com") && user.getPassword().equals("Admin"))
			{
				
				
				result = ("adminResult");
				//do whats mentioned below for all the entities
				//players result page should ask the player more questions (see the player model for these attributes, then save them to this "player" and point the player to the welcome page
			}

	      else
			{
			
				//here i will try and add a user role (admin) to the manager entity so they can login as admin
				result = ("userResult");
			}
			
			return result;
}


@RequestMapping(value="/showbooks2", method=RequestMethod.GET)
public String index23() {
    

    return "allbooks";

}

@RequestMapping(value="/searchP", method=RequestMethod.GET)
@ResponseBody
public List<Book> getResource2()
{
	  
	 
	   ArrayList<Book> cueList = bookRepository.findAll();	   

	    return cueList;
	   
}

@RequestMapping(value="/AddBook", method=RequestMethod.GET)
public String index2(Model model) {
    
	
	
	model.addAttribute("book", new Book()); //add model to view

    return "newbook";

}

////
@RequestMapping(value = "/AddBook", method = RequestMethod.POST)
public String addNewLeague(@Valid Book book, Model model, BindingResult errors) {

 
	
	//this is code for a new user

	bookRepository.save(book);
	


	return "adminResult";
	
}

@RequestMapping(value="/addtocart/{title}", method=RequestMethod.GET)
public String ViewBook(Model model, @PathVariable String title) {
	   
    Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
    String email = loggedInUser.getName(); // getName() is springs way to get the logged in user name, which in my case is their email (i.e what they login with)

    UserLogin user = uR.findByUserName(email);
	 
	  Book book = bookRepository.findByTitle(title);
	  
	  user.addBook(book);
	  
	  uR.save(user);
//	   model.addAttribute("team", team);
	   
	  
	   return "allbooks";
}


@RequestMapping(value = "/mycart", method=RequestMethod.GET)
	public String viewMyTeam(Model model) {


	

	
	  Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
      String email = loggedInUser.getName(); // getName() is springs way to get the logged in user name, which in my case is their email (i.e what they login with)

      UserLogin user = uR.findByUserName(email);
    
      double totalPrice = 0;
      
	   Set<Book> books = user.getBooks();
	   
	   for (Book b : books)
	   {
		   totalPrice = totalPrice + b.getPrice();
	   }
      
	   model.addAttribute("totalPrice", totalPrice);
      model.addAttribute("mycart", user);

	     return "mycart";
}

@RequestMapping(value="/removebook/{title}", method=RequestMethod.GET)
public String leaveTeam(@PathVariable String title) {
	   
	   
	   Book book = bookRepository.findByTitle(title);
    
	   Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	   String email = loggedInUser.getName();
	   
	   UserLogin user = uR.findByUserName(email);
	   
	   
	   user.RemoveBook(book);

		 
	   uR.save(user);
	  
	   
    return "redirect:/mycart";
}

@RequestMapping(value="/reviewABook/{title}", method=RequestMethod.POST)
public String reviewBook(@PathVariable String title, @Valid Rating rating, Model model, BindingResult errors) {
	   
	
	 Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
     String email = loggedInUser.getName(); // getName() is springs way to get the logged in user name, which in my case is their email (i.e what they login with)


    Book book = bookRepository.findByTitle(title);
    
    //rating and content
    
    rating.setBookName(title);
    rating.setUserName(email);
    
    book.addRating(rating);
    
    bookRepository.save(book);
    
    model.addAttribute("book", book);
	   
    return "justReviewed";
}


@RequestMapping(value="/showbookReview/{title}", method=RequestMethod.GET)
public String ShowBook(Model model, @PathVariable String title) {
	   
	
	 
	  Book book = bookRepository.findByTitle(title);
	  String image = book.getImagePath();
	   
	  model.addAttribute("image", image);
	  model.addAttribute("book", book);
	   return "justReviewed";
}
@RequestMapping(value="/reviewBook/{id}", method=RequestMethod.GET)
public String removeBook(@PathVariable String id, Model model) {
	   

	

//    int bar = toIntExact(id);
//	
	Book book = bookRepository.findByTitle(id);
	model.addAttribute("book", book);
	model.addAttribute("rating", new Rating());
	   
    return "review";
}

@RequestMapping(value="/showUsers", method=RequestMethod.GET)
public String Users(Model model)
{
		  
	 
	   ArrayList<UserLogin> users = (ArrayList<UserLogin>) uR.findAll();
	   
	   model.addAttribute("users", users);
	  
	   return "users";
	   
}

@RequestMapping(value="/viewUserCart/{userName}", method=RequestMethod.GET)
public String ViewCart(Model model, @PathVariable String userName) {
	   
	   
	   
	  UserLogin ul = uR.findByUserName(userName);
    
	  model.addAttribute("mycart", ul);
	  
	 
    return "mycart";
}

@RequestMapping(value="/viewUserPurchases/{userName}", method=RequestMethod.GET)
public String ViewPurchases(Model model, @PathVariable String userName) {
	   
	   
	   
	  UserLogin ul = uR.findByUserName(userName);
    
	  model.addAttribute("mycart", ul);
	  
	 
    return "myPurchases";
}
@RequestMapping(value="/calculateD/{totalPrice}", method=RequestMethod.POST)
public String CalculateDiscount(Model model, @RequestBody String code, @PathVariable int totalPrice) {
	
	double discountedPrice = 0.0;
	
	Discount dc = Discount.getInstance();
	
	 String code2 = code.substring(5);
	 System.out.println("Price b4: " + totalPrice);

	 
	 
	 double discountAmount = dc.calculatePrice(code2);	 
	 
	 System.out.println("Discounted Amount: " + discountAmount);

	 if (discountAmount == 1.0)
	 {
		 model.addAttribute("totalPrice", totalPrice);
	 }
	 else
	 {
	 discountedPrice =  (totalPrice - (totalPrice * discountAmount));
	 model.addAttribute("totalPrice", discountedPrice);
	 }	 
	
	 model.addAttribute("paymentDetails", new PaymentDetails());

	
	return "payment";

}

//This method works, although it doesnt clarify books were purchased, needs to redirect to purchase page
@RequestMapping(value="/discount", method=RequestMethod.GET)
public String Discount(Model model) {
	
	
	  Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
      String email = loggedInUser.getName(); // getName() is springs way to get the logged in user name, which in my case is their email (i.e what they login with)

      UserLogin user = uR.findByUserName(email);
    
      int totalPrice = 0;
      
	   Set<Book> books = user.getBooks();
	   
	   for (Book b : books)
	   {
		   totalPrice = totalPrice + b.getPrice();
	   }
      
	   model.addAttribute("totalPrice", totalPrice);
	
	
	return "discountPage";
	
	
}
@RequestMapping(value="/purchasedBooks", method=RequestMethod.GET)
public String ViewPurchasedBooks(Model model) {
	   
	   Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	   String email = loggedInUser.getName();

	   UserLogin user = uR.findByUserName(email);
    
	  model.addAttribute("mycart", user);
	  
	 
    return "myPurchases";
}

@RequestMapping(value="/purchaseBooks", method=RequestMethod.POST)
public String PurchaseBooks(@Valid PaymentDetails paymentDetails, Model model, BindingResult errors) {
	
	System.out.print("Payment Details : " + paymentDetails.getAddress() + paymentDetails.getCardNumber() + paymentDetails.getPaymentMethod());
	
	   Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	   String email = loggedInUser.getName();
   
	   UserLogin user = uR.findByUserName(email);
	   
	   user.addPaymentDetails(paymentDetails);
	   
	   Set<Book> set = user.getBooks();
	   
	   
	    for(Book book: set) {
    OrderController controller=new OrderController();
    controller.facade=new PurchaseServiceFacadeImpl();
     controller.orderProduct(book.getId());
     int newQuantity = book.getAmount() - 1;
 	book.setAmount(newQuantity);
     boolean result=controller.orderFulfilled;
     
	    }
	    
	    
     CloneFactory userMaker = new CloneFactory();
     

     
     UserLogin clonedUser = (UserLogin) userMaker.getClone(user);

	  

	  for (Iterator<Book> iterator = set.iterator(); iterator.hasNext();)
	  {
		  Book b = iterator.next();
		  
		  if (b.getId() > 0)
			  
		  clonedUser.addPurchasedBook(b);
		  iterator.remove();
		  
	  }
	  uR.delete(user);

	 uR.save(clonedUser);
	 
	 
	model.addAttribute("myCart", clonedUser);
	
	return "myPurchases";
	


}


}