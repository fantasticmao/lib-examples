package cn.fantasticmao.demo.java.database;

/**
 * Point
 *
 * @author fantasticmao
 * @since 2025-08-13
 */
public class Point {
    private double lon;
    private double lat;

    public Point() {
    }

    public Point(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "Point{" +
            "lon=" + lon +
            ", lat=" + lat +
            '}';
    }

    public String toEWKT() {
        return String.format("SRID=4326;POINT(%f %f)", lon, lat);
    }

    // getter and setter

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

}
