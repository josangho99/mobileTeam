package com.example.teamproject

<<<<<<< HEAD
=======
import android.annotation.SuppressLint
import android.content.Intent
>>>>>>> c30ee33 (test)
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
=======
import android.widget.Button
import android.widget.ImageButton
import com.example.teamproject.databinding.ActivityNaviBinding
import com.example.teamproject.databinding.FragmentMypageBinding
import com.google.firebase.auth.FirebaseAuth
>>>>>>> c30ee33 (test)

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
<<<<<<< HEAD

=======
    var auth : FirebaseAuth? = null
>>>>>>> c30ee33 (test)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
<<<<<<< HEAD
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mypage, container, false)
    }

=======

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMypageBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
        // 2. 바인딩으로 버튼 접근
        binding.myPageLoginBtn.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.logout.setOnClickListener {
            //firebase auth에서 sign out 기능 호출
            auth!!.signOut()
            var intent=Intent(activity,LoginActivity::class.java) //로그인 페이지 이동
            startActivity(intent)
        }

        // 3. 프래그먼트 레이아웃 뷰 반환
        return binding.root
    }



>>>>>>> c30ee33 (test)
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