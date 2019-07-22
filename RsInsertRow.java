package jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RsInsertRow {

	static Connection connection = null;
	static DatabaseMetaData metadata = null;
	
	public static void printTable() {

		ResultSet rs = null;
		
		Statement stmt = null;
		try {
			try {

				Class.forName("com.mysql.jdbc.Driver");
				connection = DbConnection.getConnection();
			} catch (SQLException e) {
				System.err.println("There was an error getting the connection: " + e.getMessage());
			}

			stmt = connection.createStatement();
			System.out.println("Current View");

			rs = stmt.executeQuery("select * from emp");

			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Static block for initialization
	static {
		try {
			connection = DbConnection.getConnection();
		} catch (SQLException e) {
			System.err.println("There was an error getting the connection: " + e.getMessage());
		}

		try {
			metadata = connection.getMetaData();
		} catch (SQLException e) {
			System.err.println("There was an error getting the metadata: " + e.getMessage());
		}
	}

	public static void main(String[] args) throws Exception {

		Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		Scanner in = new Scanner(System.in);
		BufferedReader ob = new BufferedReader(new InputStreamReader(System.in));

		try {
			ResultSet rs = st.executeQuery("SELECT * from emp");



			// Loop through result set and add 5 in age
			// Move to BFR position so while-loop works properly

			int input;
			
			do {
				System.out.println(
						"Using Result Set\n1.Insert\n2.Update\n3.Delete\n.Pree Any other key to  Exit ");
				input = in.nextInt();
				switch(input)
				{
					case 1:{
						
						System.out.println("List result set for reference....");
						printTable();
						System.out.println("Enter id ");
						Integer id = in.nextInt();

						System.out.println("Enter a name");
						String name = ob.readLine();

						System.out.println("Enter age");
						Integer age = in.nextInt();
						
						rs.moveToInsertRow();
						rs.updateInt("id", id);
						rs.updateString("e_name", name);

						rs.updateInt("age", age);
						// Commit row
						rs.insertRow();

						System.out.println("List result set showing new set...");
						printTable();
						break;
					}
					
					case 2:
					{
						System.out.println("List result set for reference....");
						printTable();

						// Loop through result set and add ex in age
						// Move to BFR position so while-loop works properly
						rs.beforeFirst();
						System.out.println("Enter the number for age incretment:");
						int ex = in.nextInt();
						//Extract data from result set
						while (rs.next()) {
							// Retrieve by column e_name
							int newAge = rs.getInt("age") + ex;
							rs.updateDouble("age", newAge);
							rs.updateRow();
						}
						System.out.println("List result set showing new ages...");
						// rs1 = rs;
						printTable();
						break;
					}
					
					case 3:
					{
						// Delete second record from the table.
						// Set position to second record first
						System.out.println("Enter the Row number: ");
						int ex = in.nextInt(); // row number for deletion
						rs.absolute(ex);
						System.out.println("List the record before deleting...");
						printTable();
						// Retrieve by column e_name
						int id = rs.getInt("id");
						int age = rs.getInt("age");
						String e_name = rs.getString("e_name");

						System.out.print("Row " + ex + ": ");
						// Display values
						System.out.print("ID: " + id);
						System.out.print(", Age: " + age);
						System.out.println(", name: " + e_name);

						// Delete row
						rs.deleteRow();
						System.out.println("List result set after deleting one records...");
						printTable();
						break;
						
					}
					default:
					{
						System.out.println("Enter valid input");
						break;
					}
					
				}
				
				

					
				}while(input!=0);
			
			

			

			// Clean-up environment
			rs.close();
			st.close();
			connection.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.fore_name
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try

		}

	}
}
	


