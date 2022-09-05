package com.fghilmany.uangku.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    /*//example query
    @Query("SELECT * FROM table_name")
    fun getEmail(): Flow<List<EmailEntity>>

    //example insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmail(emailEntity: List<EmailEntity>)*/
}