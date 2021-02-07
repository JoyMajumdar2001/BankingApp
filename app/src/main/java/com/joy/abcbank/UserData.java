package com.joy.abcbank;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "user_table")
public class UserData implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "name")
    private String userName;

    @ColumnInfo(name = "email")
    private String userEmail;

    @ColumnInfo(name = "mob")
    private String userMobNo;

    @ColumnInfo(name = "account_no")
    private String userAccountNo;

    @ColumnInfo(name = "balance")
    private Long userCurrentBalance;

    public UserData(){
    }

    public UserData(String name, String email, String mob,String accno, Long bal){
        this.userName = name;
        this.userEmail = email;
        this.userMobNo = mob;
        this.userAccountNo = accno;
        this.userCurrentBalance = bal;

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserMobNo() {
        return userMobNo;
    }

    public void setUserMobNo(String userMobNo) {
        this.userMobNo = userMobNo;
    }

    public String getUserAccountNo() {
        return userAccountNo;
    }

    public void setUserAccountNo(String userAccountNo) {
        this.userAccountNo = userAccountNo;
    }

    public Long getUserCurrentBalance() {
        return userCurrentBalance;
    }

    public void setUserCurrentBalance(Long userCurrentBalance) {
        this.userCurrentBalance = userCurrentBalance;
    }
}
