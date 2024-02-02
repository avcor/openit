package com.example.openit.data.remote.type

import com.example.openit.activities.home.model.GraphData
import com.example.openit.activities.home.model.LinkData

sealed interface ApiResponseType{
    class Success(val responseData: LinkData, val graphData: GraphData): ApiResponseType
    class Failure: ApiResponseType
    class NoData : ApiResponseType
}