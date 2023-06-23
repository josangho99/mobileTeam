package com.example.teamproject
import android.app.Activity
import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.teamproject.databinding.ActivityCommunitywriteBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.sql.Timestamp
import java.util.Calendar

/*
* 글 내용 말고도 위치와 운동날짜 넣을 수 있게 추가바람
* */
class CommunitywriteActivity : Activity() {
    var auth : FirebaseAuth = FirebaseAuth.getInstance()
    var db : FirebaseFirestore = FirebaseFirestore.getInstance()
    lateinit var dateText : TextView
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        var binding = ActivityCommunitywriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dateText = findViewById(R.id.date_text)
        lateinit var sport : String

        binding.writeWriteBtn.setOnClickListener {
            Log.d(TAG,"${auth.uid}")//현재 로그인중인 사용자의 uid
            if(dateText.text.isNotEmpty()) { //날짜가 입력되어있는지 확인
                post(
                    binding.writeTitle.text.toString(),
                    binding.writeContent.text.toString(),
                    dateText.text.toString(),
                    binding.writePlace.text.toString(),
                    sport //TODO 아무것도 안고르고 글 작성시 데이터베이스에 어떻데 들어가는지 확인
                )
                Toast.makeText(this, "글이 등록되었습니다", Toast.LENGTH_LONG).show()
                finish()
            }else{ //날짜 미입력시 알림
                Toast.makeText(this, "날짜를 선택해주세요", Toast.LENGTH_LONG).show()
            }

        }
        //날짜 선택
        binding.writeDate.setOnClickListener {
            showDatePicker()
        }

        //var sData = resources.getStringArray(R.array.sports)
        //val adapter = ArrayAdapter(this, R.layout.simple_list_item_1, sData)
        binding.sportSpinner.adapter = ArrayAdapter.createFromResource(
            this,R.array.sports,R.layout.simple_list_item_1)

        binding.sportSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position != 0) {
                    sport = binding.sportSpinner.selectedItem.toString()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

    }
    fun post(title : String, content : String, date : String, place : String, sport : String){ //글 작성함수
        if(title.isNotEmpty()&&content.isNotEmpty()&&date.isNotEmpty()&&place.isNotEmpty()&&sport.isNotEmpty()) {
            val title = title
            val content = content
            val date = date
            val place = place
            var writer: String
            val sport = sport

            //현재 로그인중인 사용자의 Nickname 가져오기
            db.collection("User")
                .whereEqualTo("uid", auth.currentUser?.uid)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        var post = hashMapOf<String, Any>()
                        for (document in task.result) {
                            Log.d(TAG, "현재 사용자 불러오기 완료")
                            writer = document["nickname"].toString()
                            post["writer"] = writer
                        }
                        post["title"] = title
                        post["content"] = content
                        post["date"] = date
                        post["place"] = place
                        post["sport"] = sport
                        //입력내용기반으로 데이터베이스에 등록
                        db?.collection("Community")
                            ?.add(post)
                            ?.addOnCompleteListener {
                                Log.i(TAG, "글 작성 성공")
                            }?.addOnFailureListener {
                                Log.i(TAG, "글 작성 에러")
                                return@addOnFailureListener
                            }
                    }
                }
        }else{
            Toast.makeText(this,"내용을 다 입력해주세요",Toast.LENGTH_LONG).show()
        }
    }
    fun showDatePicker(){ //달력 불러와서 날짜 고르고 가져옴
        val calendar : Calendar = Calendar.getInstance()
        val iYear = calendar.get(Calendar.YEAR)
        val iMonth = calendar.get(Calendar.MONTH)
        val iDay = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(this,
            { datepicker, year, month, day ->
                val tMonth : Int = month+1
                val date = "$year / $tMonth / $day"
                dateText.text = date
            }, iYear, iMonth, iDay)
        datePicker.show()
    }
}