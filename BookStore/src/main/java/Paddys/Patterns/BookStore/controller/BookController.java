package Paddys.Patterns.BookStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
	public class BookController {

	    @GetMapping("/")
	    public String welcome() {
	        return "bookstore";
	    }
	}

///This works
//@RestController
//public class BookController {
//
//	@GetMapping("/book")
//	  public String index2() {
//	    return "bookstoreddddd";
//	  }
//	
//}
