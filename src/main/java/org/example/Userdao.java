package org.example;

import java.sql.*;
import java.util.Optional;

public class Userdao {
    public static final String NAME_USER="root";

    public static final String PASSWORD="root";

    public static final String URL="jdbc:mysql://localhost:3306/mydbtest";

    public static Connection connection;

    public static Statement statement;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL,NAME_USER,PASSWORD);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }
    static {
        try {
            statement=connection.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public User get(String login) {
        if (connection != null) {
            String sql = "select * from users where login = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, login);
                try (ResultSet rs = preparedStatement.executeQuery()) {
                    if (rs.next()) {
                        return new User(rs.getString("login"), rs.getString("email"), rs.getString("password"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean save(User user) {
        boolean isSave = false;

        if (connection != null) {
            String sql = "insert into users (login, password, email) values (?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.executeUpdate();
                isSave = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isSave;
    }

    public boolean update(User user) {
        boolean isSave = false;

        if (connection != null) {
            String sql = "update users set login = ? email = ? password = ? where login = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.setString(4, user.getLogin());
                preparedStatement.executeUpdate();
                isSave = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isSave;
    }

    public boolean delete(User user) {
        boolean isSave = false;

        if (connection != null) {
            String sql = "delete from users where login = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.executeUpdate();
                isSave = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isSave;
    }
}
