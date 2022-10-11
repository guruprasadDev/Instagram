package com.guruthedev.instagram.ui.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guruthedev.instagram.dataClass.InstaStatus
import com.guruthedev.instagram.databinding.StoryItemsBinding
import com.guruthedev.instagram.utils.loadImageUrlForStory

class StoriesAdapter(
    private val statusList: ArrayList<InstaStatus>
) : RecyclerView.Adapter<StoriesAdapter.StoryViewHolder>() {
    var context: Context? = null
    override fun onCreateViewHolder(story: ViewGroup, feedStory: Int): StoryViewHolder {
        val binding =
            StoryItemsBinding.inflate(LayoutInflater.from(context), story, false)
        return StoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return statusList.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    override fun onBindViewHolder(story: StoryViewHolder, feedStory: Int) {
        val stories = statusList[feedStory]
        val binding = story.statusListBinding
        //if foodStory equals to 0 this will visible to user story
        if (feedStory == 0) {
            binding.icAddImg.visibility = View.VISIBLE
        } else {
            binding.icAddImg.visibility = View.INVISIBLE
        }
        binding.profileName.text = statusList[feedStory].name
        binding.profileName.visibility = View.VISIBLE
        context?.let {
            loadImageUrlForStory(
                imageURL = stories.picture,
                context = it,
                imageView = binding.profileImg
            )
        }
    }

    class StoryViewHolder(binding: StoryItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        val statusListBinding = binding

    }
}
