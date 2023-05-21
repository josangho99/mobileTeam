package com.example.teamproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.teamproject.databinding.ActivityLoginBinding

class LoginActivity : Activity(){

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        var binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginJoinBtn.setOnClickListener {
            var intent_join = Intent(applicationContext, JoinmembershipActivity::class.java)
            startActivity(intent_join)
        }
    }
}