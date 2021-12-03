package com.example.kotlintv

import android.os.Bundle
import android.view.View
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import java.util.*

class RowFragment(private var pageName: String) : RowsSupportFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (pageName) {
            "Sports" -> {
                loadSportData()
            }
            "Movies" -> {
                loadMovieData()
            }
            "Home" -> {
                loadHomeData()
            }
            else -> {
                loadChannelsData()
            }
        }
    }

    private fun loadChannelsData() {
        val list: List<Channels>? = ChannelsList.setupChannels()
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val cardPresenter = CardPresenter(pageName)
        var i = 0
        while (i < 2) {
            if (i != 0) {
                Collections.shuffle(list)
            }
            val listRowAdapter = ArrayObjectAdapter(cardPresenter)
            for (j in 0..4) {
                listRowAdapter.add(list?.get(j % 5))
            }
            val header = HeaderItem(i.toLong(), ChannelsList.CHANNELS_CATEGORY[i])
            rowsAdapter.add(ListRow(header, listRowAdapter))
            i++
        }
        adapter = rowsAdapter
    }

    private fun loadHomeData() {
        val list: List<Home>? = HomeList.setupHome()
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val cardPresenter = CardPresenter(pageName)
        var i = 0
        while (i < 2) {
            if (i != 0) {
                Collections.shuffle(list)
            }
            val listRowAdapter = ArrayObjectAdapter(cardPresenter)
            for (j in 0..4) {
                listRowAdapter.add(list?.get(j % 5))
            }
            val header = HeaderItem(i.toLong(), HomeList.HOME_CATEGORY[i])
            rowsAdapter.add(ListRow(header, listRowAdapter))
            i++
        }
        adapter = rowsAdapter
    }

    private fun loadMovieData() {
        val list: List<Movie?> = MovieList.setupMovies()
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val cardPresenter = CardPresenter(pageName)
        var i = 0
        while (i < 2) {
            if (i != 0) {
                Collections.shuffle(list)
            }
            val listRowAdapter = ArrayObjectAdapter(cardPresenter)
            for (j in 0..4) {
                listRowAdapter.add(list[j % 5])
            }
            val header = HeaderItem(i.toLong(), MovieList.MOVIE_CATEGORY[i])
            rowsAdapter.add(ListRow(header, listRowAdapter))
            i++
        }
        adapter = rowsAdapter
    }

    private fun loadSportData() {
        val list: List<Sport>? = SportsList.setupSports()
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val cardPresenter = CardPresenter(pageName)
        var i = 0
        while (i < 2) {
            if (i != 0) {
                Collections.shuffle(list)
            }
            val listRowAdapter = ArrayObjectAdapter(cardPresenter)
            for (j in 0..4) {
                listRowAdapter.add(list?.get(j % 5))
            }
            val header = HeaderItem(i.toLong(), SportsList.SPORT_CATEGORY[i])
            rowsAdapter.add(ListRow(header, listRowAdapter))
            i++
        }
        adapter = rowsAdapter
    }
}