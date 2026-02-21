package com.example.therapet.app.data.util.sounds

import android.content.Context
import android.media.MediaPlayer

/**
 * Object for managing sounds
 *
 * Allows playing, loading and releasing sounds
 */
object SoundManager {
    private val mediaPlayers = mutableMapOf<String, MediaPlayer>()

    /**
     * Loads a sound resource and stores it under given name
     *
     * @param context    the context used to create the media player
     * @param name the name to reference the sound
     * @param resId the resource ID of the sound file
     */
    fun loadSound(context: Context, name: String, resId: Int) {
        val mp = MediaPlayer.create(context, resId)
        mediaPlayers[name] = mp
    }

    /**
     * Plays a loaded sound
     *
     * @param name the name of the sound to play
     */
    fun playSound(name: String) {
        mediaPlayers[name]?.let { mp ->
            mp.seekTo(0)
            mp.start()
        }
    }

    /**
     * Releases all MediaPlayer instances and clears the map
     */
    fun releaseAll() {
        mediaPlayers.values.forEach {
            it.release()
        }
        mediaPlayers.clear()
    }
}