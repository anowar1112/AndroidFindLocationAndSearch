package com.first.mytestingfirstapps.roomDatabase

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase


// adding annotation for our database entities and db version.
@Database(entities = [CourseModal::class], version = 1)
abstract class CourseDatabase : RoomDatabase() {
    // below line is to create
    // abstract variable for dao.
    abstract fun Dao(): Dao

    // we are creating an async task class to perform task in background.
    private class PopulateDbAsyncTask(instance: CourseDatabase?) :
        AsyncTask<Void?, Void?, Void?>() {
        init {
            val dao = instance!!.Dao()
        }

        override fun doInBackground(vararg p0: Void?): Void? {
            return null
        }
    }

    companion object {
        // below line is to create instance
        // for our database class.
        private var instance: CourseDatabase? = null

        // on below line we are getting instance for our database.
        @Synchronized
        fun getInstance(context: Context): CourseDatabase? {
            // below line is to check if
            // the instance is null or not.
            if (instance == null) {
                // if the instance is null we
                // are creating a new instance
                instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        CourseDatabase::class.java, "course_database"
                    ).fallbackToDestructiveMigration() // below line is to add callback
                        .addCallback(roomCallback) // below line is to
                        .build()
            }
            // after creating an instance
            // we are returning our instance
            return instance
        }

        // below line is to create a callback for our room database.
        private val roomCallback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // this method is called when database is created
                // and below line is to populate our data.
                PopulateDbAsyncTask(instance).execute()
            }
        }
    }
}

