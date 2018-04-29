package xyz.avarel.aria.music

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame
import net.dv8tion.jda.core.audio.AudioSendHandler

/**
 * Interface used to send audio to Discord through JDA, specialized for LavaPlayer.
 * @param audioPlayer
 *        Audio player to wrap.
 */
class AudioPlayerSendHandler(private val audioPlayer: AudioPlayer) : AudioSendHandler {
    private var lastFrame: AudioFrame? = null

    override fun canProvide(): Boolean {
        if (lastFrame == null) {
            lastFrame = audioPlayer.provide()
        }

        return lastFrame != null
    }

    override fun provide20MsAudio(): ByteArray? {
        if (lastFrame == null) {
            lastFrame = audioPlayer.provide()
        }

        val data = lastFrame?.data
        lastFrame = null

        return data
    }

    override fun isOpus(): Boolean {
        return true
    }
}