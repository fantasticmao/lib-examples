package cn.fantasticmao.demo.java.database.mysql;

import cn.fantasticmao.demo.java.database.User;

import javax.annotation.Nullable;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * MysqlCrudRepository
 * <p>
 * 启动 MySQL Docker 容器
 *
 * @author fantasticmao
 * @see <a href="https://dev.mysql.com/doc/refman/8.4/en/">MySQL 8.4 Reference Manual</a>
 * @see <a href="https://dev.mysql.com/doc/refman/8.4/en/data-types.html">Chapter 13 Data Types</a>
 * @see <a href="https://dev.mysql.com/doc/refman/8.4/en/functions.html">Chapter 14 Functions and Operators</a>
 * @see <a href="https://dev.mysql.com/doc/connector-j/en/">MySQL Connector/J Developer Guide</a>
 * @since 2024-08-04
 */
public class MysqlCrudRepository implements AutoCloseable {
    private final Connection connection;

    public MysqlCrudRepository() throws SQLException {
        final String jdbcUrl = "jdbc:mysql://localhost:3306/fantasticmao";
        final Properties props = new Properties();
        props.put("user", "root");
        props.put("password", "123456");
        props.put("connectTimeout", "3000");
        props.put("socketTimeout", "3000");

        this.connection = DriverManager.getConnection(jdbcUrl, props);
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
                    name        VARCHAR(32),
                    age         INTEGER,
                    email       VARCHAR(32),
                    birthday    TIMESTAMP
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
            statement.setObject(5, user.getBirthday());
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
                    LocalDateTime birthday = resultSet.getObject("birthday", LocalDateTime.class);
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
                    LocalDateTime birthday = resultSet.getObject("birthday", LocalDateTime.class);
                    return new User(id, _name, age, email, birthday);
                }
            }
        }
        return null;
    }
}
