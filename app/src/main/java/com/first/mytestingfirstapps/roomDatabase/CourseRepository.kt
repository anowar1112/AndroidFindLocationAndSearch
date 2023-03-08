package com.first.mytestingfirstapps.roomDatabase

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class CourseRepository(application: Application?) {
    // below line is the creation a variable
    // for dao and list for all courses.
    private val dao: Dao

    // below method is to read all the courses.

    var allCourses_s : LiveData<List<CourseModal>?>? = null

    // creating a constructor for our variables
    // and passing the variables to it.
    init {
        val database = CourseDatabase.getInstance(application!!)
        dao = database!!.Dao()
        dao.allCourses?.let {
                allCourses_s = dao.allCourses
        }
    }

    private val updateList: (Dao) -> Unit= {dao: Dao ->
        dao.allCourses?.let {
            allCourses_s = dao.allCourses
        }
    } //lambda function

    // creating a method to insert the data to our database.
    fun insert(model: CourseModal?) {
        InsertCourseAsyncTask(dao,updateList).execute(model)
    }

    // creating a method to update data in database.
    fun update(model: CourseModal?) {
        Log.d("Repository", "update")
        UpdateCourseAsyncTask(dao,updateList).execute(model)
    }

    // creating a method to delete the data in our database.
    fun delete(model: CourseModal?) {
        DeleteCourseAsyncTask(dao,updateList).execute(model)
    }

    // below is the method to delete all the courses.
    fun deleteAllCourses() {
        DeleteAllCoursesAsyncTask(dao,updateList).execute()
    }

    // we are creating an async task method to insert new course.
    private class InsertCourseAsyncTask(private val dao: Dao, private val myLambda: (Dao) -> Unit ) :
        AsyncTask<CourseModal?, Void?, Void?>() {

        override fun doInBackground(vararg model: CourseModal?): Void? {
            model[0]?.let { dao.insert(it) }
            myLambda(dao)
            return null
        }
    }

    // we are creating an async task method to update our course.
    private class UpdateCourseAsyncTask(private val dao: Dao, private val myLambda: (Dao) -> Unit ) :
        AsyncTask<CourseModal?, Void?, Void?>() {
         override fun doInBackground(vararg models: CourseModal?): Void? {
            // below line is used to update
            // our modal in dao.
             Log.d("Repository","added")
             models[0]?.let { dao.update(it) }
             myLambda(dao)
            return null
        }
    }

    // we are creating an async task method to delete course.
    private class DeleteCourseAsyncTask(private val dao: Dao, private val myLambda: (Dao) -> Unit ) :
        AsyncTask<CourseModal?, Void?, Void?>() {
        override fun doInBackground(vararg models: CourseModal?): Void? {
            // below line is used to delete
            // our course modal in dao.
            models[0]?.let { dao.delete(it) }
            myLambda(dao)
            return null
        }
    }

    // we are creating an async task method to delete all courses.
    private class DeleteAllCoursesAsyncTask(private val dao: Dao, private val myLambda: (Dao) -> Unit ) :
        AsyncTask<Void?, Void?, Void?>() {
        override fun doInBackground(vararg voids: Void?): Void? {
            // on below line calling method
            // to delete all courses.
            dao.deleteAllCourses()
            myLambda(dao)
            return null
        }
    }
}

