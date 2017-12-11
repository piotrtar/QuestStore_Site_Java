package com.school.dao;

import com.school.models.Admin;
import com.school.models.Mentor;
import com.school.models.Student;
import com.school.models.User;

import java.sql.*;
import java.util.ArrayList;


public class UserDAO extends DBConnection implements UserInterfaceDAO {

    private static final String tableName = "users";

    public UserDAO() {

        super(tableName);
    }

    @Override
    public User load(String id, String password) {

        String query = "SELECT first_name, last_name, password, id_number, email, status FROM users" +
                       " WHERE id_number = '" + id + "' AND password = '" + password + "'";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            if (rs.next()) {
                return getUser(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public User getUser(ResultSet rs) throws SQLException {

        String name = rs.getString("first_name");
        String surname = rs.getString("last_name");
        String password = rs.getString("password");
        Integer idNum = rs.getInt("id_number");
        String mail = rs.getString("email");
        String status = rs.getString("status");

        if(status.equals("student")){
            Student student = new Student(name, surname, password, mail);
            student.setId(idNum);
            return student;
        }
        if(status.equals("mentor")){
            Mentor mentor = new Mentor(name, surname, password, mail);
            mentor.setId(idNum);
            return mentor;
        }
        if(status.equals("admin")){
            Admin admin = new Admin(name, surname, password, mail);
            admin.setId(idNum);
            return admin;
        }
        return null;
    }


    public void saveUser(User user) {

        String query = "INSERT INTO users" +
                       "(first_name, last_name, password, email, status)" +
                       " VALUES(?,?,?,?,?)";

        try (PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getMail());
            statement.setString(5, user.getStatus());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getAllUsersByStatus(String status) {

        ArrayList<User> foundUsers = new ArrayList<>();
        User user;

        String query = "SELECT * FROM users WHERE status = '" + status + "' ";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
             while(rs.next()){
                user = getUser(rs);
                foundUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foundUsers;
    }

    public User getUserById(Integer userId) {

        User user = null;
        String query = "SELECT * FROM users WHERE id_number = '" + userId + "' ";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
             user = getUser(rs);
             return user;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return user;
    }

    public static Integer getLastUserCreatedId() {

        Integer loadedUserId = null;
        String lastId = "SELECT * FROM users WHERE id_number = (SELECT MAX(id_number) FROM users)";

        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(lastId);

            if (rs.next()) {
                loadedUserId = rs.getInt("id_number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loadedUserId;
    }


    public static void editUser(String first_name, String last_name, String password, String mail, Integer id) {

        String query = "UPDATE users SET first_name = '" + first_name + "', last_name = '" + last_name + "', email = '" + mail + "', password = '" + password + "' WHERE id_number = '" + id + "'";

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(Integer id){

        String query = "DELETE FROM users WHERE id_number = '" + id + "'";

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
