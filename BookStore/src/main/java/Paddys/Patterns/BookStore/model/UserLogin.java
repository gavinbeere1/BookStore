package Paddys.Patterns.BookStore.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



@Entity
public class UserLogin {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	private Long phone;
	private String userName;
	private String password;
	private boolean userStatus;
	
	@OneToOne
	public PaymentDetails payDetails;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	public Set<Book> books;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	public Set<Book> purchasedBooks;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	public Set<Role> roles;
	
	
	public UserLogin() {
		super();
	}

    
	public UserLogin(Long id, String firstName, String lastName, Long phone,
			String userName, String password, boolean userStatus,
			PaymentDetails payDetails, Set<Book> books,
			Set<Book> purchasedBooks, Set<Role> roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.userName = userName;
		this.password = password;
		this.userStatus = userStatus;
		this.payDetails = payDetails;
		this.books = books;
		this.purchasedBooks = purchasedBooks;
		this.roles = roles;
	}


	public PaymentDetails getPayDetails() {
		return payDetails;
	}


	public void setPayDetails(PaymentDetails payDetails) {
		this.payDetails = payDetails;
	}


	public Set<Book> getPurchasedBooks() {
		return purchasedBooks;
	}

	public void setPurchasedBooks(Set<Book> purchasedBooks) {
		this.purchasedBooks = purchasedBooks;
	}

	public Set<Role> getRoles() {
		if (roles == null)
			roles = new HashSet<>();
		return roles;
	}



	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

public void addRole(Role role)
{
	getRoles().add(role);
}


public void addPurchasedBook(Book purchasedBooks)
{
	
	getPurchasedBooks().add(purchasedBooks);
}



	public void addBook(Book books){

		getBooks().add(books);
	}
	

	
	public boolean getUserStatus() {
		return userStatus;
	}



	public void setUserStatus(boolean userStatus) {
		this.userStatus = userStatus;
	}



	public Set<Book> getBooks() {
		
		if (books == null)
			books = new HashSet<>();
		return books;
		
	
	}



	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public void RemoveBook(Book book){
		books.remove(book);
	}
	

}
