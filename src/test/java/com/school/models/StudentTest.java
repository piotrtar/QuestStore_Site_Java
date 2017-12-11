//package com.school.models;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class StudentTest {
//
//    Student student;
//
//    @BeforeEach
//    public void before() {
//        this.student = new Student("Adam", "Kowalski", "adam@gmail.com", "xxx", "student");
//    }
//
//    @Test
//    public void testEmptyConstructor() {
//        assertNotNull(new Student());
//    }
//
//    @Test
//    public void testConstructor() {
//        assertNotNull(new Student("Adam", "Kowalski", "adam@gmail.com", "xxx", "student"));
//    }
//
//    @Test
//    public void testGetWallet() {
//        assertNotNull(student.getWallet());
//    }
//
//    @Test
//    public void testGetCourse() {
//        assertNull(student.getCourse());
//    }
//
//    @Test
//    public void testSetWallet() {
//        Wallet wallet = new Wallet();
//        student.setWallet(wallet);
//        assertEquals(wallet, student.getWallet());
//    }
//
//    @Test
//    public void testSetCourse() {
//        Course course = new Course("test");
//        student.setCourse(course);
//        assertEquals(course, student.getCourse());
//    }
//
//
//}