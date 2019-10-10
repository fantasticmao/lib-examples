package priv.mm.h2;

import org.h2.tools.Server;

import java.sql.*;

/**
 * H2Demo
 *
 * @author maomao
 * @since 2019-10-10
 */
public class H2Demo {

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        Server server = Server.createWebServer();
        server.start();

        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "")) {
            try (Statement statement = connection.createStatement()) {
                statement.execute("CREATE TABLE IF NOT EXISTS user(" +
                        "id INT AUTO_INCREMENT COMMENT '逻辑主键'," +
                        "name VARCHAR(8) COMMENT '用户名称'" +
                        ")");
            }

            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO user(name) VALUES ('Tom'), ('Sam')")) {
                statement.execute();
            }

            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM user")) {
                ResultSet resultSet = statement.executeQuery();
                int id;
                String name;
                while (resultSet.next()) {
                    id = resultSet.getInt(1);
                    name = resultSet.getString(2);
                    System.out.printf("id:%d name:%s%n", id, name);
                }
            }
        }

        server.stop();
    }
}
