package com.mc7.myhiltinjectionapp

import androidx.lifecycle.LiveData
import com.mc7.myhiltinjectionapp.utils.Result

interface DetailRepository {
    fun getDetailUser(): LiveData<Result<DetailUserResponse>>
}