package com.school.dao;

import com.school.models.Student;
import com.school.models.Wallet;
import com.school.onlineshop.part1.Basket;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BasketDAO extends DBConnection {

    private static String table_name = "shop_items";

    public BasketDAO(){

        super(table_name);

    }

    public void deleteBasket(Student student) {

        String query = "DELETE FROM students_basket WHERE student_id = '" + student.getId() + "'";

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }









}
