package com.guruthedev.instagram.di

import com.guruthedev.instagram.ui.fragments.HomeFragment
import com.guruthedev.instagram.ui.fragments.ReelsFragment
import com.guruthedev.instagram.ui.fragments.auth.login.LoginFragment
import com.guruthedev.instagram.ui.fragments.auth.signUp.SignUpFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment():HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment():LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeSignUpFragment():SignUpFragment

    @ContributesAndroidInjector
    abstract fun contributeReelsFragment():ReelsFragment

}