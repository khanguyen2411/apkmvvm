package com.example.mvvmexample.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmexample.data.db.entities.CURRENT_USER_ID
import com.example.mvvmexample.data.db.entities.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    @Query("SELECT * FROM user WHERE uid = $CURRENT_USER_ID")
    fun getUser() : LiveData<User>


}