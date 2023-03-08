package com.first.mytestingfirstapps.roomDatabase


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class RoomViewModal(application: Application) : AndroidViewModel(application) {
    // creating a new variable for course repository.
    private var repository: CourseRepository? = null
    private var appln: Application? = null

    // below line is to create a variable for live
    // data where all the courses are present.
    var allCourses : LiveData<List<CourseModal>?>? = null

    init {
        this.appln = application
    }
    fun init() {
        repository = CourseRepository(appln)
        allCourses = repository!!.allCourses_s
    }


    // below method is used to insert the data to our repository.
    fun insert(model: CourseModal?) {
        repository!!.insert(model)
    }

    // below line is to update data in our repository.
    fun update(model: CourseModal?) {
        Log.d("ViewModel", "update")
        repository!!.update(model)
    }

    // below line is to delete the data in our repository.
    fun delete(model: CourseModal?) {
        repository!!.delete(model)
    }

    // below method is to delete all the courses in our list.
    fun deleteAllCourses() {
        repository!!.deleteAllCourses()
    }

    // below method is to get all the courses in our list.
}