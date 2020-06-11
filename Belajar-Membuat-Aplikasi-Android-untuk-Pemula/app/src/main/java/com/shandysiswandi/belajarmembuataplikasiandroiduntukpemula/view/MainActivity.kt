package com.shandysiswandi.belajarmembuataplikasiandroiduntukpemula.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.shandysiswandi.belajarmembuataplikasiandroiduntukpemula.R
import com.shandysiswandi.belajarmembuataplikasiandroiduntukpemula.adapter.MainAdapter
import com.shandysiswandi.belajarmembuataplikasiandroiduntukpemula.model.Prophet
import com.shandysiswandi.belajarmembuataplikasiandroiduntukpemula.repository.DataProphet
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainAdapter.Interaction {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(topAppBar)

        val mainAdapter = MainAdapter( this)
        mainAdapter.submitList(DataProphet.generate())

        rv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = mainAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == R.id.about) {
            startActivity(Intent(applicationContext, AboutActivity::class.java))
            overridePendingTransition(
                R.anim.slide_in_from_bottom,
                R.anim.slide_out_to_top
            )
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemSelected(position: Int, item: Prophet) {
        val intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra("DATA", item)
        startActivity(intent)
        overridePendingTransition(
            R.anim.slide_in_right,
            R.anim.slide_out_left
        )
    }
}
