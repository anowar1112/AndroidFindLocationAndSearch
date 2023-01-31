package com.first.mytestingfirstapps.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NameViewModel : ViewModel() {


    private val _setvalue:MutableLiveData<String>? = null
    val setvalue:LiveData<String>
        get() = setvalue

    fun set(value:String){
        _setvalue?.postValue(value)
    }
    // Rest of the ViewModel...
}