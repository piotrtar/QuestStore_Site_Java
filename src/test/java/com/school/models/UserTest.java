package com.school.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private class AbstractUser extends User {
        private AbstractUser(String name, String surname, String password, String mail, String status) {
            super(name, surname, password, mail, status);
        }
    }

    AbstractUser user;

    @BeforeEach
    public void before() {
        this.user = new AbstractUser("name", "surname", "password", "mail", "status");
    }

    @Test
    void testGetStatus() {
        assertEquals("status", this.user.getStatus());
    }

    @Test
    void testSetStatus() {
        this.user.setStatus("new");
        assertEquals("new", this.user.getStatus());
    }

    @Test
    void testToString() {
        this.user.setId(1);
        assertEquals("\n" +
                "Name: name\n" +
                "Surname: $surname\n" +
                "Login: 1\n", this.user.toString());
    }

    @Test
    void testSetName() {
        this.user.setName("new");
        assertEquals("new", this.user.getName());
    }

    @Test
    void testGetName() {
        assertEquals("name", this.user.getName());
    }

    @Test
    void testGetSurname() {
        assertEquals("surname", this.user.getSurname());
    }

    @Test
    void testSetSurname() {
        this.user.setSurname("new");
        assertEquals("new", this.user.getSurname());
    }

    @Test
    void testSetPassword() {
        this.user.setPassword("new");
        assertEquals("new", this.user.getPassword());
    }

    @Test
    void testGetPassword() {
        assertEquals("password", this.user.getPassword());
    }

    @Test
    void testSetMail() {
        this.user.setMail("new");
        assertEquals("new", this.user.getMail());
    }

    @Test
    void testGetMail() {
        assertEquals("mail", this.user.getMail());
    }

    @Test
    void testGetId() {
        assertNull(this.user.getId());
    }

    @Test
    void testSetId() {
        Integer id = 1;
        this.user.setId(1);
        assertEquals(id, this.user.getId());
    }
}