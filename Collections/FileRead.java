package collectionAssignment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileRead {

	public static String readFromFile(String path) {
		StringBuilder sb = new StringBuilder();

		try {
			InputStream is = new FileInputStream(path);
			BufferedReader buf = new BufferedReader(new InputStreamReader(is));
			String line = buf.readLine();
			while (line != null) {
				sb.append(line);
				line = buf.readLine();
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return sb.toString();
	}

}
