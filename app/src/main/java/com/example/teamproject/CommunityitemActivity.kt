package com.example.teamproject

import android.app.Activity
import android.os.Bundle
import com.example.teamproject.databinding.ActivityCommunityitemBinding

class CommunityitemActivity :Activity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        var binding = ActivityCommunityitemBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}