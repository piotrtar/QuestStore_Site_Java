package com.school.dao;

import com.school.models.Course;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseDAOTest {

    private ResultSet rs = mock(ResultSet.class);

    @Test
    public void testConstructor() {
        assertNotNull(new CourseDAO());
    }

    @Test
    public void testCreateCourseFromResultSet() throws SQLException{
        Integer i = 1;
        Mockito.when(rs.getInt("id")).thenReturn(i);
        Mockito.when(rs.getString("course_name")).thenReturn("course");

        CourseDAO courseDao = new CourseDAO();
        Course course = courseDao.createCourseFromResultSet(rs);

        assertNotNull(course);
        assertEquals(i, course.getId());
        assertEquals("course", course.getName());
    }
}