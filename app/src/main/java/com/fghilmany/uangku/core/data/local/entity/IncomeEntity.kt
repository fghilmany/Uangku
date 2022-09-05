package com.fghilmany.uangku.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "income_table")
data class IncomeEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var date: String? = null,

    var type: String? = null,

    var amount: String? = null,
)