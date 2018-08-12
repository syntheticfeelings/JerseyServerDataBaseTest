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
        String queryUser = String.format("INSERT INTO user_jdbc (name,second_name,age) VALUES ('%s', '%s', '%s');",
                user.getFirstName(),
                user.getSecondName(),
                user.getAge());
        DbConnector.startConnection();
        try {
            DbConnector.getStatement().executeUpdate(queryUser);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnector.closeConnection();
        }
    }

    public boolean authorizeUser(String firstName, String secondName) {
        String request = String.format("SELECT * FROM user_jdbc WHERE name ='%s' AND second_name ='%s';", firstName, secondName);
        DbConnector.startConnection();
        try {
            ResultSet rs = DbConnector.getStatement().executeQuery(request);
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnector.closeConnection();
        }
        return false;
    }

    public UserList getUsers() {
        String JsonRequest = "SELECT * FROM user_jdbc;";
        DbConnector.startConnection();
        users.clear();
        try {
            ResultSet rs = DbConnector.getStatement().executeQuery(JsonRequest);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String secondName = rs.getString("second_name");
                int age = rs.getInt("age");
                users.add(new User(id, name, secondName, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnector.closeConnection();
        }
        return new UserList(users);
    }

}
