package com.syntheticfeelings.dao;

import com.syntheticfeelings.model.User;
import com.syntheticfeelings.model.UserList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDao {

    private static UserDao instance;

    private List<User> users = new ArrayList<>();

    private UserDao() {
    }

    public static synchronized UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public void addUser(User user) {
        users.add(user);
        String queryUser = String.format("INSERT INTO users (email,password,telephone,uuid) VALUES ('%s', '%s', '%s','%s');",
                user.getEmail(),
                user.getPassword(),
                user.getTelephone(),
                user.getUuid());
        DbConnector.startConnection();
        try {
            DbConnector.getStatement().executeUpdate(queryUser);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnector.closeConnection();
        }
    }

    public String authorizeUser(String email, String password) {
        String request = String.format("SELECT * FROM users WHERE email ='%s' AND password ='%s';", email, password);
        DbConnector.startConnection();
        try {
            ResultSet rs = DbConnector.getStatement().executeQuery(request);
            while (rs.next()) {
                String uuid = rs.getString("uuid");
                return uuid;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnector.closeConnection();
        }
        return "WRONG PASSWORD OR USER NOT FOUND";
    }

    public UserList getUsers() {
        String JsonRequest = "SELECT * FROM users;";
        DbConnector.startConnection();
        users.clear();
        try {
            ResultSet rs = DbConnector.getStatement().executeQuery(JsonRequest);
            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                int telephone = rs.getInt("telephone");
                String uuid = rs.getString("uuid");
                users.add(new User(id, email, password, telephone, uuid));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnector.closeConnection();
        }
        return new UserList(users);
    }

}
