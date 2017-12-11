package com.school.dao;

import com.school.models.Quest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class QuestDAOTest {
    ResultSet rs = mock(ResultSet.class);

    @Test
    void createQuestFromResultSetTest() throws SQLException {
        Integer id = 1;
        Integer prize = 50;
        Mockito.when(rs.getInt("id")).thenReturn(id);
        Mockito.when(rs.getString("title")).thenReturn("SI on time");
        Mockito.when(rs.getString("info")).thenReturn("Send SI project before deadline");
        Mockito.when(rs.getInt("prize")).thenReturn(prize);
        Mockito.when(rs.getString("quest_category")).thenReturn("self");

        QuestDAO questDao = new QuestDAO();
        Quest quest = questDao.createQuestFromResultSet(rs);

        assertNotNull(quest);
        assertEquals(id, quest.getId());
        assertEquals("SI on time", quest.getTitle());
        assertEquals("Send SI project before deadline", quest.getInfo());
        assertEquals(prize, quest.getPrize());
        assertEquals("self", quest.getCategory());
    }
}