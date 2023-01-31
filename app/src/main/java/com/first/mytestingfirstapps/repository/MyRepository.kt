package com.first.mytestingfirstapps.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object MyRepository {
    private val _setvalue: MutableLiveData<String>? = null
    val setvalue: LiveData<String>
        get() = setvalue

    fun set(value:String){
        _setvalue?.postValue(value)
    }
}