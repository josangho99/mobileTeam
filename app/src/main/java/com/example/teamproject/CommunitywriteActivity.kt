package com.example.teamproject
import android.app.Activity
import android.os.Bundle
import com.example.teamproject.databinding.ActivityCommunitywriteBinding

class CommunitywriteActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        var binding = ActivityCommunitywriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}