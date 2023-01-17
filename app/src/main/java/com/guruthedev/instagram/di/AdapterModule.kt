package com.guruthedev.instagram.di

import androidx.recyclerview.widget.RecyclerView
import com.guruthedev.instagram.ui.adapter.FeedPostAdapter
import dagger.Binds
import dagger.Module

@Module
abstract class AdapterModule {
    @Binds
    abstract fun bindMyAdapter(adapter: FeedPostAdapter): RecyclerView.Adapter<*>
}
