package com.example.teamproject

import org.json.JSONArray

class dataParsing {
    data class GymInfo(
        val facilityName: String,
        val category: String,
        val address: String,
        val latitude: Double,
        val longitude: Double
    )

    fun parseGymData(json: String?): List<GymInfo> {
        val jsonArray = JSONArray(json)
        val dataList = mutableListOf<GymInfo>()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val facilityName = jsonObject.getString("DataGym")
            val category = jsonObject.getString("Column2")
            val address = jsonObject.getString("Column3")
            val latitude = jsonObject.getDouble("Column4")
            val longitude = jsonObject.getDouble("Column5")

            val gymInfo = GymInfo(facilityName, category, address, latitude, longitude)
            dataList.add(gymInfo)
        }
        return dataList
    }
}