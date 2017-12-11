
package com.school.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestTest {
    private Quest quest;

    @BeforeEach
    void setUp() {
        this.quest = new Quest("SI on time", "SI project before deadline", 50, "self");
        Integer id = 10;
        quest.setId(id);
    }

    @Test
    void getTitleTest() {
        String expected = "SI on time";
        String actual;
        actual = quest.getTitle();
        assertEquals(expected, actual);
    }

    @Test
    void getinfoTest() {
        String expected = "SI project before deadline";
        String actual;
        actual = quest.getInfo();
        assertEquals(expected, actual);
    }

    @Test
    void getPrizeTest() {
        Integer expected = 50;
        Integer actual;
        actual = quest.getPrize();
        assertEquals(expected, actual);
    }

    @Test
    void getIdTest() {
        Integer expected = 10;
        Integer actual;
        actual = quest.getId();
        assertEquals(expected, actual);
    }

    @Test
    void getCategoryTest() {
        String expected = "self";
        String actual;
        actual = quest.getCategory();
        assertEquals(expected, actual);
    }

    @Test
    void constructorTest() {
        assertNotNull(new Quest("test", "test", 20, "test"));
    }
    
}