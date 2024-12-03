package com.example.oneupfarm.data

import com.example.oneupfarm.model.NavigationItem
import com.example.oneupfarm.R
import com.example.oneupfarm.model.ChoosePlant
import com.example.oneupfarm.model.PlantEducation
import com.example.oneupfarm.model.Badge
import com.example.oneupfarm.model.NotifType
import com.example.oneupfarm.model.Notification

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

    val plantGetstartedItems: List<PlantEducation> = listOf(
        PlantEducation(
            "Step 1",
            "Persiapan Pot Tanam",
            "Pilih pot yang tepat & Media Tanam",
            "Gunakan pot dengan diameter minimal 20-30 cm dan kedalaman sekitar 15-20 cm. Pastikan pot memiliki lubang drainase di bagian bawah untuk menghindari air tergenang yang bisa menyebabkan akar membusuk.",
            R.drawable.persiapan_tanaman_illustration
        ),
        PlantEducation(
            "Step 2",
            "Pemilihan Benih",
            "Pilih benih tanaman yang baik",
            "Pastikan benih Anda masih dalam kondisi baik (tidak kadaluarsa). Pilih varietas yang sesuai dengan preferensi Anda. Merendam benih dalam air hangat selama 1-2 jam bisa membantu mempercepat perkecambahan, tetapi langkah ini tidak wajib.",
            R.drawable.pemilihan_benih_illustration
        ),
        PlantEducation(
            "Step 3",
            "Penempatan Pot",
            "Jaga suhu dan kondisi lingkungan",
            "Tanaman memerlukan sinar matahari penuh minimal 4-6 jam sehari untuk pertumbuhan optimal. Tempatkan pot di area yang terkena sinar matahari langsung, seperti balkon, teras, atau dekat jendela.",
            R.drawable.pemilihan_pot_illustration
        )
    )

    val plantTipsItems: List<PlantEducation> = listOf(
        PlantEducation(
            "No 1",
            "Pupuk Organik",
            "Pupuk yang berasal dari bahan alami",
            "Pupuk ini berasal dari bahan-bahan alami, seperti sisa-sisa tanaman, kotoran hewan, atau bahan organik lainnya, yang memberikan nutrisi pada tanaman secara alami.",
            R.drawable.organic_fertilizer_illustration
        ),
        PlantEducation(
            "No 2",
            "Pupuk Anorganik",
            "Pupuk yang terbuat dari bahan kimia",
            "Pupuk yang dibuat dari bahan kimia atau mineral hasil olahan pabrik, bukan dari bahan alami. Pupuk ini dirancang untuk memberikan nutrisi tertentu, seperti nitrogen, fosfor, dan kalium, yang langsung tersedia dan cepat diserap oleh tanaman. ",
            R.drawable.inorganic_fertilizer
        ),
        PlantEducation(
            "No 3",
            "Pupuk Kandang Cair",
            "Pupuk yang berasal dari kotoran hewan ",
            "Pupuk yang dihasilkan dari hasil fermentasi kotoran hewan, seperti sapi, kambing, atau ayam, dalam bentuk cair. Proses fermentasi ini melibatkan penambahan air dan kadang-kadang bahan tambahan lainnya untuk mempercepat penguraian. Pupuk kandang cair kaya akan nitrogen, fosfor, dan kalium, serta mengandung mikroorganisme baik yang dapat meningkatkan kesuburan tanah.",
            R.drawable.liquid_manure_illustration
        )
    )

    val dummyBadge: List<Badge> = listOf(
        Badge(1, R.drawable.ic_badge_easy, "Penguasaan Mudah"),
        Badge(2, R.drawable.ic_badge_medium, "Penguasaan Sedang")
    )

    val dummyNotif: List<Notification> = listOf(
        Notification(1, R.drawable.tomato, "Jangan lupa menyiram tanaman tomatmu hari ini!", "Tomat", "03/12", NotifType.TODAY,false),
        Notification(2, R.drawable.garlic, "Jangan lupa memberi pupuk tanaman bawang putihmu hari ini!", "Bawang Putih", "03/12", NotifType.TODAY, false),
        Notification(3, R.drawable.spring_onion, "Jangan lupa menyiram tanaman daun bawangmu hari ini!", "Daun Bawang", "02/12", NotifType.NOT_TODAY, true),
        Notification(3, R.drawable.caisim, "Jangan lupa menyiram tanaman sawimu hari ini!", "Sawi", "02/12", NotifType.NOT_TODAY, true)
    )
}