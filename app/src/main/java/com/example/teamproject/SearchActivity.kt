package com.example.teamproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.teamproject.databinding.ActivitySearchBinding


class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoryList: MutableList<String> = mutableListOf()
        var categoryList_size = categoryList.size // 센터 수
        for (i in 1..categoryList_size) {
            categoryList.add("축구")
            categoryList.add("농구")
            categoryList.add("야구")
            categoryList.add("배구")
            categoryList.add("당구")
            categoryList.add("헬스")
            categoryList.add("배드민턴")
            categoryList.add("복싱")
            categoryList.add("주짓수")
            categoryList.add("수영")
            categoryList.add("요가")
            categoryList.add("탁구")
        }
        binding.searchItems.adapter = SearchitemAdapter(categoryList)
        binding.searchItems.layoutManager = GridLayoutManager(this, 5)
        binding.searchItems.clipToPadding = false
        binding.searchItems.clipChildren = false
    }
}
