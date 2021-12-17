package cn.fantasticmao.demo.java.database.h2;

import cn.fantasticmao.demo.java.database.User;
import org.h2.tools.Server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * H2Repository
 *
 * @author fantasticmao
 * @see <a href="http://www.h2database.com/html/features.html#in_memory_databases">In-Memory Databases</a>
 * @see <a href="http://www.h2database.com/html/tutorial.html#upgrade_backup_restore">Upgrade, Backup, and Restore</a>
 * @since 2021-12-18
 */
public class H2Repository implements AutoCloseable {
    private final Server server;

    public H2Repository() throws SQLException {
        this.server = Server.createWebServer();
        this.server.start();
        this.createTable();
    }

    @Override
    public void close() {
        this.server.shutdown();
    }

    public void createTable() throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS T_USER");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS T_USER(" +
                "id INT PRIMARY KEY," +
                "name VARCHAR(32))");
        }
    }

    public boolean insert(User user) throws SQLException {
        String sql = "INSERT INTO T_USER(id, name) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            return statement.executeUpdate() > 0;
        }
    }

    public List<User> selectAll() throws SQLException {
        List<User> result = new ArrayList<>();
        String sql = "SELECT id, name FROM T_USER ORDER BY id";
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
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
