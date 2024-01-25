package com.example.openit.activities.home.model

import com.google.gson.annotations.SerializedName


data class LinkDetails(
    val recent_links: List<Link>,
    val top_links: List<Link>,
    val favourite_links: List<Any>,

    @SerializedName("overall_url_chart")
    val overall_url_chart: Map<String, Int>?
)

data class Link(
    val url_id: Int,
    val web_link: String,
    val smart_link: String,
    val title: String,
    val total_clicks: Int,
    val original_image: String,
    val thumbnail: Any?,
    val times_ago: String,
    val created_at: String,
    val domain_id: String,
    val url_prefix: String?,
    val url_suffix: String,
    val app: String,
    val is_favourite: Boolean
)
