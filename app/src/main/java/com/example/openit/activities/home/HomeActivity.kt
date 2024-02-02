package com.example.openit.activities.home

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.openit.R
import com.example.openit.databinding.ActivityHomeBinding
import com.example.openit.activities.home.adapter.HomeLinkListRecycler
import com.example.openit.activities.home.model.GraphData
import com.example.openit.utils.getGreetings
import com.example.openit.utils.getRangeDateNow_30D_back
import com.example.openit.activities.home.model.Link
import com.example.openit.activities.home.viewModel.HomeViewModel
import com.example.openit.data.remote.type.ApiResponseType
import com.example.openit.utils.makeDateSetLineGraph
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    @Inject
    internal lateinit var rvAdapter: HomeLinkListRecycler

    private lateinit var binding: ActivityHomeBinding
    private var topLinkList: List<Link> = ArrayList()
    private var recentLinkList: List<Link> = ArrayList()
    private lateinit var apiViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        setContentView(binding.root)

        apiViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        apiViewModel.getResponse().observe(this){
            when(it){
                is ApiResponseType.Failure -> { Toast.makeText(this, "Unable to get data", Toast.LENGTH_SHORT).show() }
                is ApiResponseType.NoData -> { Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show() }
                is ApiResponseType.Success -> {
                    val linkData = it.responseData
                    topLinkList =  linkData.data.top_links
                    recentLinkList = linkData.data.recent_links
                    binding.mydata = linkData
                    setLineGraph(it.graphData)
                    setLinkListRecycler(linkData.data.top_links)
                }

            }
            binding.progressCircular.visibility = View.GONE
        }

        binding.apply{
            topLinkButton.setOnClickListener { handleClickTopLink() }
            recentLinkButton.setOnClickListener { handleClickRecentLink() }
            greetingTextView.text = getGreetings()
            graphTimeRange.text = getRangeDateNow_30D_back()
        }

    }

    fun setLineGraph(it: GraphData) {
        binding.aaChartViewMain.apply {
            data = makeDateSetLineGraph(it.yAxis, this@HomeActivity)
            legend.isEnabled = false
            description.isEnabled = false
            axisLeft.apply {
                setDrawGridLines(true)
                gridColor = ContextCompat.getColor(this@HomeActivity, R.color.grid_grey)
            }
            axisRight.isEnabled = false
            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                setDrawGridLines(true)
                gridColor = ContextCompat.getColor(this@HomeActivity, R.color.grid_grey)
                setLabelCount(10,false);
                setCenterAxisLabels(false)
                valueFormatter = object:IndexAxisValueFormatter(){
                    override fun getFormattedValue(value: Float): String {
                        val cur = value.toInt()
                        return if(cur>0 && cur<it.xTick.size && cur%3 == 0) it.xTick[cur] else ""
                    }
                }
                position = XAxis.XAxisPosition.BOTTOM
            }
            invalidate()
        }
    }

    private fun setLinkListRecycler(topLinks: List<Link>) {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.listItem.setLayoutManager(layoutManager)
        rvAdapter.updateDataSet(topLinks)
        binding.listItem.adapter = rvAdapter
    }

    private fun handleClickTopLink(){
        rvAdapter.updateDataSet(topLinkList)

        binding.apply {
            topLinkButton.apply {
                backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this@HomeActivity, R.color.text_blue))
                setTextColor(resources.getColor(R.color.white))
            }
            recentLinkButton.apply {
                backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this@HomeActivity, R.color.transparent))
                setTextColor(resources.getColor(R.color.text_grey))
            }
        }
    }

    private fun handleClickRecentLink(){
        rvAdapter.updateDataSet(recentLinkList)

        binding.apply {
            recentLinkButton.apply {
                backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this@HomeActivity, R.color.text_blue))
                setTextColor(resources.getColor(R.color.white))
            }
            topLinkButton.apply {
                backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this@HomeActivity, R.color.back_grey))
                setTextColor(resources.getColor(R.color.text_grey))
            }
        }
    }

}