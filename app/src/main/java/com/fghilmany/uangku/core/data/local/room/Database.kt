package com.fghilmany.uangku.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fghilmany.uangku.core.data.local.entity.ExpenseEntity
import com.fghilmany.uangku.core.utils.DATABASE_VERSION

@Database(
    entities = [/*EmailEntity::class*/ ExpenseEntity::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class Database : RoomDatabase(){
    abstract fun dao(): Dao
}