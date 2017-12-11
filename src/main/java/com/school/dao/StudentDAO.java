package com.school.dao;
import com.school.models.*;
import com.school.onlineshop.part1.Basket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentDAO extends UserDAO {

    private Student student;

    public StudentDAO(Student student) {
        this.student = student;
    }

    public void save() {

        saveUser(student);

        saveStudentRecords();
    }

    public void saveStudentBasketItem(Artifact artifact) {

        String query = "INSERT INTO students_basket (student_id, basket_artifact_id) VALUES(?,?)";
        try (PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, student.getId());
            statement.setInt(2, artifact.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveStudentRecords() {

        String query = "INSERT INTO students (student_id, course_id, wallet_id) VALUES(?,?,?)";

        try (PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, UserDAO.getLastUserCreatedId());
//            statement.setInt(2, student.getCourse().getId());
            statement.setInt(3, WalletDAO.getLastWalletCreatedId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer getStudentWalletId() {

        Integer studentWalletId = null;
        String query = "SELECT wallet_id FROM students WHERE student_id = " + student.getId() + ";";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {

                studentWalletId = rs.getInt("wallet_id");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentWalletId;
    }

    public Integer getStudentCourseId() {

        Integer studentCourseId = null;
        String query = "SELECT course_id FROM students WHERE student_id = " + student.getId() + ";";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {

                studentCourseId = rs.getInt("course_id");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentCourseId;
    }

    public ArrayList<Artifact> getStudentArtefacts() {

        ArrayList<Artifact> artifacts = new ArrayList<>();

        String query = "SELECT student_id, artifact_id FROM students_artifacts WHERE student_id = " + student.getId() + ";";
        ArtifactDAO artefactDAO = new ArtifactDAO();

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {

                Integer artefactID = rs.getInt("artifact_id");
                Artifact artifact = artefactDAO.getArtefactById(artefactID);
                artifacts.add(artifact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artifacts;
    }

    public ArrayList<Quest> getStudentQuests() {

        ArrayList<Quest> quests = new ArrayList<>();

        String query = "SELECT student_id, quest_id FROM students_quests WHERE student_id = " + student.getId() + ";";
        QuestDAO questDAO = new QuestDAO();

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {

                Integer questID = rs.getInt("quest_id");
                Quest quest = questDAO.getQuestById(questID);
                quests.add(quest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quests;
    }

    public Basket getStudentBasket() {

        Basket basket = new Basket();
        String query = "SELECT * FROM students_basket WHERE student_id = " + student.getId() + ";";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {

                Integer items_in_basket_id = rs.getInt("basket_artifact_id");
                addProductToBasket(items_in_basket_id, basket);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return basket;
    }

    private Basket addProductToBasket(Integer product_id, Basket basket) {

        String query = "SELECT artifact_id, title, price, category FROM artifacts WHERE artifact_id = " + product_id + ";";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {

                String item_name = rs.getString("title");
                String item_info = rs.getString("category");
                Integer price = rs.getInt("price");
                Integer id = rs.getInt("artifact_id");

                Artifact artifact = new Artifact(item_name, price, item_info);
                artifact.setId(id);
                basket.addProduct(artifact);
            }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return basket;

}
    public void deleteProductFromBasket(Integer product_id) {

            String query = "DELETE FROM students_basket WHERE student_id = '" + student.getId()  + "'AND basket_artifact_id = '" + product_id + "'";

            try {
                Statement st = conn.createStatement();
                st.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }


    public static void editStudent(String first_name, String last_name, String mail, String password, Integer id) {

        String query = "UPDATE users SET first_name = '" + first_name + "', last_name = '" + last_name + "', email = '" + mail + "', password = '" + password + "' WHERE id_number = '" + id + "'";

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}

