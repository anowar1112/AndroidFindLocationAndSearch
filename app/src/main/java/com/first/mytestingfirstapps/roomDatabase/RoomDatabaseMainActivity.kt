package com.first.mytestingfirstapps.roomDatabase

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.first.mytestingfirstapps.R


class RoomDatabaseMainActivity : AppCompatActivity() {
    private val viewModal: RoomViewModal by viewModels()

    private var eidTextRoom: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_database_main)

        eidTextRoom = findViewById<View>(R.id.eidTextRoom) as EditText

        // below line is used to get all the courses from view modal.
        viewModal.init()
        // below line is used to get all the courses from view modal.
        viewModal.getAllCourses()?.observe(this) {
            // when the data is changed in our models we are
            // adding that list to our adapter class.
            //todo: update adapter
            Log.d("RoomActivity"," out ")
            it?.let { courseList ->
                courseList.forEach{ item ->
                    Log.d("RoomActivity",""+item?.courseName)
                }
            }
        }

        val mapButtonRoom = findViewById<Button>(R.id.mapButtonRoom)

        mapButtonRoom.setOnClickListener {
            val name = eidTextRoom?.text.toString()
            if(name.isNotEmpty()){
                saveCourse(name,"cseSubject","2")
            }
        }
    }

    /// need to call
    private fun saveCourse(courseName:String, courseDescription:String,  courseDuration:String) {
        val model = CourseModal(courseName, courseDescription, courseDuration)
        viewModal.update(model)
        Toast.makeText(this, "Course updated", Toast.LENGTH_SHORT).show()
    }
}