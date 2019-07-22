package collectionAssignment;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Q4Tester {

	public static void main(String[] args) {
		//FileRead is class which has a function readFromFile which reads a file mans returns it into string 
		String s1 = FileRead.readFromFile("C:\\Users\\nikhil.taylor\\Desktop\\s1.txt");// put the path for s1.txt
		String s2 = FileRead.readFromFile("C:\\Users\\nikhil.taylor\\Desktop\\s2.txt");// put the path for s1.txt

		System.out.println(s1);
		System.out.println(s2);

		List<String> stringList1 = new ArrayList<String>(Arrays.asList(s1.split(",")));
		List<String> stringList2 = new ArrayList<String>(Arrays.asList(s2.split(",")));

		List<String> outList = new ArrayList<String>();

		Set<String> setHash = new HashSet<String>(stringList1);
		Iterator<String> itList = stringList2.iterator();

		while (itList.hasNext()) {
			String s = itList.next();
			if (setHash.contains(s))
				outList.add(s);
		}

		System.out.println(outList);

	}

}
