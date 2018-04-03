package Paddys.Patterns.BookStore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rating {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String bookName;
	private String userName;
	private int rating;
	private String commentContent;

	
	public Rating() {
		super();
	}


	public Rating(Long id, String bookName, String userName, int rating,
			String commentContent) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.userName = userName;
		this.rating = rating;
		this.commentContent = commentContent;
	}


	

	public String getCommentContent() {
		return commentContent;
	}


	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
}
