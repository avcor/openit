package com.example.openit.activities.home

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.openit.R
import com.example.openit.databinding.ActivityHomeBinding
import com.example.openit.activities.home.adapter.HomeLinkListRecycler
import com.example.openit.utils.getGreetings
import com.example.openit.utils.getRangeDateNow_30D_back
import com.example.openit.activities.home.model.Link
import com.example.openit.activities.home.model.LinkData
import com.example.openit.activities.home.viewModel.HomeViewModel
import com.example.openit.utils.makeDateSetLineGraph
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var rvAdapter: HomeLinkListRecycler
    private var topLinkList: List<Link> = ArrayList()
    private var recentLinkList: List<Link> = ArrayList()
    private lateinit var apiViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        setContentView(binding.root)

        apiViewModel = ViewModelProvider(this).get(HomeViewModel::class.java);

        apiViewModel.getLinkData().observe(this){
            topLinkList = it.data.top_links
            recentLinkList = it.data.recent_links
            binding.mydata = it
            setLineGraph(it)
            setLinkListRecycler(it.data.top_links)
        }

        apiViewModel.getIsCallComplete().observe(this){
            if(it) binding.progressCircular.visibility = View.GONE
        }

        binding.apply{
            topLinkButton.setOnClickListener {
                handleClickTopLink()
            }
            recentLinkButton.setOnClickListener {
                handleClickRecentLink()
            }
            greetingTextView.text = getGreetings()
            graphTimeRange.text = getRangeDateNow_30D_back()
        }
    }

    fun setLineGraph(it: LinkData) {
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
        rvAdapter = HomeLinkListRecycler(topLinks)
        binding.listItem.adapter = rvAdapter
    }

    private fun handleClickTopLink(){
        rvAdapter.updateDataSet(topLinkList)
        rvAdapter.notifyDataSetChanged()

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
        rvAdapter.notifyDataSetChanged()

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