package com.school.dao;


import com.school.models.Quest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QuestDAO extends DBConnection {

    private static final String tableName = "quests";

    public QuestDAO() {

        super(tableName);
    }

    public Quest getQuestById(Integer id) {

        Quest course = null;

        String query = "SELECT * FROM quests WHERE quest_id = " + id + ";";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            if (rs.next()) {
                course = createQuestFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    public ArrayList<Quest> getAllQuests() {

        ArrayList<Quest> allQuests = new ArrayList<>();
        Quest quest = null;

        String query = "SELECT * FROM quests";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                quest = createQuestFromResultSet(rs);
                allQuests.add(quest);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allQuests;
    }


    public static Quest createQuestFromResultSet(ResultSet rs) throws SQLException {

        Integer id = rs.getInt("quest_id");
        String title = rs.getString("title");
        String info = rs.getString("info");
        Integer prize = rs.getInt("prize");
        String category = rs.getString("category");

        Quest quest = new Quest(title, info, prize, category);
        quest.setId(id);

        return quest;
    }

    public void saveQuest(Quest quest) {

        String query = "INSERT INTO quests (title, info, prize, category) VALUES(?,?,?,?)";

        try (PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, quest.getTitle());
            statement.setString(2, quest.getInfo());
            statement.setInt(3, quest.getPrize());
            statement.setString(4, quest.getCategory());

            statement.executeUpdate();
            System.out.println("Saved");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudentQuest(Integer questID, Integer studentID) {

        String query = "DELETE FROM students_quests WHERE quest_id = '" + questID + "'AND student_id = '" + studentID + "'";

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteQuest(Integer questID) {

        String query = "DELETE FROM quests WHERE quest_id = '" + questID + "'";

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

