package com.example.therapet.app.data.util.sounds

import android.content.Context
import android.media.MediaPlayer

object SoundManager {
    private val mediaPlayers = mutableMapOf<String, MediaPlayer>()

    fun loadSound(context: Context, name: String, resId: Int) {
        val mp = MediaPlayer.create(context, resId)
        mediaPlayers[name] = mp
    }

    fun playSound(name: String) {
        mediaPlayers[name]?.let { mp ->
            mp.seekTo(0)
            mp.start()
        }
    }

    fun releaseAll() {
        mediaPlayers.values.forEach {
            it.release()
        }
        mediaPlayers.clear()
    }
}