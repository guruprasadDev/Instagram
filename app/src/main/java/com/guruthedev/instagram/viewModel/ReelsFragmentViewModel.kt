package com.guruthedev.instagram.viewModel

import androidx.lifecycle.ViewModel
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.guruthedev.instagram.data.Reels
import javax.inject.Inject

class ReelsFragmentViewModel @Inject constructor(): ViewModel() {
    fun getOptionsFromDatabase(): FirebaseRecyclerOptions<Reels?> {
        val dataBase = Firebase.database.getReference("videos")
        return FirebaseRecyclerOptions.Builder<Reels>()
            .setQuery(dataBase, Reels::class.java)
            .build()
    }
}
