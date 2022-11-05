package com.example.imagediary

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.io.IOException

class GalleryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            onPickPhoto()
        }
    }

    // PICK_PHOTO_CODE is a constant integer
    val PICK_PHOTO_CODE = 1046

    // Trigger gallery selection for a photo
    fun onPickPhoto() {
        // Create intent for picking a photo from the gallery
        val intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        Log.d("ONPICK", "Intent")
        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.

        Log.d("ONPICK", "Inside if")
        // Bring up gallery to select a photo
        ActivityCompat.startActivityForResult(this, intent, PICK_PHOTO_CODE, null)

    }

    fun loadFromUri(photoUri: Uri): Bitmap? {
        var image: Bitmap? = null
        try {
            // check version of Android on device
            image = if (Build.VERSION.SDK_INT > 27) {
                // on newer versions of Android, use the new decodeBitmap method
                val source: ImageDecoder.Source =
                    ImageDecoder.createSource(this.getContentResolver(), photoUri)
                ImageDecoder.decodeBitmap(source)
            } else {
                // support older versions of Android by using getBitmap
                MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return image
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null && requestCode == PICK_PHOTO_CODE) {
            val photoUri: Uri = data.data!!

            // Load the image located at photoUri into selectedImage
            val selectedImage = loadFromUri(photoUri)

            // Load the selected image into a preview
            val ivPreview: ImageView = findViewById(R.id.image) as ImageView
            ivPreview.setImageBitmap(selectedImage)
        }
    }
}