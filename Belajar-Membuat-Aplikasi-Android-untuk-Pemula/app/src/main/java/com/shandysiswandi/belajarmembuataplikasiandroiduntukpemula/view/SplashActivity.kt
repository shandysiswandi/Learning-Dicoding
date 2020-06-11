package com.shandysiswandi.belajarmembuataplikasiandroiduntukpemula.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.shandysiswandi.belajarmembuataplikasiandroiduntukpemula.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(applicationContext, MainActivity::class.java))
            overridePendingTransition(
                R.anim.slide_in_from_bottom,
                R.anim.slide_out_to_top
            )
            finish()

        }, 4000L)
    }
}
