package Paddys.Patterns.BookStore.Service;

public class CloneFactory {

	public User getClone(User userSample)
	{
		
		return userSample.makeCopy();
	}
}
