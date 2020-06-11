package com.shandysiswandi.belajarmembuataplikasiandroiduntukpemula.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shandysiswandi.belajarmembuataplikasiandroiduntukpemula.R
import com.shandysiswandi.belajarmembuataplikasiandroiduntukpemula.model.Prophet
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initView()
    }

    private fun initView() {
        val data = intent.getParcelableExtra<Prophet?>("DATA")
        toolbar.title = data?.name
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        data?.image?.let { image.setImageResource(it) }
        tv_name.text = data?.name
        tv_description.text = data?.description
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
        overridePendingTransition(
            R.anim.slide_in_left,
            R.anim.slide_out_right
        )
        finish()
    }
}
