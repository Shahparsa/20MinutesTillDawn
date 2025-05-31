package com.tilldawn.database;

import com.tilldawn.models.App;
import com.tilldawn.models.SecurityQuestion;
import com.tilldawn.models.User;
import com.tilldawn.models.enums.SecurityQuestionType;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:sqlite:game.db";

    public static void createDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            DatabaseMetaData meta = conn.getMetaData();
            try (ResultSet tables = meta.getTables(null, null, "users", null)) {
                if (!tables.next()) {
                    createUserTable();
                }
            }
        } catch (SQLException e) {}
    }

    public static void createUserTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
            "username TEXT PRIMARY KEY," +
            "password TEXT NOT NULL," +
            "securityQuestion TEXT," +
            "answer TEXT," +
            "avatarPath TEXT," +
            "score INTEGER DEFAULT 0," +
            "kills INTEGER DEFAULT 0," +
            "timeAlive REAL DEFAULT 0.0)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {}
    }

    public static User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new User(
                    rs.getString("username"),
                    rs.getString("password"),
                    new SecurityQuestion(
                        SecurityQuestionType.getSecurityFromQuestion(rs.getString("securityQuestion")),
                        rs.getString("answer")
                    ),
                    rs.getString("avatarPath"),
                    rs.getInt("score"),
                    rs.getInt("kills"),
                    rs.getFloat("timeAlive")
                );
            }
        } catch (SQLException e) {}
        return null;
    }

    public static void registerUser(User user) {
        String sql = "INSERT INTO users(username, password, securityQuestion, answer, avatarPath) " +
            "VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getSecurityQuestion().getType().getQuestion());
            pstmt.setString(4, user.getSecurityQuestion().getAnswer());
            pstmt.setString(5, user.getAvatarPath());

            pstmt.executeUpdate();
        } catch (SQLException e) {}
    }

    public static void changePassword(String username, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newPassword);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {}
    }

    public static void changeUsername(String currentUsername, String newUsername) {
        String sql = "UPDATE users SET username = ? WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newUsername);
            pstmt.setString(2, currentUsername);
            pstmt.executeUpdate();
        } catch (SQLException e) {}
    }

    public static void deleteUser(String username) {
        String sql = "DELETE FROM users WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {}
    }

    public static void changeAvatar(String username, String newAvatarPath) {
        String sql = "UPDATE users SET avatarPath = ? WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newAvatarPath);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {}
    }

    public static void updateUserStats(String username, int score, int kills, float timeAlive) {
        String sql = "UPDATE users SET score = ?, kills = ?, timeAlive = ? WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, score);
            pstmt.setInt(2, kills);
            pstmt.setFloat(3, timeAlive);
            pstmt.setString(4, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {}
    }

    public static void getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                User user = new User(
                    rs.getString("username"),
                    rs.getString("password"),
                    new SecurityQuestion(
                        SecurityQuestionType.getSecurityFromQuestion(rs.getString("securityQuestion")),
                        rs.getString("answer")
                    ),
                    rs.getString("avatarPath"),
                    rs.getInt("score"),
                    rs.getInt("kills"),
                    rs.getFloat("timeAlive")
                );
                users.add(user);
            }
        } catch (SQLException e) {}
        App.setUsers(users);
    }
}
