package com.example.covac.ui.signup

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import com.example.covac.MainActivity
import com.example.covac.R
import com.example.covac.ui.login.LoginActivity
import gun0912.tedimagepicker.builder.TedImagePicker
import kotlinx.android.synthetic.main.activity_set_nickname.*

class SetNicknameActivity : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_nickname)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(true)

        set_image_btn.setOnClickListener(this)
        start_btn.setOnClickListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.set_image_btn -> {
                TedImagePicker.with(this)
                    .title(R.string.select_picture)
                    .backButton(R.drawable.ic_arrow_back)
                    .buttonText(R.string.complete)
                    .buttonBackground(R.drawable.rec_tranparent)
                    .buttonTextColor(R.color.black)
                    .showCameraTile(false)
                    .start { uri -> showSingleImage(uri) }
            }

            R.id.start_btn -> startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun showSingleImage(uri: Uri) {

        imageView.setImageURI(uri)

    }


}