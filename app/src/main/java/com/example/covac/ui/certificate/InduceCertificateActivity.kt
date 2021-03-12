package com.example.covac.ui.certificate

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import com.afollestad.materialdialogs.MaterialDialog
import com.example.covac.R
import com.github.dhaval2404.imagepicker.ImagePicker
import gun0912.tedimagepicker.builder.TedImagePicker
import kotlinx.android.synthetic.main.activity_induce_certificate.*
import kotlinx.android.synthetic.main.activity_set_nickname.*
import java.io.File

class InduceCertificateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_induce_certificate)

        certificate_image.setOnClickListener {
            MaterialDialog(this).show {
                title(R.string.upload_picture)
                cornerRadius(0f)
                neutralButton(R.string.cancle)

                positiveButton(R.string.take_picture) {

                    ImagePicker.with(this@InduceCertificateActivity)
                        .cameraOnly()    //User can only capture image using Camera
                        .start()

                }

                negativeButton(R.string.select_gallery) {

                    TedImagePicker.with(this@InduceCertificateActivity)
                        .title(R.string.select_picture)
                        .backButton(R.drawable.ic_arrow_back)
                        .buttonText(R.string.complete)
                        .buttonBackground(R.drawable.rec_tranparent)
                        .buttonTextColor(R.color.black)
                        .showCameraTile(false)
                        .start { uri -> showSingleImage(uri) }
                }
            }
        }
    }

    private fun showSingleImage(uri: Uri) {


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {

//                val fileUri = data?.data
//                val file: File = ImagePicker.getFile(data)!!
//                val filePath: String = ImagePicker.getFilePath(data)!!
//
//                val newImage = ImageView(this)
//                newImage.setImageURI(fileUri)
//
//
//                val lp = LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.MATCH_PARENT,
//                    205.toPx(this)
//                )
//                lp.setMargins(0, 0, 0, 20.toPx(this))
//                newImage.layoutParams = lp
//                picture_layout.addView(newImage)

            }
        }
    }
}