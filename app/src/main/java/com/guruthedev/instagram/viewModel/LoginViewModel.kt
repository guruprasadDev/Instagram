package com.guruthedev.instagram.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val _taskResponseLiveData = MutableLiveData<Task<AuthResult>>()
    val taskResponseLiveData: LiveData<Task<AuthResult>>
        get() = _taskResponseLiveData
    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String>
        get() = _errorLiveData

    private fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { taskResult ->
                if(taskResult.isSuccessful){
                    _taskResponseLiveData.postValue(taskResult)
                }else{
                    _errorLiveData.postValue(taskResult.exception?.message)
                }
            }
    }

    fun validateCred(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            login(email, password)
        }else{
            _errorLiveData.postValue("Empty fields are not allowed")
        }
    }
}


