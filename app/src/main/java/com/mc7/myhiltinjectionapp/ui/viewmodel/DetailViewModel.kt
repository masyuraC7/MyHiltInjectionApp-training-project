package com.mc7.myhiltinjectionapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mc7.myhiltinjectionapp.repository.DetailUserRepositoryImp
import com.mc7.myhiltinjectionapp.DetailUserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailUserRepositoryImp: DetailUserRepositoryImp
): ViewModel() {

    private val detailUser: DetailUserResponse? = null

    private val _isUsername = MutableLiveData<String>()
    val isUsername: LiveData<String> = _isUsername

    init {
        if (isFilledUser()) _isUsername.value = detailUser?.login.toString()
    }

    fun getDetailUser() = detailUserRepositoryImp.getDetailUser()

    fun saveUsername(userName: String){
        _isUsername.value = userName
    }

    fun isFilledUser(): Boolean{
        return detailUser != null
    }
}