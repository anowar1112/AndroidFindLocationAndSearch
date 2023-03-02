package com.first.mytestingfirstapps.roomDatabase

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.first.mytestingfirstapps.R


class RoomDatabaseMainActivity : AppCompatActivity() {
    private val viewModal: RoomViewModal by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_database_main)

        // below line is used to get all the courses from view modal.

        // below line is used to get all the courses from view modal.
        viewModal.getAllCourses()?.observe(this) {
            // when the data is changed in our models we are
            // adding that list to our adapter class.
            //todo: update adapter
        }
    }

    /// need to call
    private fun saveCourse(courseName:String, courseDescription:String,  courseDuration:String) {
        val model = CourseModal(courseName, courseDescription, courseDuration)
        viewModal.update(model)
        Toast.makeText(this, "Course updated", Toast.LENGTH_SHORT).show()
    }
}