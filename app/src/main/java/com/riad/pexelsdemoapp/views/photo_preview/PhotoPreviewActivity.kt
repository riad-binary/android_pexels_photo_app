package com.riad.pexelsdemoapp.views.photo_preview

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.riad.pexelsdemoapp.R
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.riad.pexelsdemoapp.views.main.MainViewModel
import kotlinx.android.synthetic.main.activity_photo_preview.*

class PhotoPreviewActivity : AppCompatActivity() {
    private val TAG: String = MainViewModel::class.java.getName()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_preview)

        supportActionBar?.hide()

        val extras = intent.extras
        if (extras != null) {
            val url = extras.getString("photo_url")
            Glide.with(this)
                .load(url)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        progress_bar.visibility = View.GONE
                        txt_error.visibility = View.VISIBLE
                        return false
                    }
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        Log.d(TAG, "OnResourceReady")
                        progress_bar.visibility = View.GONE
                        return false
                    }
                })
                .into(photo_view)

        }

    }
}