package collectionAssignment;

import java.util.Comparator;

public class BookComparator implements Comparator<Book> {

	public int compare(Book o1, Book o2) {
		// TODO Auto-generated method stub

		if (o1 == null) {
			if (o2 == null) {
				return 0;
			}
			return -1;
		}
		if (o2 == null) {
			return 1;
		}
		if (o1.getPrice() > o2.getPrice()) {
			return 1;

		}
		if (o1.getPrice() < o2.getPrice()) {
			return -1;
		}
		// if price is same
		if (o1.getPrice() == o2.getPrice()) {
			return o1.getName().compareTo(o2.getName());

			/*
			 * if(o1.getName().compareTo(o2.getName()) == 1 ) { return 1;
			 * 
			 * } if(o1.getName().compareTo(o2.getName()) == -1) { return -1; }
			 */
		}
		return 0;
	}

}
