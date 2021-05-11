package es.cc.esliceu.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ExempleJdbc {

    public static void main(String[] args) throws SQLException, IOException {
            FileInputStream input = new FileInputStream("resources/db.properties");
            Properties props = new Properties();
            props.load(input);
            String URL = props.getProperty("url");
            String USER = props.getProperty("user");
            String PASSWORD = props.getProperty("password");
            System.out.println(URL + " " + USER + " " + PASSWORD);
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL,USER, PASSWORD);

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT employee_id, first_name, last_name from employees ");
            while (rs.next()){
                System.out.println(
                        rs.getInt("employee_id") +  "\t" +
                        rs.getString("first_name") + "\t" +
                        rs.getString(3)
                );
            }
            rs.close();
            stmt.close();

            PreparedStatement stUpdate = con.prepareStatement("UPDATE employees set first_name = ? where employee_id = ?");
            stUpdate.setString(1, "Douglass");
            stUpdate.setInt(2, 199);
            int files = stUpdate.executeUpdate();
            System.out.println("files " + files);
            con.close();

    }
}
