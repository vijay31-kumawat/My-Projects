package com.example.demo.ui.componants.activities.home

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.demo.R
import com.example.demo.databinding.ActivityHomeBinding
import com.example.demo.ui.base.BaseBindingActivity
import com.example.demo.ui.componants.adapters.ItemAdapter
import com.example.demo.utils.Utility

class HomeActivity : BaseBindingActivity() {
    private var viewModel: HomeViewModel? = null
    private var binding: ActivityHomeBinding? = null
    var adapter: ItemAdapter? = null

    override fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding?.lifecycleOwner = this //----set lifecyclerowner for binding--
        viewModel = ViewModelProvider(this, HomeViewModel.Factory(this))//------viewmodel
            .get(HomeViewModel::class.java)
    }

    override fun createActivityObject(savedInstanceState: Bundle?) {
        mActivity = this
    }

    override fun initializeObject() {
        adapter = ItemAdapter()
        binding?.rvItem?.setHasFixedSize(true)
        binding?.adapter = adapter

        if (Utility.isOnline(mActivity!!)) { //----check if internet connected or not-------
            getItemData()
        }

    }

    //----------get data from viewmodel----
    private fun getItemData() {
        viewModel?.getPhotos()?.observe(binding?.lifecycleOwner!!, Observer {
            adapter?.submitData(
                binding?.lifecycleOwner?.lifecycle!!,
                it
            )//--------submit data to pagingadapter
        })
    }

    override fun setListeners() {

    }
}