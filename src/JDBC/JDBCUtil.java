package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
public class JDBCUtil{
	public static Connection getConnection() {
		Connection conn = null;

		try {
			// đăng kí mySQL Driver với DriverManager My SQL
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

			// các thông số của My SQL
			String url = "jdbc:mySQL://localhost:3306/quanlyquancafe";
			String username = "root";
			String password = "";

                        // tạo kết nối
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
                    e.printStackTrace();

		}

		return conn;
	}
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
		}
	}
	public static void printInfo(Connection conn) {
		if (conn != null) { 
			try {
				DatabaseMetaData mtdt = conn.getMetaData();
				System.out.println(mtdt.getDatabaseProductName());
				System.out.println(mtdt.getDatabaseProductVersion());
			} catch (SQLException e) {
                            // TODO Auto-generated catch block

			}
		}
	}
        public static void main(String[] args) {
        Connection c = getConnection();
        if (c == null) {
            printInfo(c);
            System.out.println("Kết nối thất bại!");
        } else {
            printInfo(c);
            System.out.println("Kết nối thành công!");
        }
    }  
}

