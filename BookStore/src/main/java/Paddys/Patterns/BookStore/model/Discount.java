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
	
	public boolean calculatePrice(String code)
	{
		for (String e : discountCode)
		{
			if (code.equals(e))
			{
				correctCode = true;
				break;
				
			}
			
		}
	
		return correctCode;
		
	}
	
	
}
