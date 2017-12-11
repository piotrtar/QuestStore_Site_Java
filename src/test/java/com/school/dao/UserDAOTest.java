package com.school.dao;

import com.school.models.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserDAOTest {

    ResultSet rs = mock(ResultSet.class);

    public void testGetUser(String statusTxt) throws SQLException{
        Mockito.when(rs.getString("first_name")).thenReturn("Jola");
        Mockito.when(rs.getString("last_name")).thenReturn("Bond");
        Mockito.when(rs.getString("password")).thenReturn("xxx");
        Integer id = 1;
        Mockito.when(rs.getInt("id_number")).thenReturn(id);
        Mockito.when(rs.getString("email")).thenReturn("jola@gmail.com");
        Mockito.when(rs.getString("status")).thenReturn(statusTxt);

        UserDAO userDao = new UserDAO();
        User user = userDao.getUser(rs);

        assertNotNull(user);
        assertEquals("Jola", user.getName());
        assertEquals("Bond", user.getSurname());
        assertEquals("xxx", user.getPassword());
        assertEquals(id, user.getId());
        assertEquals("jola@gmail.com", user.getMail());
        assertEquals(statusTxt, user.getStatus());
    }

    @Test
    public void testGetUserAsAdmin() throws SQLException {
        testGetUser("admin");
    }

    @Test
    public void testGetUserAsMentor() throws  SQLException {
        testGetUser("admin");
    }

    @Test
    public void testGetUserAsStudent() throws SQLException {
        testGetUser("student");
    }
}