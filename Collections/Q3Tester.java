package collectionAssignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Q3Tester {

	public static void main(String[] args) {
		// place the stringInput.txt path in path variable
		String path = "C:\\Users\\nikhil.taylor\\Desktop\\stringInput.txt";
		//FileRead is class which has a function readFromFile which reads a file mans returns it into string 
		String str = FileRead.readFromFile(path);
		ArrayList<String> al = new ArrayList<>(Arrays.asList(str.split(",")));

		System.out.println(str);

		Set<String> strSet = new HashSet<String>();
		Iterator<String> itList = al.iterator();

		List<String> outList = new ArrayList<String>();

		while (itList.hasNext()) {
			String s = itList.next();
			if (!strSet.contains(s))
				strSet.add(s);
			else
				outList.add(s);
		}

		System.out.println(outList);
		Collections.sort(outList);
		System.out.println(outList);

	}

}
