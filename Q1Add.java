package jdbc;

import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Q1Add {

	public static void main(String[] args) throws SQLException {
		Connection con = null;
		ResultSet rs = null;
		Map<Integer, Employee> hm1 = new HashMap<>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?useSSL=false", "root", "root");

			hm1.put(1, new Employee(1, "lalu", 12));
			hm1.put(2, new Employee(2, "faizal", 30));
			hm1.put(3, new Employee(3, "danish", 20));
			hm1.put(4, new Employee(4, "sardar", 15));
			String tablename = "emp";

			StringBuilder sql = new StringBuilder("INSERT INTO ").append(tablename).append(" ");
			StringBuilder placeholders = new StringBuilder("?,?,?");

			sql.append("VALUES (").append(placeholders).append(")");
			PreparedStatement ps = con.prepareStatement(sql.toString());

			Iterator<Map.Entry<Integer, Employee>> it = hm1.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<Integer, Employee> en = it.next();
				ps.setInt(1, en.getValue().getId());
				ps.setString(2, en.getValue().getName());
				ps.setInt(3, en.getValue().getAge());

				ps.addBatch();

				int[] rowsAffected = ps.executeBatch();
				// System.out.println( +" Rows Affected");

			}
			rs = ps.executeQuery("select * from emp");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {
			if (con != null)
				con.close();

			if (rs != null)
				rs.close();
		}

	}

}
