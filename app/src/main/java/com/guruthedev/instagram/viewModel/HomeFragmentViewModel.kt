package com.guruthedev.instagram.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guruthedev.instagram.data.PostResponse
import com.guruthedev.instagram.data.network.RetroInstance
import com.guruthedev.instagram.data.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragmentViewModel @Inject constructor() : ViewModel() {
    var recyclerListData: MutableLiveData<PostResponse> = MutableLiveData()

    fun getPostResponse(): MutableLiveData<PostResponse> {
        return recyclerListData
    }

    fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
            val response = retroInstance.getFeedPosts("ny")
            recyclerListData.postValue(response)
        }
    }
}