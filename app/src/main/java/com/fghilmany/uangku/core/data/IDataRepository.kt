package com.fghilmany.uangku.core.data

import com.fghilmany.uangku.core.data.local.entity.EmailEntity
import com.fghilmany.uangku.core.data.remote.response.EmailResponse
import kotlinx.coroutines.flow.Flow

interface IDataRepository {

    //get online
    fun getEmailOnline(email: String): Flow<Resource<EmailResponse>>

    //get online offline
    fun getEmailOnlineOffline(email: String): Flow<Resource<List<EmailEntity>>>

    //get offline
    fun getEmailOffline(): Flow<List<EmailEntity>>



}