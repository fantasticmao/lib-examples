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
 * @see <a href="https://www.sqlite.org/datatype3.html">Datatypes In SQLite</a>
 * @since 2021-12-18
 */
public class SqliteRepository implements AutoCloseable {
    private final Connection connection;

    public SqliteRepository() throws SQLException {
        SQLiteConfig config = new SQLiteConfig();
        config.setSharedCache(true);
        this.connection = DriverManager.getConnection("jdbc:sqlite:test.db", config.toProperties());
        this.createTable();
    }

    @Override
    public void close() throws SQLException {
        if (this.connection != null) {
            this.connection.close();
        }
    }

    public void createTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS t_user");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS t_user(" +
                "id INT PRIMARY KEY," +
                "name VARCHAR(32))");
        }
    }

    public boolean insert(User user) throws SQLException {
        String sql = "INSERT INTO t_user(id, name) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            return statement.executeUpdate() > 0;
        }
    }

    public List<User> selectAll() throws SQLException {
        List<User> result = new ArrayList<>();
        String sql = "SELECT id, name FROM t_user ORDER BY id";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
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
