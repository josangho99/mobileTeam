package com.example.teamproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.teamproject.databinding.ActivityNaviBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


private const val TAG_COMMUNITY = "community_fragment"
private const val TAG_LIST = "centerlist_fragment"
private const val TAG_MYPAGE = "mypage_fragment"
private const val TAG_MAP = "map_fragment"

class NaviActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityNaviBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragment(TAG_MAP, map())

        binding.navigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.communityFragment -> setFragment(TAG_COMMUNITY, community())
                R.id.centerlistFragment -> setFragment(TAG_LIST, centerlist())
                R.id.myPageFragment-> setFragment(TAG_MYPAGE, mypage())
<<<<<<< HEAD
                R.id.mapFragment -> setFragment(TAG_MAP,map())
=======
                R.id.mapFragment -> setFragment(TAG_MAP, map())
>>>>>>> c30ee33 (test)
            }
            true
        }
    }

    private fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        if (manager.findFragmentByTag(tag) == null){
            fragTransaction.add(R.id.mainFrameLayout, fragment, tag)
        }

        val community = manager.findFragmentByTag(TAG_COMMUNITY)
        val list = manager.findFragmentByTag(TAG_LIST)
        val mypage = manager.findFragmentByTag(TAG_MYPAGE)
        val map = manager.findFragmentByTag(TAG_MAP)

        if (community != null){
            fragTransaction.hide(community)
        }

        if (list != null){
            fragTransaction.hide(list)
        }

        if (mypage != null) {
            fragTransaction.hide(mypage)
        }
        if (map != null){
            fragTransaction.hide(map)
        }

        if (tag == TAG_COMMUNITY) {
            if (community!=null){
                fragTransaction.show(community)
            }
        }
        else if (tag == TAG_LIST) {
            if (list != null) {
                fragTransaction.show(list)
            }
        }

        else if (tag == TAG_MYPAGE){
            if (mypage != null){
                fragTransaction.show(mypage)
            }
        }
        else if (tag == TAG_MAP){
            if(map != null){
                fragTransaction.show(map)
            }
        }
        fragTransaction.commitAllowingStateLoss()
    }
}