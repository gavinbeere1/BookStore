package Paddys.Patterns.BookStore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;




import Paddys.Patterns.BookStore.repository.UserLoginRepository;

import Paddys.Patterns.BookStore.model.UserLogin;

@Controller
	public class BookController {
	
	
	@Autowired
	UserLoginRepository uR;

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
	
	
	user.setUserType("Regular");
	uR.save(user);
	
	model.addAttribute("username", user.getUserName());
	
	return "result";
	
}



}