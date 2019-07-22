package jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Q3Delete {
	public static void printMap(Map<Integer, Employee> hm3) {
		hm3.entrySet().forEach(entry -> {
			System.out.println(
					entry.getValue().getId() + " " + entry.getValue().getName() + " " + entry.getValue().getAge());
		});
	}

	public static void printTable() {

		ResultSet rs = null;

		Statement stmt = null;
		try {
			try {

				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DbConnection.getConnection();
			} catch (SQLException e) {
				System.err.println("There was an error getting the connection: " + e.getMessage());
			}

			// stmt = connection.createStatement();
			System.out.println("Current View");

			rs = stmt.executeQuery("select * from emp");

			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) throws SQLException {
		Connection con = null;
		ResultSet rs = null;
		Scanner in = new Scanner(System.in);
		Statement stmt = null;
		BufferedReader ob = new BufferedReader(new InputStreamReader(System.in));

		try {

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?useSSL=false", "root", "root");

			Q2Update.printTable();

			Map<Integer, Employee> hm3 = new HashMap<>();
			int input;
			Integer key = 1; // default Integer type
			do {
				System.out.println(
						"Enter your Choice to input into hash table and delete those input \n1.To input the hash table \n.Pree Any key other than to  Exit ");
				input = in.nextInt();
				if (input == 1) {
					printMap(hm3);
					System.out.println("Enter id ");
					Integer id = in.nextInt();

					System.out.println("Enter a name");
					String name = ob.readLine();

					System.out.println("Enter age");
					Integer age = in.nextInt();

					hm3.put(key++, new Employee(id, name, age));
					System.out.println("Entries Entered into Map");
					printMap(hm3);

					String sql = "delete from emp where id = ?";

					PreparedStatement ps = con.prepareStatement(sql);

//					

					Iterator<Map.Entry<Integer, Employee>> it = hm3.entrySet().iterator();
					while (it.hasNext()) {
						Map.Entry<Integer, Employee> en = it.next();
						ps.setInt(1, en.getValue().getId());

						ps.addBatch();
						ps.executeBatch();

						// System.out.println( +" Rows Affected");

					}
					System.out.println("Entry deleted from table");

					printTable();

				} else
					break;

			} while (true);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (stmt != null)
				stmt.close();
		} finally {
			if (con != null)
				con.close();
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
		}

	}
}
