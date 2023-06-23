package com.example.teamproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.teamproject.databinding.ActivityCommunityitemBinding

class CommunityitemActivity :Activity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        var binding = ActivityCommunityitemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        val writer = intent.getStringExtra("writer")
        val date = intent.getStringExtra("date")
        val place = intent.getStringExtra("place")
        val sport = intent.getStringExtra("sport")

        binding.itemTitle.text = title
        binding.itemContent.text = content
        binding.itemWriter.text = writer
        binding.itemDate.text = date
        binding.itemPlace.text = place
        binding.itemSport.text = sport
    }
}