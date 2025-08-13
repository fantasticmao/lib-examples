package cn.fantasticmao.demo.java.database.postgresql;

import cn.fantasticmao.demo.java.database.User;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.annotation.Nullable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * PostgresJsonbRepository
 * <p>
 * 启动 PostgreSQL Docker 容器
 *
 * @author fantasticmao
 * @see <a href="https://www.postgresql.org/docs/current/index.html">PostgreSQL Documentation</a>
 * @see <a href="https://www.postgresql.org/docs/current/datatype-json.html">JSON Types</a>
 * @see <a href="https://www.postgresql.org/docs/current/functions-json.html">JSON Functions and Operators</a>
 * @see <a href="https://jdbc.postgresql.org/documentation/">PostgreSQL JDBC Driver</a>
 * @since 2024-08-05
 */
public class PostgresJsonbRepository implements AutoCloseable {
    private final Connection connection;
    private final ObjectMapper mapper = JsonMapper.builder()
        .addModule(new JavaTimeModule())
        .build();

    public PostgresJsonbRepository() throws SQLException {
        final String jdbcUrl = "jdbc:postgresql://localhost:5432/fantasticmao";
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

    public boolean insert(User user) throws SQLException, JacksonException {
        String sql = "INSERT INTO t_user_json(content) VALUES (?::JSONB)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, mapper.writeValueAsString(user));
            return statement.executeUpdate() > 0;
        }
    }

    public List<User> selectAll() throws SQLException {
        List<User> result = new ArrayList<>();
        String sql = """
            SELECT content ->> 'id'                             AS id,
                   JSONB_PATH_QUERY(content, '$.name') #>> '{}' AS name,
                   content ->> 'age'                            AS age,
                   content ->> 'email'                          AS email,
                   content ->> 'birthday'                       AS birthday
            FROM t_user_json
            """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String _name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    String email = resultSet.getString("email");
                    LocalDateTime birthday = LocalDateTime.parse(resultSet.getString("birthday"), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    result.add(new User(id, _name, age, email, birthday));
                }
            }
        }
        return result;
    }

    @Nullable
    public User selectByName(String name) throws SQLException, JacksonException {
        String sql = "SELECT * FROM t_user_json WHERE content ->> 'name' = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String content = resultSet.getString("content");
                    return mapper.readValue(content, User.class);
                }
            }
        }
        return null;
    }
}
