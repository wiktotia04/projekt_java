package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseController {
    private static final String URL = "jdbc:sqlite:identifier.sqlite";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS info_walut (\n"
                + "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "    walutaDb TEXT NOT NULL,\n"
                + "    wartoscDb REAL NOT NULL\n"
                + ");";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void clearTable() {
        String sql = "DELETE FROM info_walut";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveInfoWalut(info_walut info) {
        String sql = "INSERT INTO info_walut(walutaDb, wartoscDb) VALUES(?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, info.getWalutaDb());
            pstmt.setDouble(2, info.getWartoscDb());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<info_walut> getWaluty(String nazwa) {
        List<info_walut> waluty = new ArrayList<>();
        String sql = "SELECT * FROM info_walut WHERE walutaDb = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nazwa);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                info_walut walut = new info_walut();
                walut.setId(rs.getInt("id"));
                walut.setWalutaDb(rs.getString("walutaDb"));
                walut.setWartoscDb(rs.getDouble("wartoscDb"));
                waluty.add(walut);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return waluty;
    }

    public static List<info_walut> getWyniki() {
        List<info_walut> waluty = new ArrayList<>();
        String sql = "SELECT * FROM info_walut";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                info_walut walut = new info_walut();
                walut.setId(rs.getInt("id"));
                walut.setWalutaDb(rs.getString("walutaDb"));
                walut.setWartoscDb(rs.getDouble("wartoscDb"));
                waluty.add(walut);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return waluty;
    }
}
