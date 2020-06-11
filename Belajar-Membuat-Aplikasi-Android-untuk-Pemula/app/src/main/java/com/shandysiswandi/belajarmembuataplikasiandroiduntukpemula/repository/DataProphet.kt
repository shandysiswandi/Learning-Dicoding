package com.shandysiswandi.belajarmembuataplikasiandroiduntukpemula.repository

import com.shandysiswandi.belajarmembuataplikasiandroiduntukpemula.R
import com.shandysiswandi.belajarmembuataplikasiandroiduntukpemula.model.Prophet
import kotlin.random.Random

object DataProphet {
    private val items = (1..25).map {
        Prophet(it, R.drawable.icon, "Nabi Adam", "Nabi Pertama didunia sekaligus manusia pertama didunia.")
    }

    fun generate(): List<Prophet> {
        val rand = Random(System.currentTimeMillis())
        return items.filter { rand.nextBoolean() }
    }
}