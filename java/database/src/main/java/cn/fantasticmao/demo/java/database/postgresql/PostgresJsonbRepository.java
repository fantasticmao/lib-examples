package cn.fantasticmao.demo.java.database.postgresql;

import cn.fantasticmao.demo.java.database.User;
import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * PostgresJsonbRepository
 * <p>
 * 启动 PostgreSQL Docker 容器
 *
 * @author fantasticmao
 * @see <a href="https://www.postgresql.org/docs/16/index.html">PostgreSQL 16.3 Documentation</a>
 * @see <a href="https://jdbc.postgresql.org/documentation/">PostgreSQL JDBC Driver</a>
 * @since 2024-08-05
 */
public class PostgresJsonbRepository implements AutoCloseable {
    private final Connection connection;
    private final Gson gson = new Gson();

    public PostgresJsonbRepository() throws SQLException {
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
            statement.executeUpdate("DROP TABLE IF EXISTS t_user_json");
            statement.executeUpdate("""
                CREATE TABLE IF NOT EXISTS t_user_json (
                    content JSONB NOT NULL
                )""");
        }
    }

    public boolean insert(User user) throws SQLException {
        String sql = "INSERT INTO t_user_json(content) VALUES (?::JSONB)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, gson.toJson(user));
            return statement.executeUpdate() > 0;
        }
    }

    public List<User> selectAll() throws SQLException {
        List<User> result = new ArrayList<>();
        String sql = "SELECT content FROM t_user_json";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String content = resultSet.getString("content");
                    result.add(gson.fromJson(content, User.class));
                }
            }
        }
        return result;
    }
}
