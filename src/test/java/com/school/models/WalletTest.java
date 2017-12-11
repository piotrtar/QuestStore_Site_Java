//package com.school.models;
//
//import java.util.ArrayList;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//class WalletTest {
//
//    private Wallet wallet;
//    private ArrayList<Artifact> artifacts_controllers;
//
//    @BeforeEach
//    public void before() {
//        this.artifacts_controllers = new ArrayList<Artifact>();
//        this.artifacts_controllers.add(new Artifact("artifact1"));
//        this.artifacts_controllers.add(new Artifact("artifact2"));
//        this.wallet = new Wallet(1, 100, 100, this.artifacts_controllers);
//    }
//
//    @Test
//    public void testWalletHasEmptyConstructor() {
//        assertNotNull(new Wallet());
//    }
//
//    // temp test dependent on walletDAO
//    @Test
//    public void testWalletHasConstructorWithExperienceAndBalance() {
//        assertNotNull(new Wallet(100, 100));
//    }
//
//    @Test
//    public void testWalletHasConstructorWithAllAttribiutes() {
//        assertNotNull(new Wallet(1, 100, 100, artifacts_controllers));
//    }
//
//    @Test
//    public void testGetWalletIdReturnsExpectedValue() {
//        int actual = this.wallet.getWalletId();
//        assertEquals(1, actual);
//    }
//
//    @Test
//    public void testSetWalletIdChangesValueToExpected() {
//        this.wallet.setWalletId(2);
//        int actual = this.wallet.getWalletId();
//        assertEquals(2, actual);
//    }
//
//    @Test
//    public void testGetExperienceReturnsExpectedValue() {
//        int actual = this.wallet.getExperience();
//        assertEquals(100, actual);
//    }
//
//    @Test
//    public void testSetExperienceChangesValueToExpected() {
//        this.wallet.setExperience(200);
//        int actual = this.wallet.getExperience();
//        assertEquals(200, actual);
//    }
//
//    @Test
//    public void testSetExperienceThrowsExcpetionIfValueBelow0() {
//        assertThrows(IllegalArgumentException.class, () -> {
//            this.wallet.setExperience(-100);
//        });
//    }
//
//    @Test
//    public void testGetBalanceReturnsExpectedValue() {
//        int actual = this.wallet.getBalance();
//        assertEquals(100, actual);
//    }
//
//    @Test
//    public void testSetBalanceChangesValueToExpected() {
//        this.wallet.setBalance(200);
//        int actual = this.wallet.getBalance();
//        assertEquals(200, actual);
//    }
//
//    @Test
//    public void testSetBalanceThrowsExcpetionIfValueBelow0() {
//        assertThrows(IllegalArgumentException.class, () -> {
//            this.wallet.setBalance(-100);
//        });
//    }
//
//    @Test
//    public void testGetArtifactsReturnsExpectedValue() {
//        ArrayList<Artifact> actual = this.wallet.getArtifacts();
//        assertIterableEquals(this.artifacts_controllers, actual);
//    }
//
//    @Test
//    public void testAddArtifactIsAddByReference() {
//        Artifact artifact = new Artifact("artifact3");
//        this.wallet.addArtifact(artifact);
//        assertTrue(this.wallet.getArtifacts().contains(artifact));
//    }
//
//    @Test
//    public void testAddCoolcoinsChangesExperience() {
//        this.wallet.addCoolcoins(100);
//        int actual = this.wallet.getExperience();
//        assertEquals(200, actual);
//    }
//
//    @Test
//    public void testAddCoolcoinsChangesBalance() {
//        this.wallet.addCoolcoins(100);
//        int actual = this.wallet.getBalance();
//        assertEquals(200, actual);
//    }
//
//    @Test
//    public void testCalculateLevelReturnsExpectedValue() {
//        this.wallet.setExperience(2795);
//        int actual = this.wallet.calculateLevel();
//        assertEquals(2, actual);
//    }
//
//    @Test
//    public void testCalculateLevelNeverReturns0() {
//        int actual = this.wallet.calculateLevel();
//        assertEquals(1, actual);
//    }
//
//    @Test
//    public void testViewWalletReturnsExpectedString() {
//        String expected = "You have 100 coolcoins available.\nYour bought artifacts_controllers:";
//        expected += "\n" + "artifact1";
//        expected += "\n" + "artifact2";
//        String actual = this.wallet.viewWallet();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testViewWalletReturnsExpectedStringIfThereAreNoArtifacts() {
//        String expected = "You have 100 coolcoins available.\nYour bought artifacts_controllers:";
//        String actual = new Wallet(100, 100).viewWallet();
//        assertEquals(expected, actual);
//    }
//
//}