package com.example.kotlintv

import java.util.ArrayList

object SportsList {
    val SPORT_CATEGORY = arrayOf(
        "Cricket",
        "Football",
        "Category Two",
        "Category Three",
        "Category Four",
        "Category Five"
    )
    private var  list:List<Sport>? = null
    private var count: Long = 0
    fun getList(): List<Sport>? {
        if (list == null) {
            list = setupSports()
        }
        return list
    }

    fun setupSports(): List<Sport>? {
        list = ArrayList()
        val title = arrayOf(
            "Sport Card One",
            "Sport Card 2",
            "Sport Card 3",
            "Sport Card 4",
            "Sport Card 5"
        )
        val description = ("Fusce id nisi turpis. Praesent viverra bibendum semper. "
                + "Donec tristique, orci sed semper lacinia, quam erat rhoncus massa, non congue tellus est "
                + "quis tellus. Sed mollis orci venenatis quam scelerisque accumsan. Curabitur a massa sit "
                + "amet mi accumsan mollis sed et magna. Vivamus sed aliquam risus. Nulla eget dolor in elit "
                + "facilisis mattis. Ut aliquet luctus lacus. Phasellus nec commodo erat. Praesent tempus id "
                + "lectus ac scelerisque. Maecenas pretium cursus lectus id volutpat.")
        val studio = arrayOf(
            "Studio Zero", "Studio One", "Studio Two", "Studio Three", "Studio Four"
        )
        val videoUrl = arrayOf(
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search.mp4",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Gmail%20Blue.mp4",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole.mp4",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose.mp4"
        )
        val bgImageUrl = arrayOf(
            "https://origin-staticv2.sonyliv.com/landscape_thumb/show_marathi_criminals_ep04_Landscape_Thumb.jpg",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search/bg.jpg",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Gmail%20Blue/bg.jpg",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole/bg.jpg",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose/bg.jpg"
        )
        val cardImageUrl = arrayOf(
            "https://origin-staticv2.sonyliv.com/landscape_thumb/UEFAChampionsLeague2021-22GOB_Landscape_Thumb.jpg",
            "https://origin-staticv2.sonyliv.com/landscape_thumb/WBBLGOB_Landscape_Thumb1.jpg",
            "https://origin-staticv2.sonyliv.com/landscape_thumb/BBL_2021_Landscape_Thumb_GOB_IND.jpg",
            "https://origin-staticv2.sonyliv.com/landscape_thumb/LPL2021GOB_Landscape_Thumb1.jpg",
            "https://origin-staticv2.sonyliv.com/landscape_thumb/EuropeanQualifiersForFIFAWC2022Generic_Landscape_Thumb.jpg"
        )
        for (index in title.indices) {
            (list as ArrayList<Sport>).add(
                buildSportInfo(
                    title[index],
                    description,
                    studio[index],
                    videoUrl[index],
                    cardImageUrl[index],
                    bgImageUrl[index]
                )
            )
        }
        return list
    }

    private fun buildSportInfo(
        title: String,
        description: String,
        studio: String,
        videoUrl: String,
        cardImageUrl: String,
        backgroundImageUrl: String
    ): Sport {
        val sport = Sport()
        sport.id = count++
        sport.title = title
        sport.description = description
        sport.studio = studio
        sport.cardImageUrl = cardImageUrl
        sport.backgroundImageUrl = backgroundImageUrl
        sport.videoUrl = videoUrl
        return sport
    }
}