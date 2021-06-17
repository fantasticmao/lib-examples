package cn.fantasticmao.demo.java.others.sqlite;

import java.sql.*;

/**
 * Main
 *
 * @author maomao
 * @since 2021-06-17
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite::memory:");
             Statement statement = connection.createStatement()) {
            int updated = statement.executeUpdate("CREATE TABLE IF NOT EXISTS user(" +
                "id INTEGER PRIMARY KEY," +
                "name VARCHAR" +
                ")");
            assert updated > 0;

            updated = statement.executeUpdate("INSERT INTO user(name) VALUES ('Tom'), ('Bob'), ('Anni')");
            assert updated > 0;

            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM user")) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    System.out.printf("id: %d, name: %s%n", id, name);
                }
            }
        }
    }
}
