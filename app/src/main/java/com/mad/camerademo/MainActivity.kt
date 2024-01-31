package com.mad.camerademo

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.imageview.ShapeableImageView
import com.mad.camerademo.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import com.theartofdev.edmodo.cropper.CropImage

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var uri: Uri

//    var camPermission: Array<String>? = null
//    var storagePermission:Array<String>? = null
//    var imageUri: Uri? = null
//    var click: TextView? = null
//
//    var userpic: ShapeableImageView? = null
//    val STORAGE_REQUEST = 200


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



//        camPermission = arrayOf(android.Manifest.permission.CAMERA,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
//
//        storagePermission = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
//        binding.btnSelect.setOnClickListener {
//            showImagePicDialog()
//        }



        binding.btnSelect.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start()
        }

    }



//    fun onChooseFile() {
//
//    }
//
//    private fun showImagePicDialog() {
//        val builder = AlertDialog.Builder(this)
//            .setTitle("Pick Image From")
//            .setPositiveButton("Gallery") { dialog, _ ->
//                if (!checkStoragePermission()) {
//                    requestStoragePermission()
//                } else {
//                    pickFromGallery()
//                }
//
//            }
//        builder.create().show()
//    }
//
//    private fun checkStoragePermission(): Boolean {
//        return ContextCompat.checkSelfPermission(
//            this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE
//        ) == PackageManager.PERMISSION_GRANTED
//
//    }
//
//    private fun pickFromGallery() {
//        CropImage.activity().start(this@MainActivity)
//    }
//
//    private fun requestStoragePermission() {
//        requestPermissions(storagePermission!!, STORAGE_REQUEST)
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == STORAGE_REQUEST) {
//            if (grantResults.size > 0) {
//                val writeStoregeaccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
//                if (writeStoregeaccepted) {
//                    pickFromGallery()
//                } else {
//                    Toast.makeText(
//                        this,
//                        "Please Enable Storage Permission",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            val result: CropImage.ActivityResult = CropImage.getActivityResult(data)
//            if (resultCode == RESULT_OK) {
//                val resultUri: Uri = result.getUri()
//                Picasso.with(this).load(resultUri).into(userpic)
//            }
//        }
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val uri: Uri = data?.data!!

            binding.siv.setImageURI(uri)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }


}