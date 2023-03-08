package com.first.mytestingfirstapps.roomDatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

// Adding annotation
// to our Dao class
@Dao
interface Dao {
    // below method is used to
    // add data to database.
    @Insert
    fun insert(model: CourseModal)

    // below method is used to update
    // the data in our database.
    @Update
    fun update(model: CourseModal)

    // below line is used to delete a
    // specific course in our database.
    @Delete
    fun delete(model: CourseModal)

    // on below line we are making query to
    // delete all courses from our database.
    @Query("DELETE FROM course_table")
    fun deleteAllCourses()

    // below line is to read all the courses from our database.
    // in this we are ordering our courses in ascending order
    // with our course name.
    @get:Query("SELECT * FROM course_table ORDER BY courseName ASC")
    val allCourses: LiveData<List<CourseModal>?>?
}

