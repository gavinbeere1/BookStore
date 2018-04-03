package Paddys.Patterns.BookStore.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class UserLogin {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	private Long phone;
	private String userName;
	private String address;
	private String password;
	private String paymentMethod;
	private String userType;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	public Set<Book> books;
	
	public UserLogin() {
		super();
	}

	

	public UserLogin(Long id, String firstName, String lastName, Long phone,
			String userName, String address, String password,
			String paymentMethod, String userType, Set<Book> books) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.userName = userName;
		this.address = address;
		this.password = password;
		this.paymentMethod = paymentMethod;
		this.userType = userType;
		this.books = books;
	}


	public void addBook(Book books){

		getBooks().add(books);
	}
	
	
	public String getUserType() {
		return userType;
	}



	public void setUserType(String userType) {
		this.userType = userType;
	}



	public Set<Book> getBooks() {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	

}
