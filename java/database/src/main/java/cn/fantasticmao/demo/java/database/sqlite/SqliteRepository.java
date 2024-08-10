package cn.fantasticmao.demo.java.database.sqlite;

import cn.fantasticmao.demo.java.database.User;
import org.sqlite.SQLiteConfig;

import javax.annotation.Nullable;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * SqliteRepository
 *
 * @author fantasticmao
 * @see <a href="https://www.sqlite.org/lang.html">SQL Syntax</a>
 * @see <a href="https://www.sqlite.org/datatype3.html">Datatypes In SQLite</a>
 * @see <a href="https://github.com/xerial/sqlite-jdbc?tab=readme-ov-file#usage">SQLite JDBC Usage</a>
 * @since 2021-12-18
 */
public class SqliteRepository implements AutoCloseable {
    private final Connection connection;

    public SqliteRepository() throws SQLException {
        SQLiteConfig config = new SQLiteConfig();
        config.setSharedCache(true);
        this.connection = DriverManager.getConnection("jdbc:sqlite::memory:", config.toProperties());
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
            statement.executeUpdate("""
                CREATE TABLE IF NOT EXISTS t_user(
                    id          INTEGER PRIMARY KEY,
                    name        TEXT,
                    age         INTEGER,
                    email       TEXT,
                    birthday    TEXT
                )""");
        }
    }

    public boolean insert(User user) throws SQLException {
        String sql = "INSERT INTO t_user(id, name, age, email, birthday) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            statement.setInt(3, user.getAge());
            statement.setString(4, user.getEmail());
            statement.setString(5, DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(user.getBirthday()));
            return statement.executeUpdate() > 0;
        }
    }

    public List<User> selectAll() throws SQLException {
        List<User> result = new ArrayList<>();
        String sql = "SELECT id, name, age, email, birthday FROM t_user ORDER BY id";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    String email = resultSet.getString("email");
                    LocalDateTime birthday = LocalDateTime.parse(resultSet.getString("birthday"), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    result.add(new User(id, name, age, email, birthday));
                }
            }
        }
        return result;
    }

    @Nullable
    public User selectByName(String name) throws SQLException {
        String sql = "SELECT id, name, age, email, birthday FROM t_user WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String _name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    String email = resultSet.getString("email");
                    LocalDateTime birthday = LocalDateTime.parse(resultSet.getString("birthday"), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    return new User(id, _name, age, email, birthday);
                }
            }
        }
        return null;
    }
}
