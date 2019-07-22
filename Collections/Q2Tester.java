package collectionAssignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Q2Tester {

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

		HashMap<String, Integer> BookCountMap = new HashMap<>();

		Iterator<Book> itList = BookList.iterator();
		Iterator<Map.Entry<String, Integer>> itMap = BookCountMap.entrySet().iterator();

		while (itList.hasNext()) {
			Book b = itList.next();
			Integer count = 0;

			if (!BookCountMap.containsKey(b.getAuthorName()))
				BookCountMap.put(b.getAuthorName(), 0);
			else {
				// System.out.println("asdasd");
				Integer oldValue = BookCountMap.get(b.getAuthorName());
				oldValue++;

				BookCountMap.put(b.getAuthorName(), oldValue);

			}

		}

		for (Map.Entry<String, Integer> entry : BookCountMap.entrySet()) {
			System.out.println(entry.getKey() + "->" + entry.getValue());
			// System.out.println(entry.getValue());
		}

	}

}
