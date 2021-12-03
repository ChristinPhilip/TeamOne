package com.example.kotlintv

import java.io.Serializable

data class Home( var id: Long = 0,
    var title: String? = null,
    var description: String? = null,
    var backgroundImageUrl: String? = null,
    var cardImageUrl: String? = null,
    var videoUrl: String? = null,
    var studio: String? = null
    ) : Serializable {

        override fun toString(): String {
            return "Home{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", videoUrl='" + videoUrl + '\'' +
                    ", backgroundImageUrl='" + backgroundImageUrl + '\'' +
                    ", cardImageUrl='" + cardImageUrl + '\'' +
                    '}'
        }

        companion object {
            internal const val serialVersionUID = 727566175075960653L
        }
    }