package com.example.oneupfarm.data

import com.example.oneupfarm.model.NavigationItem
import com.example.oneupfarm.R
import com.example.oneupfarm.model.ChoosePlant
import com.example.oneupfarm.ui.screen.PlantsGrid

object DataSource {
    val navigationItems: List<NavigationItem> = listOf(
        NavigationItem(R.drawable.ic_profile, R.drawable.ic_profile_filled),
        NavigationItem(R.drawable.ic_track_plant, R.drawable.ic_track_plant_filled),
        NavigationItem(R.drawable.ic_calendar, R.drawable.ic_calendar),
        NavigationItem(R.drawable.ic_marketplace, R.drawable.ic_marketplace),
    )

    val staticPlantLists = listOf<ChoosePlant>(
        ChoosePlant("Tomat", R.drawable.lettuce, "Sedang"),
        ChoosePlant("Bawang Merah", R.drawable.lettuce, "Mudah"),
        ChoosePlant("Selada", R.drawable.lettuce, "Sedang"),
        ChoosePlant("Bayam", R.drawable.lettuce, "Sedang"),
        ChoosePlant("Cabai", R.drawable.lettuce, "Sedang"),
        ChoosePlant("Daun Bawang", R.drawable.lettuce, "Sedang")
    )
}