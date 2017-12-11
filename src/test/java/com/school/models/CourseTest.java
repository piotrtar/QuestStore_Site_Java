//package com.school.models;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//class CourseTest {
//
//    private Course course;
//
//    @BeforeEach
//    public void before() {
//        course = new Course(1, "name");
//    }
//
//    @Test
//    public void testCourseHasConstructorWithOnlyName() {
//        assertNotNull(new Course("name"));
//    }
//
//    @Test
//    public void testCourseHasConstructor() {
//        assertNotNull(new Course(1, "name"));
//    }
//
//    @Test
//    public void testGetNameReturnsExpectedValue() {
//        String actual = this.course.getName();
//        assertEquals("name", actual);
//    }
//
//    @Test
//    public void testSetNameChangesValueToExpected() {
//        this.course.setName("new name");
//        String actual = this.course.getName();
//        assertEquals("new name", actual);
//    }
//
//    @Test
//    public void testGetIdReturnExpectedValue() {
//        int actual = this.course.getId();
//        assertEquals(1, actual);
//    }
//
//    @Test
//    public void testSetIdChanagesValueToExpected() {
//        this.course.setId(2);
//        int actual = this.course.getId();
//        assertEquals(2, actual);
//    }
//
//}
