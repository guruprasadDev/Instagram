package com.guruthedev.instagram.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class SignUpViewModel() : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val _taskResponse = MutableLiveData<Task<AuthResult>>()
    val taskResponseLiveData: LiveData<Task<AuthResult>>
        get() = _taskResponse
    private val _errorMutableLiveData = MutableLiveData<Task<AuthResult>>()
    val errorLiveData: LiveData<Task<AuthResult>>
        get() = _errorMutableLiveData

    private fun signUp(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { taskResult ->
                _taskResponse.postValue(taskResult)
                _errorMutableLiveData.postValue(taskResult)
            }
    }

    fun validateCred(fullName: String, username: String, email: String, password: String) {
        if (fullName.isNotEmpty() && username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
            signUp(email, password)
        }
    }
}