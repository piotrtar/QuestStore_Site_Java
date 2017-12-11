package com.school.dao;

import com.school.models.Artifact;
import com.school.models.Quest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ArtifactDAO extends  DBConnection{

    private static String table_name = "artefacts";
//    ArrayList<Artifact> artefacts = new ArrayList<>();

    public ArtifactDAO(){

        super(table_name);

    }

    public Artifact getArtefactById(Integer id) {

        Artifact artifact = null;

        String query = "SELECT * FROM artifacts WHERE artifact_id = " + id + ";";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            if(rs.next()){
                artifact = createArtefactsFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            return artifact;
        }
    }

    public static Artifact createArtefactsFromResultSet(ResultSet rs) throws SQLException {

        Integer id = rs.getInt("artifact_id");
        String title = rs.getString("title");
        Integer price = rs.getInt("price");
        String category = rs.getString("category");

        Artifact artifact = new Artifact(title, price, category);
        artifact.setId(id);

        return artifact;
    }

    public ArrayList<Artifact> getAllArtifacts() {

        ArrayList<Artifact> allQuests = new ArrayList<>();
        Artifact artifact = null;

        String query = "SELECT * FROM artifacts";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                artifact = createArtefactsFromResultSet(rs);
                allQuests.add(artifact);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allQuests;
    }

    public void deleteStudentArtifact(Integer artifactID, Integer studentID){

        String query = "DELETE FROM students_artifacts WHERE artifact_id = '" + artifactID + "'AND student_id = '" + studentID + "'" ;

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteArtifact(Integer artifactID){

        String query = "DELETE FROM artifacts WHERE artifact_id = '" + artifactID + "'" ;

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public void saveArtifact(Artifact artifact) {

        String query = "INSERT INTO artifacts (title, price, category) VALUES(?,?,?)";

        try (PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1,artifact.getTitle());
            statement.setInt(2, artifact.getPrice());
            statement.setString(3, artifact.getCategory());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void editArtifact(String title, String info, Integer price, Integer artifact_id) {

        String query = "UPDATE artifacts SET title = '" + title + "', price = '" + price + "'," +
                      " category = '" + info + "' WHERE artifact_id = '" + artifact_id + "'";

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
