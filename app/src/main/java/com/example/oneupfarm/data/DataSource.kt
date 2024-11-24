package com.example.oneupfarm.data

import com.example.oneupfarm.model.NavigationItem
import com.example.oneupfarm.R
import com.example.oneupfarm.model.Badge

object DataSource {
    val navigationItems: List<NavigationItem> = listOf(
        NavigationItem(R.drawable.ic_profile, R.drawable.ic_profile_filled),
        NavigationItem(R.drawable.ic_track_plant, R.drawable.ic_track_plant_filled),
        NavigationItem(R.drawable.ic_calendar, R.drawable.ic_calendar),
        NavigationItem(R.drawable.ic_marketplace, R.drawable.ic_marketplace),
    )

    val dummyBadge: List<Badge> = listOf(
        Badge(1, R.drawable.badge1, "Penguasaan Mudah"),
        Badge(2, R.drawable.badge2, "Penguasaan Sedang")
    )
}