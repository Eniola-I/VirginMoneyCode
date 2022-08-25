package com.eniola.virginmoney.api

import com.eniola.virginmoney.model.ColleaguesItem
import com.eniola.virginmoney.model.OfficeItem
import retrofit2.Response
import retrofit2.http.GET

interface OfficeApi {

    @GET(COLLEAGUES)
    suspend fun getColleaguesInfo():Response<List<ColleaguesItem>>

    @GET(OFFICES)
    suspend fun getOfficeInfo():Response<List<OfficeItem>>

    companion object{
        const val BASE_URL = "https://61e947967bc0550017bc61bf.mockapi.io/"
        private const val COLLEAGUES = "api/v1/people"
        private const val OFFICES = "api/v1/rooms"
    }
}