package com.guruthedev.instagram.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.guruthedev.instagram.repository.ViewModelFactory
import com.guruthedev.instagram.repository.ViewModelKey
import com.guruthedev.instagram.viewModel.HomeFragmentViewModel
import com.guruthedev.instagram.viewModel.LoginViewModel
import com.guruthedev.instagram.viewModel.ReelsFragmentViewModel
import com.guruthedev.instagram.viewModel.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun getViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeFragmentViewModel::class)
    abstract fun getHomeViewModel(homeFragmentViewModel: HomeFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun getLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun getSignUpViewModel(signUpViewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReelsFragmentViewModel::class)
    abstract fun getReelsViewModel(reelsFragmentViewModel: ReelsFragmentViewModel): ViewModel
}
