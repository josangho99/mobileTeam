package com.example.teamproject

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.teamproject.databinding.FragmentMypageBinding
import com.google.firebase.auth.FirebaseAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [mypage.newInstance] factory method to
 * create an instance of this fragment.
 */
class mypage : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var auth : FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMypageBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()

        if(auth?.currentUser == null) {
            binding.myPageLoginBtn.visibility = View.VISIBLE
            binding.myPageLogoutBtn.visibility = View.GONE
        }
        else if (auth?.currentUser != null) {
            binding.myPageLoginBtn.visibility = View.GONE
            binding.myPageLogoutBtn.visibility = View.VISIBLE
        }
        // 2. 바인딩으로 버튼 접근
        binding.myPageLoginBtn.setOnClickListener {
            if(auth?.currentUser == null) {
                val intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)
            }else
                Toast.makeText(context,"현재 로그인된 상태입니다",Toast.LENGTH_LONG).show()
        }
        binding.myPageLogoutBtn.setOnClickListener {
            if(auth?.currentUser == null) {
                Toast.makeText(context, "로그인 상태가 아닙니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else {
                //firebase auth에서 sign out 기능 호출
                Log.i(TAG,"파이어베이스 로그아웃 성공")
                auth!!.signOut()
                binding.myPageLoginBtn.visibility = View.VISIBLE
                binding.myPageLogoutBtn.visibility = View.GONE
                var intent = Intent(activity, LoginActivity::class.java) //네비 페이지 이동
                startActivity(intent)
            }
        }

        // 3. 프래그먼트 레이아웃 뷰 반환
        return binding.root
    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment mypage.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            mypage().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}