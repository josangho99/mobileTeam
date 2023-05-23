package com.example.teamproject

import android.app.Activity
import android.os.Bundle
<<<<<<< HEAD
import com.example.teamproject.databinding.ActivityJoinmembershipBinding

class JoinmembershipActivity : Activity(){

=======
import android.widget.Toast
import com.example.teamproject.databinding.ActivityJoinmembershipBinding
import com.google.firebase.auth.FirebaseAuth



private var auth : FirebaseAuth? = null

class JoinmembershipActivity : Activity(){


>>>>>>> c30ee33 (test)
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        var binding = ActivityJoinmembershipBinding.inflate(layoutInflater)
        setContentView(binding.root)
<<<<<<< HEAD
    }
=======

        auth = FirebaseAuth.getInstance()


        binding.joinJoinbtn.setOnClickListener {
            createAccount(binding.joinId.text.toString(),binding.joinPw.text.toString())
        }

    }


    private fun createAccount(email: String, password: String) {

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.createUserWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this, "계정 생성 완료.",
                            Toast.LENGTH_LONG
                        ).show()
                        finish() // 가입창 종료
                    } else {
                        Toast.makeText(
                            this, "계정 생성 실패",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }

>>>>>>> c30ee33 (test)
}