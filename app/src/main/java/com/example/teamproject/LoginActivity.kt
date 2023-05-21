package com.example.teamproject

import android.app.Activity
import android.os.Bundle
import com.example.teamproject.databinding.ActivityLoginBinding

class LoginActivity : Activity(){

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        var binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}