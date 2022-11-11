package com.guruthedev.instagram.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guruthedev.instagram.data.Post
import com.guruthedev.instagram.network.RetroInstance
import com.guruthedev.instagram.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragmentViewModel:ViewModel() {
  var recyclerListData : MutableLiveData<Post> = MutableLiveData()

    fun getRecycleListObserver():MutableLiveData<Post>{
        return recyclerListData
    }

    fun makeApiCall(){
        viewModelScope.launch(Dispatchers.IO){
            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
            val response = retroInstance.getDataFromApi("ny")
            recyclerListData.postValue(response)
        }
    }
}