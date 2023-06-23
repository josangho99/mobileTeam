package com.example.teamproject

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.example.teamproject.databinding.ActivityJoinmembershipBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class JoinmembershipActivity : Activity(){

    var db : FirebaseFirestore? = FirebaseFirestore.getInstance()
    var auth : FirebaseAuth = FirebaseAuth.getInstance()
    var idCk : Any? = null
    var nkCk : Any? = null
    var par1 : Boolean = false // 아이디 중복인지 확인하고
    var par2 : Boolean = false // 닉네임도 중복확인 해야 회원가입 버튼 활성화

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        var binding = ActivityJoinmembershipBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //비밀번호 일치 확인
        binding.joinPwCheck.addTextChangedListener(object : TextWatcher {
            //입력이 끝났을 때
            //4. 비밀번호 일치하는지 확인
            override fun afterTextChanged(p0: Editable?) {
                if(binding.joinPw.getText().toString().equals(binding.joinPwCheck.getText().toString())){
                    binding.joinPwCheckResult.setText("비밀번호가 일치합니다.")
                    // 가입하기 버튼 활성화
                    binding.joinJoinbtn.isEnabled=true
                }
                else{
                    binding.joinPwCheckResult.setText("비밀번호가 일치하지 않습니다.")
                }
            }
            //입력하기 전
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            //텍스트 변화가 있을 시
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.joinPw.getText().toString().equals(binding.joinPwCheck.getText().toString())){
                    binding.joinPwCheckResult.setText("비밀번호가 일치합니다.")
                    // 가입하기 버튼 활성화
                    binding.joinJoinbtn.isEnabled=true
                }
                else{
                    binding.joinPwCheckResult.setText("비밀번호가 일치하지 않습니다.")
                }
            }
        })


        //회원가입 버튼
        binding.joinJoinbtn.setOnClickListener {
           if(!checkEmail()){ //틀린 경우
                Toast.makeText(applicationContext,"이메일 형식에 맞게 입력하세요!",Toast.LENGTH_LONG).show()
           } else if (par1 && par2) {
                if (binding.joinPw.getText().toString().equals(binding.joinPwCheck.getText().toString())){
                    createAccount(
                        binding.joinId.text.toString(),
                        binding.joinPw.text.toString(),
                        binding.joinNickname.text.toString()
                    )
                }else
                    Toast.makeText(this, "비밀번호를 확인하시길 바랍니다", Toast.LENGTH_LONG).show()
            }
            else
                Toast.makeText(this,"닉네임과 아이디 중복확인 바랍니다",Toast.LENGTH_LONG).show()
        }

        //아이디 중복 확인 버튼 + 이메일 형태 확인
        binding.joinIdCheckBtn.setOnClickListener {
            if(binding.joinId.text.isNotEmpty()) {
                if(checkEmail()) //이메일 형태 확인 구간
                    idCheck(binding.joinId.text.toString())
                else
                    Toast.makeText(this,"이메일 형태를 확인하세요",Toast.LENGTH_LONG).show()
            }
            else
                Toast.makeText(this,"아이디를 입력하세요",Toast.LENGTH_LONG).show()
        }

        //닉네임 중복 확인 버튼
        binding.joinNicknameCheck.setOnClickListener {
            if(binding.joinNickname.text.isNotEmpty()) {
                nicknameCheck(binding.joinNickname.text.toString())
            }
            else
                Toast.makeText(this,"닉네임을 입력하세요",Toast.LENGTH_LONG).show()
        }

    }


    private fun createAccount(email: String, password: String, nickname: String) {

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.createUserWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this, "계정 생성 완료.",
                            Toast.LENGTH_LONG
                        ).show()
                        userData(email,nickname) // 가입내용 데이터베이스에 저장
                        finish() // 가입창 종료
                    } else {
                        Toast.makeText(
                            this, "계정 생성 실패",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
        else{
            Toast.makeText(this,"이메일과 비밀번호를 입력하십시오.",Toast.LENGTH_LONG).show()
        }
    }
    fun userData(email: String, nickname: String) {
        var user = hashMapOf<String,String?>()
        user.put("email", email)
        user.put("uid", auth?.currentUser?.uid)
        user.put("nickname", nickname)
        db?.collection("User")
            ?.add(user)
            ?.addOnSuccessListener{
                Log.i(ContentValues.TAG, "유저 데이터 저장 성공")
            }
    }
    fun idCheck(id : String) {
        db?.collection("User")
            ?.whereEqualTo("email", id)
            ?.get()
            ?.addOnCompleteListener { document ->
                if(document.isSuccessful)
                    for(task in document.result)
                        if(task["email"].toString() == id)
                            idCk = task
                if (idCk == null){
                    Toast.makeText(this, "사용가능한 아이디 입니다", Toast.LENGTH_LONG).show()
                    par1 = true
                    idCk = null
                } else {
                    Toast.makeText(this, "이미 있는 아이디 입니다", Toast.LENGTH_LONG).show()
                    par1 = false
                    idCk = null
                }
            }

        }

    fun nicknameCheck(nickname : String) {
        db?.collection("User")
            ?.whereEqualTo("nickname", nickname)
            ?.get()
            ?.addOnCompleteListener { document ->
                if(document.isSuccessful)
                    for(task in document.result)
                        if(task["nickname"].toString() == nickname)
                            nkCk = task
                if(nkCk == null){
                    Toast.makeText(this, "사용가능한 닉네임 입니다", Toast.LENGTH_LONG).show()
                    par2 = true
                    nkCk = null
                }else {
                    Toast.makeText(this, "이미 있는 닉네임 입니다", Toast.LENGTH_LONG).show()
                    par2 = false
                    nkCk = null
                }
            }

    }
    @SuppressLint("ResourceAsColor")
    fun checkEmail():Boolean{
        var email : EditText = findViewById(R.id.join_id)
        val pattern = Patterns.EMAIL_ADDRESS
        // 검사 정규식
        var comp = email.text.toString().trim() //공백제거
        return pattern.matcher(comp).matches()
    }
}
