package com.example.teamproject

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.teamproject.databinding.FragmentMapBinding
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import net.daum.mf.map.n.api.internal.NativeMapLocationManager.setShowCurrentLocationMarker
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [map.newInstance] factory method to
 * create an instance of this fragment.
 */
class map : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val ACCESS_FINE_LOCATION = 1000     // Request Code
    private var yes = false//test용


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
        val binding = FragmentMapBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val mapView = MapView(context)
        binding.clKakaoMapView.addView(mapView)



        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            val lm: LocationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val userNowLocation: Location? = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            val uLatitude = userNowLocation?.latitude
            val uLongitude = userNowLocation?.longitude
            val uNowPosition = MapPoint.mapPointWithGeoCoord(uLatitude!!, uLongitude!!)
            mapView.setMapCenterPoint(uNowPosition, true)
        }

        //mylocation버튼을 누르면 현재위치로 지도 이동
        binding.myLocationBtn.setOnClickListener(){
            if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {//권한이 없는 상태
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), ACCESS_FINE_LOCATION) // 권한 요청
            } else { // 권한이 있는 상태
                val lm: LocationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
                val userNowLocation: Location? = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                val uLatitude = userNowLocation?.latitude
                val uLongitude = userNowLocation?.longitude
                val uNowPosition = MapPoint.mapPointWithGeoCoord(uLatitude!!, uLongitude!!)
                mapView.setMapCenterPoint(uNowPosition, true)
            }
        }

        parseJSONData(requireContext(), mapView)

        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_map, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment map.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            map().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        data class Facility(
            val name: String,
            val sport: String,
            val address: String,
            var latitude: Double,
            var longitude: Double
        )

        fun readJSONFromAsset(context: Context, fileName: String): String? {
            return try {
                val inputStream = context.assets.open(fileName)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                String(buffer)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        fun drawableToBitmap(drawable: Drawable?): Bitmap? {
            if (drawable is BitmapDrawable) {
                return drawable.bitmap
            }

            val bitmap = Bitmap.createBitmap(drawable?.intrinsicWidth ?: 0, drawable?.intrinsicHeight ?: 0, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            drawable?.setBounds(0, 0, canvas.width, canvas.height)
            drawable?.draw(canvas)
            return bitmap
        }

        fun parseJSONData(context: Context, mapView: MapView) {
            val facilityList = mutableListOf<Facility>()
            val geocoder = Geocoder(context, Locale.KOREA)

            try {
                val jsonData = readJSONFromAsset(context, "gymData.json")
                val jsonArray = JSONArray(jsonData)

                for (i in 0 until jsonArray.length()) {
                    val facilityObj = jsonArray.getJSONObject(i)

                    val facilityName = facilityObj.getString("시설명")
                    val sport = facilityObj.getString("종 목")
                    val address = facilityObj.getString("주소")

                    val addressList = geocoder.getFromLocationName(address, 1)
                    if (addressList != null && addressList.isNotEmpty()) {
                        val location = addressList[0]
                        val latitude = location.latitude
                        val longitude = location.longitude

                        val facility = Facility(facilityName, sport, address, latitude, longitude)
                        facilityList.add(facility)
                    }
                }

                for (facility in facilityList) {
                    // 마커 아이콘 생성
                    val markerDrawable = ContextCompat.getDrawable(context, R.drawable.health)
                    val markerBitmap = drawableToBitmap(markerDrawable)

                    // 마커 생성
                    val marker = MapPOIItem()
                    marker.itemName = facility.name
                    marker.tag = facilityList.indexOf(facility)
                    marker.mapPoint = MapPoint.mapPointWithGeoCoord(facility.latitude, facility.longitude)
                    marker.markerType = MapPOIItem.MarkerType.CustomImage
                    marker.customImageBitmap = markerBitmap

                    // 마커를 지도에 추가
                    mapView.addPOIItem(marker)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
} 