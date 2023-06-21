package com.example.teamproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
    private var clist: ArrayList<CommnunityItem> = ArrayList()
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
        clist = CommnunityItem.createContactsList(clistSize) // 글의 갯수
        communityRecyclerView.setHasFixedSize(true)
        adapter = CommunityItemAdapter(requireActivity(), clist)
        communityRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        communityRecyclerView.adapter = adapter
        // Inflate the layout for this fragment
        return rootView
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