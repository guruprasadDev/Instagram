package com.guruthedev.instagram.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.guruthedev.instagram.IgApplication
import com.guruthedev.instagram.data.LoginError
import com.guruthedev.instagram.data.pref.SessionPrefHelper
import com.guruthedev.instagram.utils.LoginErrorType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val igPreference = IgApplication.instances.getPreference()
    private val _taskResponseLiveData = MutableLiveData<Task<AuthResult>>()
    val taskResponseLiveData: LiveData<Task<AuthResult>>
        get() = _taskResponseLiveData
    private val _errorLiveData = MutableLiveData<LoginError>()
    val errorLiveData: LiveData<LoginError>
        get() = _errorLiveData

    private fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { taskResult ->
                    if (taskResult.isSuccessful) {
                        SessionPrefHelper.saveEmailPostLogin(igPreference, email)
                        _taskResponseLiveData.postValue(taskResult)
                    } else {
                        _errorLiveData.postValue(
                            LoginError(
                                LoginErrorType.ERROR_API,
                                taskResult.exception?.message
                            )
                        )
                    }
                }
        }
    }

    fun validateCred(email: String, password: String) {
        if (email.isEmpty()) {
            _errorLiveData.value = LoginError(LoginErrorType.ERROR_EMPTY_EMAIL)
        } else if (password.isEmpty()) {
            _errorLiveData.value = LoginError(LoginErrorType.ERROR_EMPTY_PASSWORD)
        } else {
            login(email, password)
        }
    }
}