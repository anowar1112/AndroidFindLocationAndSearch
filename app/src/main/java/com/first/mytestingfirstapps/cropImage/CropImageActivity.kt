package com.first.mytestingfirstapps.cropImage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.first.mytestingfirstapps.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class CropImageActivity : AppCompatActivity() {
    private val TAG = "CropImageActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop_image)
        var bitmap = drawablleToBitmap()
        saveImgToCache(bitmap)
        val imageView = findViewById<ImageView>(R.id.cropImageView)
        imageView.setImageBitmap(bitmap)
        val cropRotationButton = findViewById<Button>(R.id.cropRotationButton)
        cropRotationButton.setOnClickListener {
            bitmap = imageRotation(bitmap,90f)
            saveImgToCache(bitmap)
            imageView.setImageBitmap(bitmap)
        }
    }

    private fun saveImgToCache(bitmap: Bitmap): File? {
        var cachePath: File? = null
        var fileName: String = "default"
        try {
            cachePath = File(applicationContext!!.cacheDir,"image")
            cachePath.mkdirs()
            val stream = FileOutputStream("$cachePath/$fileName.png")
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.close()
        } catch (e: IOException) {
            Log.e(TAG, "saveImgToCache error: $bitmap", e)
        }
        return cachePath
    }

    //https://stackoverflow.com/questions/8981845/android-rotate-image-in-imageview-by-an-angle

    private fun imageRotation(bitmap: Bitmap, rotation:Float): Bitmap{
        val matrix = Matrix()
        matrix.postRotate(rotation)
        //matrix.postScale(1.0f,1.0f)
        val rotated = Bitmap.createBitmap(
            bitmap, 0, 0, bitmap.width, bitmap.height,
            matrix, true
        )
        return rotated
    }

    private fun drawablleToBitmap() : Bitmap{
        val bitmap: Bitmap = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.photo1)
        return bitmap
    }
}