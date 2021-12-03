package com.example.kotlintv

import java.util.ArrayList

object HomeList {
    val HOME_CATEGORY = arrayOf(
    "Category 1",
    "Category 2",
    "Category Two",
    "Category Three",
    "Category Four",
    "Category Five"
)
    private var  list:List<Home>? = null
    private var count: Long = 0
    fun getList(): List<Home>? {
        if (list == null) {
            list = setupHome()
        }
        return list
    }

    fun setupHome(): List<Home>? {
        list = ArrayList()
        val title = arrayOf(
            "Home Card 1",
            "Home Card 2",
            "Home Card 3",
            "Home Card 4",
            "Home Card 5"
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
            "https://origin-staticv2.sonyliv.com/landscape_thumb/show_marathi_criminals_ep04_Landscape_Thumb.jpg",
            "https://media.gettyimages.com/photos/cricket-batsman-hitting-ball-during-cricket-match-in-stadium-picture-id518022060?s=2048x2048",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Gmail%20Blue/card.jpg",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole/card.jpg",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose/card.jpg"
        )
        for (index in title.indices) {
            (list as ArrayList<Home>).add(
                buildHomeInfo(
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

    private fun buildHomeInfo(
        title: String,
        description: String,
        studio: String,
        videoUrl: String,
        cardImageUrl: String,
        backgroundImageUrl: String
    ): Home {
        val home = Home()
        home.id = count++
        home.title = title
        home.description = description
        home.studio = studio
        home.cardImageUrl = cardImageUrl
        home.backgroundImageUrl = backgroundImageUrl
        home.videoUrl = videoUrl
        return home
    }
}