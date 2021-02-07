package com.joy.abcbank;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDao {

    @Insert(onConflict = REPLACE)
    void insert(UserData userData);

    @Query("UPDATE user_table SET balance = :sBal WHERE ID = :sID")
    void updateBalance(int sID,Long sBal);

    @Query("SELECT * FROM user_table")
    List<UserData> getAllUser();

    @Query("SELECT * FROM user_table WHERE ID != :iID")
    List<UserData> getAllUserExceptMe(int iID);

    @Query("SELECT * FROM user_table WHERE ID = :mID")
    UserData getUserInfo(int mID);

    @Query("SELECT COUNT(balance) FROM user_table")
    int getRowCount();
}
