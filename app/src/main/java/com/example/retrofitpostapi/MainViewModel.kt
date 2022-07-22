package com.example.retrofitpostapi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    lateinit var createNewUserLiveData : MutableLiveData<UserResponse?>

    init {
        createNewUserLiveData = MutableLiveData()
    }

    fun getCreateNewUserObserver() : MutableLiveData<UserResponse?>{
        return createNewUserLiveData
    }

    fun createNewUser(user: User){
        val retroService = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroService.createUser(user)
        call.enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful){
                    createNewUserLiveData.postValue(response.body())
                }else{
                    createNewUserLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
               createNewUserLiveData.postValue(null)
            }
        })
    }



}