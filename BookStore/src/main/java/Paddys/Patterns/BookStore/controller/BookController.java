package Paddys.Patterns.BookStore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



import Paddys.Patterns.BookStore.model.Book;
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

}