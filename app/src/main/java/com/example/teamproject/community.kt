package com.example.teamproject

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.teamproject.databinding.FragmentCommunityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [community.newInstance] factory method to
 * create an instance of this fragment.
 */
class community : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var communityRecyclerView: RecyclerView
    private lateinit var adapter: CommunityItemAdapter
    private var clist: ArrayList<CommunityItem> = ArrayList()
    private var clistSize = clist.size // 글의 갯수
    lateinit var binding : FragmentCommunityBinding
    private lateinit var refreshLayout01 : SwipeRefreshLayout
    private lateinit var write_btn : ImageButton
    private var auth : FirebaseAuth = FirebaseAuth.getInstance()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
        adapter = CommunityItemAdapter(requireActivity(), clist)
        adapter.setOnItemClickListener(object : CommunityItemAdapter.OnItemClickListener {
            override fun onItemClick(v: View?, position: Int) {
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        refreshData()
    }

        @SuppressLint("NotifyDataSetChanged")
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            var rootView: ViewGroup = inflater.inflate(R.layout.fragment_community, container, false) as ViewGroup
            communityRecyclerView = rootView.findViewById(R.id.community_items)
            clist = CommunityItem.createContactsList(clistSize) // 글의 갯수
            communityRecyclerView.setHasFixedSize(true)

            val layoutManager = LinearLayoutManager(context)
            layoutManager.reverseLayout = true
            layoutManager.stackFromEnd = true
            adapter.addList(clist)
            communityRecyclerView.layoutManager = layoutManager
            adapter.notifyDataSetChanged()
            communityRecyclerView.adapter = adapter
            initData()

            //글쓰기 버튼
            write_btn = rootView.findViewById(R.id.community_writeBtn)
            write_btn.setOnClickListener {
                if (auth?.currentUser != null) {
                    var intent = Intent(this.context, CommunitywriteActivity::class.java)
                    startActivity(intent)
                } else
                    Toast.makeText(this.context, "로그인 상태가 아닙니다", Toast.LENGTH_LONG).show()
            }

            return rootView
        }

        @SuppressLint("NotifyDataSetChanged")
        private fun initData() { //Firebase에 있는 데이터 불러오기
            val db = FirebaseFirestore.getInstance()
            db.collection("Community").orderBy("timestamp")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result) {
                            val title: String = document["title"].toString()
                            val writer: String = document["writer"].toString()
                            val place: String = document["place"].toString()
                            val content: String = document["content"].toString()
                            val date: String = document["date"].toString()
                            val sport: String = document["sport"].toString()
                            val e = CommunityItem(
                                title, writer, place, date, content, sport
                            )
                            clist.add(e)
                        }
                        Log.d(TAG, "onComplete: 데이터 반영 완료$clist")
                        val layoutManager = LinearLayoutManager(context)
                        layoutManager.reverseLayout = true //역순 정렬
                        layoutManager.stackFromEnd = true
                        adapter.addList(clist)
                        communityRecyclerView.layoutManager = layoutManager
                        adapter.notifyDataSetChanged()
                        communityRecyclerView.adapter = adapter
                    } else {
                        Log.d(TAG, "onComplete: 실패")
                    }
                }.addOnFailureListener { e -> Log.d(TAG, "onFailure: " + e.message) }
        }

    private fun refreshData(){      //화면 전환시마다 데이터 업데이트
        val db = FirebaseFirestore.getInstance()
        db.collection("Community").orderBy("timestamp")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    clist.clear()
                    for (document in task.result) {
                        val title: String = document["title"].toString()
                        val writer: String = document["writer"].toString()
                        val place: String = document["place"].toString()
                        val content: String = document["content"].toString()
                        val date: String = document["date"].toString()
                        val sport: String = document["sport"].toString()
                        val e = CommunityItem(
                            title, writer, place, date, content, sport
                        )
                        clist.add(e)
                    }
                    Log.d(TAG, "onComplete: 데이터 반영 완료$clist")
                    val layoutManager = LinearLayoutManager(context)
                    layoutManager.reverseLayout = true
                    layoutManager.stackFromEnd = true
                    adapter.addList(clist)
                    communityRecyclerView.layoutManager = layoutManager
                    adapter.notifyDataSetChanged()
                    communityRecyclerView.adapter = adapter
                } else {
                    Log.d(TAG, "onComplete: 실패")
                }
            }.addOnFailureListener { e -> Log.d(TAG, "onFailure: " + e.message) }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment community.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            community().apply {
                arguments = Bundle().apply {
                }
            }

    }
}