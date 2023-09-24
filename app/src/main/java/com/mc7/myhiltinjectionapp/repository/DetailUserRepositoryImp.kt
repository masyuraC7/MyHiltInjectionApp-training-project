package com.mc7.myhiltinjectionapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.mc7.myhiltinjectionapp.DetailRepository
import com.mc7.myhiltinjectionapp.DetailUserResponse
import com.mc7.myhiltinjectionapp.utils.Result
import com.mc7.myhiltinjectionapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DetailUserRepositoryImp @Inject constructor(
    private val apiService: ApiService
): DetailRepository {

    private var result = MediatorLiveData<Result<DetailUserResponse>>()
    private var detailUser = DetailUserResponse()

    override fun getDetailUser(): LiveData<Result<DetailUserResponse>> {
        result.value = Result.Loading

        val client = apiService.getDetailUser()

        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {

                if (response.isSuccessful) {
                    val item = response.body()

                    if (item != null) {
                        detailUser = item
                    }

                    result.value = Result.Success(detailUser)
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                result.value = Result.Error(t.message.toString())
            }
        })

        return result
    }
}