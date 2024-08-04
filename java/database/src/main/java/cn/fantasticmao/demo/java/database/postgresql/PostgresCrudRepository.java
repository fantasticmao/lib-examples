package cn.fantasticmao.demo.java.database.postgresql;

import cn.fantasticmao.demo.java.database.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * PostgresCrudRepository
 * <p>
 * 启动 PostgreSQL Docker 容器
 *
 * @author fantasticmao
 * @see <a href="https://www.postgresql.org/docs/16/index.html">PostgreSQL 16.3 Documentation</a>
 * @see <a href="https://jdbc.postgresql.org/documentation/">PostgreSQL JDBC Driver</a>
 * @since 2024-08-04
 */
public class PostgresCrudRepository implements AutoCloseable {
    private final Connection connection;

    public PostgresCrudRepository() throws SQLException {
        final String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
        final Properties props = new Properties();
        props.put("user", "postgres");
        props.put("password", "123456");
        props.put("loginTimeout", "3");
        props.put("connectTimeout", "3");
        props.put("socketTimeout", "3");

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
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS t_user(" +
                "id INT PRIMARY KEY," +
                "NAME VARCHAR(32)," +
                "age INT)");
        }
    }

    public boolean insert(User user) throws SQLException {
        String sql = "INSERT INTO t_user(id, name, age) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            statement.setInt(3, user.getAge());
            return statement.executeUpdate() > 0;
        }
    }

    public List<User> selectAll() throws SQLException {
        List<User> result = new ArrayList<>();
        String sql = "SELECT id, name, age FROM t_user ORDER BY id";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    result.add(new User(id, name, age, null));
                }
            }
        }
        return result;
    }
}
