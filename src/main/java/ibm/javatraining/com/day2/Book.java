package ibm.javatraining.com.day2;

public class Book {
	private String title;
	private String author;
	private boolean available;
	
	public Book(String title, String author)
	{
		this.title = title;
		this.author = author;
		this.available = true;
	}
	
	public void borrowBook()
	{
		if(available)
		{
			available = false;
		}
		
		else
		{
			System.out.println("Book is already followed");
		}
	}
	
	public void returnBook()
	{
		available = true;
	}
	
	public String getInfo()
	{
		return title + " by " + author + " | Availability: " + available;
	}
	

}
