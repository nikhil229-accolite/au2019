package collectionAssignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

//import auJava.Book;

public class q1Tester {

	public static void main(String[] args) {

		ArrayList<Book> BookList = new ArrayList<>();
		BookList.add(new Book(2, "The Concepts of Physics", "H.C Verma", 50));
		BookList.add(new Book(1, "2 States", "Chetan Bhagat", 30));
		BookList.add(new Book(3, "x y z", "lallu singh", 10));
		BookList.add(new Book(4, "a b c", "faizal khan", 40));
		BookList.add(new Book(7, "p q r", "perpendicular", 90));
		BookList.add(new Book(5, "m n o", "danish", 70));
		BookList.add(new Book(6, "d e f", "Yashwant Kanetakar", 30));
		BookList.add(new Book(9, "Alochol", "faizal khan", 200));
		BookList.add(new Book(8, "corona", "perpendicular", 160));

		BookList.sort(new BookComparator());

		System.out.println(BookList);

		Iterator<Book> it = BookList.iterator();
		while (it.hasNext()) {
			Book b = it.next();
			double newPrice = b.getPrice() - b.getPrice() * 0.2;
			b.setPrice((int) newPrice);

		}

		System.out.println(BookList);

	}

}
