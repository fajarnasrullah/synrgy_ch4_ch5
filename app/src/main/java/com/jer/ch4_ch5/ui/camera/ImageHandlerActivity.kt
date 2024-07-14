package com.jer.ch4_ch5.ui.camera

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.load
import com.jer.ch4_ch5.R
import com.jer.ch4_ch5.databinding.ActivityImageHandlerBinding
import com.nareshchocha.filepickerlibrary.models.PickMediaConfig
import com.nareshchocha.filepickerlibrary.models.PickMediaType
import com.nareshchocha.filepickerlibrary.ui.FilePicker
import java.io.File

class ImageHandlerActivity : AppCompatActivity() {


    private lateinit var binding : ActivityImageHandlerBinding

    private lateinit var uri: Uri

    private val permissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions(),
        ::handlePermissionResult,
    )

    private val galleryResult = registerForActivityResult(
        ActivityResultContracts.GetContent(),
        ::handleGalleryResult,
    )

    private val cameraResult = registerForActivityResult(
        ActivityResultContracts.TakePicture(),
        ::handleCameraResult,
    )

    private val filePickerResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ::handleFilePickerResult,
        )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageHandlerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddImage.setOnClickListener {
            chooseImageDialog()
        }
    }

    private fun handlePermissionResult(result: Map<String, Boolean>) {
        if (result.containsValue(false)) {
            Toast.makeText(this, "permission telah ditolak", Toast.LENGTH_SHORT).show()
            onBackPressed()
        } else {
            Toast.makeText(this, "permission telah  diterima", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleGalleryResult(result: Uri?) {
        binding.ivImage.load(result)
    }

    private fun handleCameraResult(result: Boolean) {
        if (result) {
            binding.ivImage.load(uri)
        }
    }

    private fun askPermission() {
        permissionRequest.launch(
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
            )
        )
    }

    private fun isWriteExternalStorageGranted(): Boolean {
        return ContextCompat.checkSelfPermission(this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun isReadExternalStorageGranted(): Boolean {
        return ContextCompat.checkSelfPermission(this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun isCameraGranted(): Boolean {
        return ContextCompat.checkSelfPermission(this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun chooseImageDialog() {
        AlertDialog.Builder(this)
            .setMessage("Pilih Gambar")
            .setPositiveButton("Gallery") { _, _ ->
                openGallery()
//                openFilePicker()
            }
            .setNegativeButton("Camera") { _, _ ->
                openCamera()
//                openCameraFromLibrary()
            }
            .show()
    }

    private fun openGallery() {
        intent.type = "image/*"
        galleryResult.launch("image/*")
    }

    private fun openFilePicker() {
        filePickerResult.launch(
            FilePicker.Builder(this)
                .pickMediaBuild(
                    PickMediaConfig(
                        mPickMediaType = PickMediaType.ImageOnly,
                        allowMultiple = false,
                    ),
                ),
        )
    }

    private fun openCameraFromLibrary() {
        filePickerResult.launch(
            FilePicker.Builder(this)
                .imageCaptureBuild(),
        )
    }

    private fun openCamera() {
        val photoFile = File.createTempFile(
            "SYNRGY_",
            ".jpg",
            this.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        )

        uri = FileProvider.getUriForFile(
            this,
            "com.jer.ch4_ch5.dev.provider",
            photoFile
        )

        cameraResult.launch(uri)
    }

    private fun handleFilePickerResult(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            binding.ivImage.load(result.data?.data)
        }
    }
}