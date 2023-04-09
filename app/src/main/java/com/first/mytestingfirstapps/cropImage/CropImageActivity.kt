package com.first.mytestingfirstapps.cropImage

import android.content.Intent
import android.graphics.*
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
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
        val cropButton = findViewById<Button>(R.id.cropButton)

        cropButton.setOnClickListener {
            bitmap = crop(bitmap)
            imageView.setImageBitmap(bitmap)
        }
    }

    fun crop(originalBitmap: Bitmap):Bitmap{

// Define the coordinates of the crop area
        val cropLeft = 30
        val cropTop = 30
        val cropWidth = originalBitmap.width
        val cropHeight = originalBitmap.height
// Create a new bitmap with the cropped dimensions
        val croppedBitmap = Bitmap.createBitmap(cropWidth, cropHeight, Bitmap.Config.ARGB_8888)
// Create a new canvas to draw the cropped image onto
        val canvas = Canvas(croppedBitmap)
// Draw the cropped portion of the original image onto the canvas
        val srcRect = Rect(cropLeft, cropTop, cropLeft + cropWidth, cropTop + cropHeight)
        val destRect = Rect(0, 0, cropWidth, cropHeight)
        canvas.drawBitmap(originalBitmap, srcRect, destRect, null)

// Use the cropped bitmap as desired
        return croppedBitmap
    }

    private fun getimagepath(){
        val cachePath = "${File(applicationContext!!.cacheDir,"image")}"+"/default.png"
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