package com.example.demo.ui.componants.activities.home

import android.content.Context
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.demo.data.remote.Repositry
import com.example.demo.pojo.ImageData

class HomeViewModel(val context: Context) : ViewModel() {
    private var repositry = Repositry(context)

    //--------get photos from server--return livedata
    fun getPhotos(): LiveData<PagingData<ImageData>> {
        return repositry.getAllImages().cachedIn(viewModelScope)
    }


    //--------factory for viewmodel---
    class Factory(val context: Context) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(
                context
            ) as T
        }
    }
}