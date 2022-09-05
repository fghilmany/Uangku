package com.fghilmany.uangku.core.data.local

import com.fghilmany.uangku.core.data.local.room.Dao
import kotlinx.coroutines.flow.Flow

class LocalDatasource (
    private val dao: Dao
) {
    /*fun getEmail(): Flow<List<EmailEntity>> = dao.getEmail()
    suspend fun insertEmail(emailEntity: List<EmailEntity>) {
        return dao.insertEmail(emailEntity)
    }*/
}