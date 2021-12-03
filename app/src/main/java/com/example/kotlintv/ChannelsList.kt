package com.example.kotlintv

import java.util.ArrayList

object ChannelsList {
    val CHANNELS_CATEGORY = arrayOf(
        "Channel 1",
        "Channel 2",
        "Category Two",
        "Category Three",
        "Category Four",
        "Category Five"
    )
    private var  list:List<Channels>? = null
    private var count: Long = 0
    fun getList(): List<Channels>? {
        if (list == null) {
            list = setupChannels()
        }
        return list
    }

    fun setupChannels(): List<Channels>? {
        list = ArrayList()
        val title = arrayOf(
            "Channel Card One",
            "Channel Card 2",
            "Channel Card 3",
            "Channel Card 4",
            "Channel Card 5"
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
            "https://origin-staticv2.sonyliv.com/landscape_thumb/show_badeachhekagtehai_ep69_Landscape_Thumb.jpg",
            "https://origin-staticv2.sonyliv.com/landscape_thumb/show_tkss_ep208_india_Landscape_Thumb.jpg",
            "https://origin-staticv2.sonyliv.com/landscape_thumb/promo_set_sharktank_191121_Landscape_Thumb.jpg",
            "https://origin-staticv2.sonyliv.com/landscape_thumb/Kaamna_Landscape_Thumb.jpg",
            "https://origin-staticv2.sonyliv.com/landscape_thumb/6134492789001.jpg"
        )
        for (index in title.indices) {
            (list as ArrayList<Channels>).add(
                buildChannelsInfo(
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

    private fun buildChannelsInfo(
        title: String,
        description: String,
        studio: String,
        videoUrl: String,
        cardImageUrl: String,
        backgroundImageUrl: String
    ): Channels {
        val channel = Channels()
        channel.id = count++
        channel.title = title
        channel.description = description
        channel.studio = studio
        channel.cardImageUrl = cardImageUrl
        channel.backgroundImageUrl = backgroundImageUrl
        channel.videoUrl = videoUrl
        return channel
    }
}