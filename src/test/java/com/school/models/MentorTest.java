//package com.school.models;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class MentorTest {
//
//    private Mentor admin;
//
//    @BeforeEach
//    public void before() {
//        this.admin = new Mentor("Mateusz", "Ostafil", "password", "mat@gmail.com", "admin");
//    }
//
//    @Test
//    public void testEmptyConstructor() {
//        assertNotNull(new Mentor());
//    }
//
//    @Test
//    public void testGetName() {
//        assertEquals("Mateusz", admin.getName());
//    }
//
//    @Test
//    public void testGetSurname() {
//        assertEquals("Ostafil", admin.getSurname());
//    }
//
//    @Test
//    public void testGetPassword() {
//        assertEquals("password", admin.getPassword());
//    }
//
//    @Test
//    public void testGetMail() {
//        assertEquals("mat@gmail.com", admin.getMail());
//    }
//
//    @Test
//    public void testGetStatus() {
//        assertEquals("admin", admin.getStatus());
//    }
//
//    @Test
//    public void testGetId() {
//        assertNull(admin.getId());
//    }
//
//
//    @Test
//    public void testSetName() {
//        admin.setName("Jan");
//        assertEquals("Jan", admin.getName());
//    }
//
//    @Test
//    public void testSetSurname() {
//        admin.setSurname("Nowak");
//        assertEquals("Nowak", admin.getSurname());
//    }
//
//    @Test
//    public void testSetPassword() {
//        admin.setPassword("1234");
//        assertEquals("1234", admin.getPassword());
//    }
//
//    @Test
//    public void testSetMail() {
//        admin.setMail("admin@gmail.com");
//        assertEquals("admin@gmail.com", admin.getMail());
//    }
//
//    @Test
//    public void testSetStatus() {
//        admin.setStatus("student");
//        assertEquals("student", admin.getStatus());
//    }
//
//    @Test
//    public void testSetId() {
//        Integer id = 1;
//        admin.setId(id);
//        assertEquals(id, admin.getId());
//    }
//
//    @Test
//    public void testDetailsObjectsAsString() {
//        String info = String.format("\nName: %s\nSurname: $%s%nLogin: %s%n", admin.name, admin.surname, admin.id);
//        assertEquals(info, admin.toString());
//    }
//}