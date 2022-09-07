package com.fghilmany.uangku.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "regular_transaction_entity")
data class RegularTransactionEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var name: String? = null,

    var date: String? = null,

    var type: String? = null,

    var amount: String? = null,

    @ColumnInfo(name = "is_income")
    var isIncome: Boolean? = false,
)