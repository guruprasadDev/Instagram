package com.guruthedev.instagram.ui.fragments.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel(){

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val _taskResponse = MutableLiveData<Task<AuthResult>>()
    val taskResponse: LiveData<Task<AuthResult>>
        get() = _taskResponse

    fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { taskResult ->
                _taskResponse.postValue(taskResult)
            }
    }

}