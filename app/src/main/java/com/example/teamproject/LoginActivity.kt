package com.example.teamproject

<<<<<<< HEAD
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
=======
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.teamproject.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser



class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding

    var auth : FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
        auth = FirebaseAuth.getInstance()
        binding.loginLoginBtn.setOnClickListener {
            signInEmail(binding.loginId.text.toString(),binding.loginPw.text.toString())
        }
        binding.loginJoinBtn.setOnClickListener {
            startActivity(Intent(applicationContext,JoinmembershipActivity::class.java))
        }
    }

    private fun signInEmail(email: String, password: String) {

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            baseContext, "로그인에 성공 하였습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                        moveMainPage(auth?.currentUser)
                    } else {
                        Toast.makeText(
                            baseContext, "로그인에 실패 하였습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
    // 로그인이 성공하면 다음 페이지로 넘어가는 함수
    fun moveMainPage(user:FirebaseUser?) {
        // 파이어베이스 유저 상태가 있을 경우 다음 페이지로 넘어갈 수 있음
        if(user != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()

        }
    }
    // 로그아웃하지 않을 시 자동 로그인 , 회원가입시 바로 로그인 됨
    public override fun onStart() {
        super.onStart()
        moveMainPage(auth?.currentUser)
    }

>>>>>>> c30ee33 (test)
}