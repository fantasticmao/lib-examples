package cn.fantasticmao.demo.java.database.h2;

import cn.fantasticmao.demo.java.database.User;

import javax.annotation.Nullable;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * H2Repository
 *
 * @author fantasticmao
 * @see <a href="https://www.h2database.com/html/tutorial.html#creating_new_databases">Creating New Databases</a>
 * @see <a href="https://www.h2database.com/html/tutorial.html#web_applications">Using Databases in Web Applications</a>
 * @see <a href="https://www.h2database.com/html/tutorial.html#upgrade_backup_restore">Upgrade, Backup, and Restore</a>
 * @see <a href="https://www.h2database.com/html/tutorial.html#fulltext">Fulltext Search</a>
 * @see <a href="https://www.h2database.com/html/features.html#in_memory_databases">In-Memory Databases</a>
 * @see <a href="https://www.h2database.com/html/features.html#execute_sql_on_connection">Execute SQL on Connection</a>
 * @see <a href="https://www.h2database.com/html/datatypes.html">Data Types</a>
 * @since 2021-12-18
 */
public class H2Repository implements AutoCloseable {
    private final Connection connection;

    public H2Repository() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:h2:mem:test");
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
