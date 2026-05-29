package ibm.javatraining.com.day2;

public class Main {

	public static void main(String[] args) {
		Library library = new Library();
		
		Book book1 = new Book("Harry Potter", "J. K. Rowling");
		Book book2 = new Book("The Fault in Our Stars", "John Green");
		Book book3 = new Book("To Kill a Mockingbird", "Harper Lee");
		
		library.addBook(book1);
		library.addBook(book2);
		library.addBook(book3);
		
		System.out.println("Books:");
		library.showAllBooks();
		System.out.println();
		
		System.out.println("Books: ");
		library.borrowBook("Harry Potter");
		library.showAllBooks();
		System.out.println();
		
		System.out.println("Books: ");
		library.returnBook("Harry Potter");
		library.showAllBooks();
		System.out.println();

	}

}
