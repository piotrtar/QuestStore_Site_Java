package com.school.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.school.models.Wallet;

public class WalletDAO extends DBConnection {


    private static final String tableName = "wallets";

    public WalletDAO() {
        super(tableName);
    }

    Wallet wallet = null;
    public ArrayList<Wallet> getAllWallets() {

        ArrayList<Wallet> wallets = new ArrayList<>();

        String query = "SELECT * FROM " + tableName + ";";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                createWalletFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return wallets;
        }
    }

    public Wallet getWalletById(Integer id) {

        Wallet wallet = null;

        String query = "SELECT * FROM wallets WHERE wallet_id = " + id + ";";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            if(rs.next()) {
                wallet = createWalletFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return wallet;
        }
    }

    private Wallet createWalletFromResultSet(ResultSet rs) throws SQLException {

        Integer walletId = rs.getInt("wallet_id");
        Integer experience = rs.getInt("experience");
        Integer walletBalance = rs.getInt("wallet_balance");

        wallet = new Wallet(experience, walletBalance);
        wallet.setWalletId(walletId);

        return wallet;
    }

    public void saveWallet(Wallet wallet) {

        String query = "INSERT INTO wallets (experience, wallet_balance) VALUES(?,?)";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, wallet.getExperience());
            statement.setInt(2, wallet.getBalance());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editWallet(Wallet wallet) {

        String query = "UPDATE wallets SET wallet_balance = (?)  WHERE wallet_id = '" + wallet.getWalletId() + "'";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, wallet.getBalance());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static Integer getLastWalletCreatedId() {

        Integer loadedWalletId = null;
        String lastId = "SELECT * FROM wallets WHERE wallet_id = (SELECT MAX(wallet_id) FROM wallets)";

        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(lastId);

            if (rs.next()) {
                loadedWalletId = rs.getInt("wallet_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loadedWalletId;
    }
}
