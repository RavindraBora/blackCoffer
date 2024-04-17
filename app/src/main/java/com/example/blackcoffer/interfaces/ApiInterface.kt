package com.example.blackcoffer.interfaces

import com.example.blackcoffer.model.modelPersonal.ResponsePersonal
import com.example.blackcoffer.modelBusiness.ResponseBusiness
import com.example.blackcoffer.modelRefine.RequestRefine
import com.example.blackcoffer.modelRefine.ResponseRefine
import com.example.blackcoffer.modelServices.ResponseServices
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("getAllBlackCofferUser")
    fun getBlackCofferPersonalUser(): retrofit2.Call<ResponsePersonal>

    @POST("getAllBlackCofferServicesUser")
    fun getBlackCofferServicesUser(): retrofit2.Call<ResponseServices>

    @POST("getAllBlackCofferBusinessUser")
    fun getBlackCofferBusinessUser(): retrofit2.Call<ResponseBusiness>

    @POST("savedRefineData")
    fun saveRefineData(@Body requestRefine: RequestRefine): retrofit2.Call<ResponseRefine>
}