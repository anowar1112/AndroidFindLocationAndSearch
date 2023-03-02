package com.first.mytestingfirstapps.roomDatabase

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData


class CourseRepository(application: Application?) {
    // below line is the creation a variable
    // for dao and list for all courses.
    private val dao: Dao

    // below method is to read all the courses.
    val allCourses: LiveData<List<CourseModal?>?>?

    // creating a constructor for our variables
    // and passing the variables to it.
    init {
        val database = CourseDatabase.getInstance(application!!)
        dao = database!!.Dao()
        allCourses = dao.allCourses
    }

    // creating a method to insert the data to our database.
    fun insert(model: CourseModal?) {
        InsertCourseAsyncTask(dao).execute(model)
    }

    // creating a method to update data in database.
    fun update(model: CourseModal?) {
        UpdateCourseAsyncTask(dao).execute(model)
    }

    // creating a method to delete the data in our database.
    fun delete(model: CourseModal?) {
        DeleteCourseAsyncTask(dao).execute(model)
    }

    // below is the method to delete all the courses.
    fun deleteAllCourses() {
        DeleteAllCoursesAsyncTask(dao).execute()
    }

    // we are creating an async task method to insert new course.
    private class InsertCourseAsyncTask internal constructor(private val dao: Dao) :
        AsyncTask<CourseModal?, Void?, Void?>() {

        override fun doInBackground(vararg model: CourseModal?): Void? {
            dao.insert(model[0])
            return null
        }
    }

    // we are creating an async task method to update our course.
    private class UpdateCourseAsyncTask internal constructor(private val dao: Dao) :
        AsyncTask<CourseModal?, Void?, Void?>() {
         override fun doInBackground(vararg models: CourseModal?): Void? {
            // below line is used to update
            // our modal in dao.
            dao.update(models[0])
            return null
        }
    }

    // we are creating an async task method to delete course.
    private class DeleteCourseAsyncTask internal constructor(private val dao: Dao) :
        AsyncTask<CourseModal?, Void?, Void?>() {
        override fun doInBackground(vararg models: CourseModal?): Void? {
            // below line is used to delete
            // our course modal in dao.
            dao.delete(models[0])
            return null
        }
    }

    // we are creating an async task method to delete all courses.
    private class DeleteAllCoursesAsyncTask internal constructor(private val dao: Dao) :
        AsyncTask<Void?, Void?, Void?>() {
        override fun doInBackground(vararg voids: Void?): Void? {
            // on below line calling method
            // to delete all courses.
            dao.deleteAllCourses()
            return null
        }
    }
}

