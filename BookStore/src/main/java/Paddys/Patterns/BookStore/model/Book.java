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
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String author;
	private String title;
	private String topic;
	private int number;
	private int amount;
	private int price;

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	public Set<Rating> ratings;
	
	
	public Book() {
		super();
	}



	
	public Book(Long id, String author, String title, String topic, int number,
			int amount, int price, Set<Rating> ratings) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.topic = topic;
		this.number = number;
		this.amount = amount;
		this.price = price;
		this.ratings = ratings;
	}




	public int getPrice() {
		return price;
	}




	public void setPrice(int price) {
		this.price = price;
	}




	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public void addRating(Rating ratings){

		getRatings().add(ratings);
	}
	


	public Set<Rating> getRatings() {
		return ratings;
	}


	public void setRatings(Set<Rating> ratings) {
		this.ratings = ratings;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	

	

}
