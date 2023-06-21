package com.example.teamproject

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: ViewGroup = inflater.inflate(R.layout.fragment_community, container, false) as ViewGroup
        communityRecyclerView = rootView.findViewById(R.id.community_items)
        clist = CommunityItem.createContactsList(clistSize) // 글의 갯수
        communityRecyclerView.setHasFixedSize(true)
        initData()
        adapter = CommunityItemAdapter(requireActivity(), clist)
        //밑에 initData()에서 실행
        //communityRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        //communityRecyclerView.adapter = adapter
        // Inflate the layout for this fragment
        return rootView
    }
    @SuppressLint("NotifyDataSetChanged", "SimpleDateFormat")
    private fun initData() { //Firebase에 있는 데이터 불러오기
        val db = FirebaseFirestore.getInstance()
        db.collection("Community")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        val title: String = document["title"].toString()
                        val writer: String = document["writer"].toString()
                        val place: String = document["place"].toString()
                        val sdf = SimpleDateFormat("YYYY-MM-dd") //pattern 형태로 변환
                        val date: String = sdf.format(document.getDate("date"))
                        val e = CommunityItem(
                            title, writer, place, date
                        )
                        clist.add(e)
                    }
                    Log.d(TAG, "onComplete: 데이터 반영 완료$clist")
                    val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
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