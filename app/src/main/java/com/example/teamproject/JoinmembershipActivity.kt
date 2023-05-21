package com.example.teamproject

import android.app.Activity
import android.os.Bundle
import com.example.teamproject.databinding.ActivityJoinmembershipBinding

class JoinmembershipActivity : Activity(){

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        var binding = ActivityJoinmembershipBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}