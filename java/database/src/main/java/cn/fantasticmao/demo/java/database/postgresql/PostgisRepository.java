package cn.fantasticmao.demo.java.database.postgresql;

import cn.fantasticmao.demo.java.database.Point;
import cn.fantasticmao.demo.java.database.Shop;
import net.postgis.jdbc.PGgeography;
import org.postgresql.PGConnection;
import org.postgresql.util.PGobject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * PostgisRepository
 * <p>
 * 启动 PostGIS Docker 容器
 *
 * @author fantasticmao
 * @see <a href="https://postgis.net/docs/manual-3.5/using_postgis_dbmanagement.html#RefObject">PostGIS Spatial Data Model</a>
 * @see <a href="https://postgis.net/docs/manual-3.5/using_postgis_dbmanagement.html#PostGIS_Geometry">PostGIS Geometry Data Type</a>
 * @see <a href="https://postgis.net/docs/manual-3.5/using_postgis_dbmanagement.html#PostGIS_Geography">PostGIS Geography Data Type</a>
 * @see <a href="https://postgis.net/docs/manual-3.5/using_postgis_dbmanagement.html#spatial_ref_sys">PostGIS Spatial Reference Systems</a>
 * @since 2025-08-12
 */
public class PostgisRepository implements AutoCloseable {
    private final Connection connection;

    public PostgisRepository() throws SQLException, ClassNotFoundException {
        final String jdbcUrl = "jdbc:postgresql://localhost:5432/fantasticmao";
        final Properties props = new Properties();
        props.put("user", "postgres");
        props.put("password", "123456");
        props.put("loginTimeout", "3");
        props.put("connectTimeout", "3");
        props.put("socketTimeout", "3");

        this.connection = DriverManager.getConnection(jdbcUrl, props);
        if (this.connection instanceof PGConnection conn) {
            conn.addDataType("geometry", (Class<? extends PGobject>) Class.forName("net.postgis.jdbc.PGgeometry"));
            conn.addDataType("geography", (Class<? extends PGobject>) Class.forName("net.postgis.jdbc.PGgeography"));
        }
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
            statement.executeUpdate("DROP TABLE IF EXISTS t_gis");
            statement.executeUpdate("""
                CREATE TABLE IF NOT EXISTS t_gis (
                    shop_name VARCHAR(32),
                    location  GEOGRAPHY(POINT, 4326)
                )""");
        }
    }

    public boolean insert(Shop shop) throws SQLException {
        String sql = "INSERT INTO t_gis (shop_name, location) VALUES (?, ST_GeogFromText(?))";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, shop.getShopName());
            statement.setString(2, shop.getLocation().toEWKT());
            return statement.executeUpdate() > 0;
        }
    }

    public List<Shop> selectAll() throws SQLException {
        List<Shop> result = new ArrayList<>();
        String sql = "SELECT shop_name, location FROM t_gis";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String shopName = resultSet.getString(1);
                    PGgeography location = (PGgeography) resultSet.getObject(2);
                    Point point = new Point(location.getGeometry().getFirstPoint().x, location.getGeometry().getFirstPoint().y);
                    result.add(new Shop(shopName, point));
                }
            }
        }
        return result;
    }

    public List<List<String>> selectAllAsGeoJson() throws SQLException {
        List<List<String>> result = new ArrayList<>();
        String sql = "SELECT shop_name, ST_AsEWKT(location), ST_AsGeoJSON(location) FROM t_gis";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String col1 = resultSet.getString(1);
                    String col2 = resultSet.getString(2);
                    String col3 = resultSet.getString(3);
                    List<String> row = List.of(col1, col2, col3);
                    result.add(row);
                }
            }
        }
        return result;
    }

    public List<List<String>> selectByDistance(Point point, int distance) throws SQLException {
        List<List<String>> result = new ArrayList<>();
        String sql = """
            SELECT shop_name, ST_AsText(location), distance
            FROM (
                SELECT shop_name, location, ST_Distance(location, ST_Point(?, ?, 4326)::geography) AS distance
                FROM t_gis
            ) AS t
            WHERE distance <= ?""";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, point.getLon());
            statement.setDouble(2, point.getLat());
            statement.setInt(3, distance);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String col1 = resultSet.getString(1);
                    String col2 = resultSet.getString(2);
                    String col3 = resultSet.getString(3);
                    List<String> row = List.of(col1, col2, col3);
                    result.add(row);
                }
            }
        }
        return result;
    }
}
