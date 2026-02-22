package beans;

import java.sql.*;
import java.util.ArrayList;

public class MovieBean {

    //Database connection info
    private static final String URL = "jdbc:mysql://localhost:3306/csd430";
    private static final String USER = "student1";
    private static final String PASSWORD = "pass";

    //Movie properties
    private int id;
    private String title;
    private String genre;
    private String director;
    private int year;
    private double rating;

    //Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    // Insert Movie
    public boolean insertMovie() {
        boolean success = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "INSERT INTO cameron_movies_data (title, genre, director, year, rating) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, title);
            ps.setString(2, genre);
            ps.setString(3, director);
            ps.setInt(4, year);
            ps.setDouble(5, rating);

            int rows = ps.executeUpdate();
            if (rows > 0) success = true;

            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    // Get All Movies
    public ArrayList<MovieBean> getAllMovies() {

        ArrayList<MovieBean> movies = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "SELECT * FROM cameron_movies_data";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                MovieBean movie = new MovieBean();

                movie.setId(rs.getInt("movie_id"));
                movie.setTitle(rs.getString("title"));
                movie.setGenre(rs.getString("genre"));
                movie.setDirector(rs.getString("director"));
                movie.setYear(rs.getInt("year"));
                movie.setRating(rs.getDouble("rating"));

                movies.add(movie);

                System.out.println("Found Movie: " + movie.getTitle());
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return movies;
    }

    // Get Movie By ID
    public MovieBean getMovieById(int movieId) {

        MovieBean movie = new MovieBean();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "SELECT * FROM cameron_movies_data WHERE movie_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, movieId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                movie.setId(rs.getInt("movie_id"));
                movie.setTitle(rs.getString("title"));
                movie.setGenre(rs.getString("genre"));
                movie.setDirector(rs.getString("director"));
                movie.setYear(rs.getInt("year"));
                movie.setRating(rs.getDouble("rating"));
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return movie;
    }

    // Update Movie
    public boolean updateMovie() {

        boolean success = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "UPDATE cameron_movies_data SET title=?, genre=?, director=?, year=?, rating=? WHERE movie_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, title);
            ps.setString(2, genre);
            ps.setString(3, director);
            ps.setInt(4, year);
            ps.setDouble(5, rating);
            ps.setInt(6, id);

            int rows = ps.executeUpdate();
            if (rows > 0) success = true;

            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }
}