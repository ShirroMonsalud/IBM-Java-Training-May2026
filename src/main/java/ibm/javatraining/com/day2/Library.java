package ibm.javatraining.com.day2;
import java.util.ArrayList;

public class Library {
	private ArrayList <Book> books = new ArrayList<>();
	
	public void addBook(Book b)
	{
		books.add(b);
	}
	
	public void showAllBooks()
	{
		for (int i = 0; i < books.size(); i++)
		{
			Book book = books.get(i);
			System.out.println(book.getInfo());
		}
	}
	
	public void borrowBook(String title)
	{
		for (int i = 0; i < books.size(); i++)
		{
			Book book = books.get(i);
			if (book.getInfo().contains(title))
			{
				book.borrowBook();
				return;
			}
		}
	}
	
	public void returnBook(String title)
	{
		for (int i = 0; i < books.size(); i++)
		{
			Book book = books.get(i);
			if (book.getInfo().contains(title))
			{
				book.returnBook();
				return;
			}
		}
		
	}
	
	

}
