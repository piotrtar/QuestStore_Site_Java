package com.school.dao;

import com.school.models.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserInterfaceDAO {

    User load(String id, String password);
    void saveUser(User user);
    User getUser(ResultSet rs) throws SQLException;

}
