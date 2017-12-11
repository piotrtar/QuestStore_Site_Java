package com.school.models;

import com.school.controllers.WalletInterface;
import java.util.ArrayList;


public class Wallet implements WalletInterface{

  ArrayList artifacts = new ArrayList();
  Integer experience = 0;
  Integer level = 0;
  Integer balance = 0;
  Integer walletId;

  public Wallet(){}

  public Wallet(Integer experience, Integer balance) {
    this.experience = experience;
    this.balance = balance;
  }


  public ArrayList getArtifacts(){
    return this.artifacts;
  }
  public Integer getWalletId() {return this.walletId; }
  public Integer getExperience(){
    return this.experience;
  }
  public Integer getBalance(){
    return this.balance;
  }
  public Integer getLevel() {
    return this.level;
  }

  public void setWalletId(Integer id) { this.walletId = id; }
  public void setExperience(Integer exp){
    this.experience = exp;
  }
  public void setBalance(Integer balance){
    this.balance = balance;
  }
  public void setLevel(Integer level) {
    this.level = level;
  }

  public String vievWallet(){
    String walletInfo;

    walletInfo = String.format("You have %s coolcoins available.\nYour bought artifacts_controllers: %s", balance, artifacts);

    return walletInfo;
  }

  private void calculateLevel() {

    System.out.println("To be implemented");
  }

}

