package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieBean {

    //Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/csd430";
    private static final String USER = "student1";
    private static final String PASSWORD = "pass";

    //Load MySQL driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Method to get all movie names
    public ArrayList<String> getAllMovies() {
        ArrayList<String> movies = new ArrayList<>();
        String sql = "SELECT * FROM cameron_movies_data";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                //Assuming the table has a column called title
                movies.add(rs.getString("title"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;
    }
}
