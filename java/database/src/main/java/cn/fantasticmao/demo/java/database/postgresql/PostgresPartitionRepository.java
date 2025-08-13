package cn.fantasticmao.demo.java.database.postgresql;

import cn.fantasticmao.demo.java.database.User;

import javax.annotation.Nullable;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * PostgresPartitionRepository
 *
 * @author fantasticmao
 * @see <a href="https://www.postgresql.org/docs/current/ddl-partitioning.html">Table Partitioning</a>
 * @since 2024-09-29
 */
public class PostgresPartitionRepository implements AutoCloseable {
    private final Connection connection;

    public PostgresPartitionRepository() throws SQLException {
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
            statement.executeUpdate("DROP TABLE IF EXISTS t_user_partition");
            statement.executeUpdate("""
                CREATE TABLE IF NOT EXISTS t_user_partition(
                    id          INTEGER,
                    name        VARCHAR(32),
                    age         INTEGER,
                    email       VARCHAR(32),
                    birthday    TIMESTAMP,
                    PRIMARY KEY (id, birthday)
                ) PARTITION BY RANGE (birthday)""");
            statement.executeUpdate("""
                CREATE TABLE IF NOT EXISTS t_user_partition_2004 PARTITION OF t_user_partition
                FOR VALUES FROM ('2004-01-01') TO ('2004-12-31')""");
            statement.executeUpdate("""
                CREATE TABLE IF NOT EXISTS t_user_partition_2005 PARTITION OF t_user_partition
                FOR VALUES FROM ('2005-01-01') TO ('2005-12-31')""");
            statement.executeUpdate("""
                CREATE TABLE IF NOT EXISTS t_user_partition_2006 PARTITION OF t_user_partition
                FOR VALUES FROM ('2006-01-01') TO ('2006-12-31')""");
            statement.executeUpdate("""
                CREATE TABLE IF NOT EXISTS t_user_partition_2007 PARTITION OF t_user_partition
                FOR VALUES FROM ('2007-01-01') TO ('2007-12-31')""");
        }
    }

    public boolean insert(User user) throws SQLException {
        String sql = "INSERT INTO t_user_partition(id, name, age, email, birthday) VALUES (?, ?, ?, ?, ?)";
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
        String sql = "SELECT id, name, age, email, birthday FROM t_user_partition ORDER BY id";
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
        String sql = "SELECT id, name, age, email, birthday FROM t_user_partition WHERE name = ?";
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
