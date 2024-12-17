package com.example.oneupfarm.data

import com.example.oneupfarm.R
import com.example.oneupfarm.model.Badge
import com.example.oneupfarm.model.ChoosePlant
import com.example.oneupfarm.model.LeaderboardUser
import com.example.oneupfarm.model.NavigationItem
import com.example.oneupfarm.model.NotifType
import com.example.oneupfarm.model.Notification
import com.example.oneupfarm.model.PlantEducation
import com.example.oneupfarm.model.Task
import com.example.oneupfarm.model.ToDo


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
        Notification(
            1,
            R.drawable.tomato,
            "Jangan lupa menyiram tanaman tomatmu hari ini!",
            "Tomat",
            "03/12",
            NotifType.TODAY,
            false
        ),
        Notification(
            2,
            R.drawable.garlic,
            "Jangan lupa memberi pupuk tanaman bawang putihmu hari ini!",
            "Bawang Putih",
            "03/12",
            NotifType.TODAY,
            false
        ),
        Notification(
            3,
            R.drawable.spring_onion,
            "Jangan lupa menyiram tanaman daun bawangmu hari ini!",
            "Daun Bawang",
            "02/12",
            NotifType.NOT_TODAY,
            true
        ),
        Notification(
            3,
            R.drawable.caisim,
            "Jangan lupa menyiram tanaman sawimu hari ini!",
            "Sawi",
            "02/12",
            NotifType.NOT_TODAY,
            true
        )
    )
    val dummyTaskNotDone: List<Task> = listOf(
        Task(1, "Siram tanaman", 13, 10),
        Task(2, "Pemberian pupuk", null, 40),
        Task(3, "Cari penyakit & hama", null, 5),
        Task(4, "Bersihkan tanaman & sekitar", 30, null)
    )

    val dummyTaskDone: List<Task> = listOf(
        Task(1, "Siram tanaman", 13, 10, true),
        Task(2, "Pemberian pupuk", null, 40, true),
        Task(3, "Cari penyakit & hama", null, 5, true),
        Task(4, "Bersihkan tanaman & sekitar", 30, null, true)
    )

    val dummyToDos: List<ToDo> = listOf(
        ToDo(1, "Tomat", R.drawable.lettuce, dummyTaskNotDone.toMutableList()),
        ToDo(2, "Selada", R.drawable.lettuce, dummyTaskNotDone.toMutableList()),
        ToDo(3, "Bawang Merah", R.drawable.lettuce, dummyTaskDone.toMutableList()),
        ToDo(4, "Cabai", R.drawable.lettuce, dummyTaskDone.toMutableList()),
    )

    val dummyLeaderboardUser: List<LeaderboardUser> = listOf(
        LeaderboardUser(1, R.drawable.boyavatar, "Zacky Khelfa", 10, 13200),
        LeaderboardUser(2, R.drawable.boyavatar,"Ibnu Hanif", 9, 13200),
        LeaderboardUser(3, R.drawable.boyavatar,"Bagas Satire", 8, 13200),
        LeaderboardUser(4, R.drawable.boyavatar,"Aldy Jhonatan", 7, 13200),
        LeaderboardUser(5, R.drawable.girlavatar,"Rizqi Vela Syifa", 6, 13200),
        LeaderboardUser(6, R.drawable.girlavatar,"Alia Pramestia", 5, 13200),
        LeaderboardUser(7, R.drawable.boyavatar,"Jamal Jasuke", 4, 13200),
        LeaderboardUser(8, R.drawable.boyavatar,"Mas Ironi", 3, 13200),
        LeaderboardUser(9, R.drawable.boyavatar,"Mas Fuad", 2, 13200),
        LeaderboardUser(10, R.drawable.boyavatar,"Mas Andre", 1, 13200)
    )
}