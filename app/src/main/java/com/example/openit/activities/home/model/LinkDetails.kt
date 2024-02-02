package com.example.openit.activities.home.model

import com.google.gson.annotations.SerializedName

data class LinkDetails(
    val recent_links: List<Link>,
    val top_links: List<Link>,
    val favourite_links: List<Any>,
    @SerializedName("overall_url_chart")
    val overall_url_chart: Map<String, Int>?
)


