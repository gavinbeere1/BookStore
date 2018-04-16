package Paddys.Patterns.BookStore.model;

import java.util.ArrayList;
import java.util.List;

public class Discount {
	
	static List<String> discountCode = new ArrayList<String>();
	
	static boolean correctCode = false;
	
	private static Discount discount = new Discount();
	
	private Discount() 
	{
		discountCode.add("Discount1010");
		discountCode.add("NewMember");
		discountCode.add("LongfordBookClub");
	}
	
	public static Discount getInstance()
	{
		return discount;
	}
	
	public double calculatePrice(String code)
	{
		double discount=0;
		
		for (String e : discountCode)
		{
			
			if (code.equals(e) && e.equals("Discount1010"))
			{
				discount =  .2;
				break;	
			}
			if (code.equals(e) && e.equals("NewMember"))
			{
				discount =  .25;
				break;	
			}
			if (code.equals(e) && e.equals("LongfordBookClub"))
			{
				discount =  .3;
				break;	
			}
			else 
			{
				discount = 1;
				
			}
			
		}
		
		return discount;
		
	}
	
	
}
