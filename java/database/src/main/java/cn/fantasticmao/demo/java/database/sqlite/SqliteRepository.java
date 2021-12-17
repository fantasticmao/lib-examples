package cn.fantasticmao.demo.java.database.sqlite;

import cn.fantasticmao.demo.java.database.User;
import org.sqlite.SQLiteConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * SqliteRepository
 *
 * @author fantasticmao
 * @see <a href="https://www.sqlite.org/lang.html">SQL Syntax</a>
 * @since 2021-12-18
 */
public class SqliteRepository {
    private final SQLiteConfig config;

    public SqliteRepository() throws SQLException {
        this.config = new SQLiteConfig();
        this.config.setSharedCache(true);
        this.createTable();
    }

    public void createTable() throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db", this.config.toProperties());
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS t_user");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS t_user(" +
                "id INT PRIMARY KEY," +
                "name VARCHAR(32))");
        }
    }

    public boolean insert(User user) throws SQLException {
        String sql = "INSERT INTO t_user(id, name) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db", this.config.toProperties());
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            return statement.executeUpdate() > 0;
        }
    }

    public List<User> selectAll() throws SQLException {
        List<User> result = new ArrayList<>();
        String sql = "SELECT id, name FROM t_user ORDER BY id";
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db", this.config.toProperties());
             PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    result.add(new User(id, name));
                }
            }
        }
        return result;
    }

}
