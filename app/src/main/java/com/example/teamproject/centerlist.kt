package com.example.teamproject

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamproject.databinding.FragmentCenterlistBinding
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import net.daum.mf.map.n.api.internal.NativeMapLocationManager.setShowCurrentLocationMarker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [map.newInstance] factory method to
 * create an instance of this fragment.
 */
class centerlist : Fragment() {
    companion object {
        const val BASE_URL = "https://dapi.kakao.com/"
        const val API_KEY = "KakaoAK e860d902e04ea353745473e10e8170ed"  // REST API 키
    }
    // TODO: Rename and change types of parameters
    private val listItems = arrayListOf<ListLayout>()   // 리사이클러 뷰 아이템
    private val listAdapter = ListAdapter(listItems)    // 리사이클러 뷰 어댑터
    private var pageNumber = 1      // 검색 페이지 번호
    private var keyword = ""        // 검색 키워드
    // 검색 키워드
    private var x= "37.380238"
    private var y= "126.802891"
    private var param1: String? = null
    private var param2: String? = null
    private val ACCESS_FINE_LOCATION = 1000     // Request Code
    private var yes = false//test용
    private lateinit var binding : FragmentCenterlistBinding
    private lateinit var userNowLocation: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCenterlistBinding.inflate(inflater, container, false)//
        context ?: return binding.root


        // 리사이클러 뷰
        binding.rvList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvList.adapter = listAdapter
        listAdapter.setItemClickListener(object: ListAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                Toast.makeText(requireContext(), "거리:"+listItems[position].distance+"m", Toast.LENGTH_SHORT).show()
            }
        })

        // 이전 페이지 버튼
        binding.btnPrevPage.setOnClickListener {
            pageNumber--
            binding.tvPageNumber.text = pageNumber.toString()
            searchKeyword(keyword, pageNumber,true)
        }

        // 다음 페이지 버튼
        binding.btnNextPage.setOnClickListener {
            pageNumber++
            binding.tvPageNumber.text = pageNumber.toString()
            searchKeyword(keyword, pageNumber,true)
        }
        binding.constraintLayout.setVisibility(View.VISIBLE)
        binding.constraintLayout.setVisibility(View.VISIBLE)


        //1행 카테고리
        binding.billiards.setOnClickListener {
            keyword="당구"
            pageNumber = 1
            searchKeyword(keyword, pageNumber,true)
        }
        binding.soccer.setOnClickListener {
            keyword="축구"
            pageNumber = 1
            searchKeyword(keyword, pageNumber,true)
        }
        binding.baseball.setOnClickListener {
            keyword="야구"
            pageNumber = 1
            searchKeyword(keyword, pageNumber,true)
        }
        binding.basketball.setOnClickListener {
            keyword="농구"
            pageNumber = 1
            searchKeyword(keyword, pageNumber,true)
        }

        //2행 카테고리
        binding.footvolley.setOnClickListener {
            keyword="족구"
            pageNumber = 1
            searchKeyword(keyword, pageNumber,true)
        }
        binding.pingpong.setOnClickListener {
            keyword="탁구"
            pageNumber = 1
            searchKeyword(keyword, pageNumber,true)
        }
        binding.bowling.setOnClickListener {
            keyword="볼링"
            pageNumber = 1
            searchKeyword(keyword, pageNumber,true)
        }
        binding.football.setOnClickListener {
            keyword="풋살"
            pageNumber = 1
            searchKeyword(keyword, pageNumber,true)
        }

        //3행 카테고리
        binding.tennis.setOnClickListener {
            keyword="테니스"
            pageNumber = 1
            searchKeyword(keyword, pageNumber,true)
        }
        binding.badminton.setOnClickListener {
            keyword="배드민턴"
            pageNumber = 1
            searchKeyword(keyword, pageNumber,true)
        }
        binding.golf.setOnClickListener {
            keyword="골프"
            pageNumber = 1
            searchKeyword(keyword, pageNumber,true)
        }
        binding.swimming.setOnClickListener {
            keyword="수영"
            pageNumber = 1
            searchKeyword(keyword, pageNumber,true)
        }

        //4행 카테고리
        binding.pilates.setOnClickListener {
            keyword="필라테스"
            pageNumber = 1
            searchKeyword(keyword, pageNumber,true)
        }
        binding.workout.setOnClickListener {
            keyword="헬스"
            pageNumber = 1
            searchKeyword(keyword, pageNumber,true)
        }
        binding.yoga.setOnClickListener {
            keyword="요가"
            pageNumber = 1
            searchKeyword(keyword, pageNumber,true)
        }
        binding.climbing.setOnClickListener {
            keyword="클라이밍"
            pageNumber = 1
            searchKeyword(keyword, pageNumber,true)
        }

        //5행 카테고리
        binding.taekwondo.setOnClickListener {
            keyword="태권도"
            pageNumber = 1
            searchKeyword(keyword, pageNumber,true)
        }
        binding.boxing.setOnClickListener {
            keyword="복싱"
            pageNumber = 1
            searchKeyword(keyword, pageNumber,true)
        }
        binding.jujitsu.setOnClickListener {
            keyword="주짓수"
            pageNumber = 1
            searchKeyword(keyword, pageNumber,false)
        }
        binding.judo.setOnClickListener {
            keyword="유도"
            pageNumber = 1
            searchKeyword(keyword, pageNumber,false)
        }
        //6행 카테고리
        binding.outdoor.setOnClickListener {
            //카카오맵에 없음
        }
        binding.etc.setOnClickListener {
            keyword="게이트볼"
            pageNumber = 1
            searchKeyword("게이트볼", pageNumber,false)
            searchKeyword("양궁", pageNumber,false)
            searchKeyword("궁도", pageNumber,false)
            searchKeyword("썰매", pageNumber,false)
        }
        val sportlist = arrayOf("당구","축구","야구","농구","족구","탁구","볼링","풋살","테니스","배드민턴","골프","수영","필라테스","헬스","요가","클라이밍","태권도","복싱","주짓수","유도")
        binding.all.setOnClickListener {
            keyword="전체"
            pageNumber = 1
            for (i in 0..19) searchKeyword(sportlist[i], pageNumber,false)
        }

        //위치권한 있는지 확인
        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            val lm: LocationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
            userNowLocation = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)!!
            val uLatitude = userNowLocation?.latitude
            val uLongitude = userNowLocation?.longitude
            val uNowPosition = MapPoint.mapPointWithGeoCoord(uLatitude!!, uLongitude!!)
        }
        else{
            requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), ACCESS_FINE_LOCATION) // 권한 요청
        }

        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_map, container, false)
    }


    private fun searchKeyword(keyword: String, page: Int, remove: Boolean) {
        val retrofit = Retrofit.Builder()          // Retrofit 구성
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(KakaoAPI::class.java)            // 통신 인터페이스를 객체로 생성
        val call = api.getSearchKeyword(API_KEY, keyword, page,userNowLocation.longitude.toString() ,userNowLocation.latitude.toString(), 10000, "distance")    // 검색 조건 입력

        // API 서버에 요청
        call.enqueue(object: Callback<ResultSearchKeyword> {
            override fun onResponse(call: Call<ResultSearchKeyword>, response: Response<ResultSearchKeyword>) {
                // 통신 성공
                if (!response.body()?.documents.isNullOrEmpty()) {
                    // 검색 결과 있음
                    listItems.clear()                   // 리스트 초기화
                    for (document in response.body()!!.documents) {
                        // 결과를 리사이클러 뷰에 추가
                        val item = ListLayout(document.place_name,
                            document.road_address_name,
                            document.address_name,
                            document.x.toDouble(),
                            document.y.toDouble(),
                            document.distance)
                        listItems.add(item)
                    }
                    listAdapter.notifyDataSetChanged()

                    binding.btnNextPage.isEnabled = !response.body()!!.meta.is_end // 페이지가 더 있을 경우 다음 버튼 활성화
                    binding.btnPrevPage.isEnabled = pageNumber != 1             // 1페이지가 아닐 경우 이전 버튼 활성화

                } else {
                    // 검색 결과 없음
                    Toast.makeText(requireContext(), "검색 결과가 없습니다", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResultSearchKeyword>, t: Throwable) {
                // 통신 실패
                Log.w("LocalSearch", "통신 실패: ${t.message}")
            }
        })
    }

}