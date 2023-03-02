package com.first.mytestingfirstapps.roomDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey


// below line is for setting table name.
@Entity(tableName = "course_table")
class CourseModal(
    var courseName: String, // below line is use for
    // course description.
    var courseDescription: String, // below line is use
    // for course duration.
    var courseDuration: String
) {
    // below line is to auto increment
    // id for each course.
    @PrimaryKey(autoGenerate = true) // variable for our id.
    var id = 0

}

