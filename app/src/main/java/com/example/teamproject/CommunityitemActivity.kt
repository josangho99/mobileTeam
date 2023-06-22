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

        var title = intent.getStringExtra("title")
        var content = intent.getStringExtra("content")

        binding.itemTitle.text = title
        binding.itemContent.text = content
    }
}