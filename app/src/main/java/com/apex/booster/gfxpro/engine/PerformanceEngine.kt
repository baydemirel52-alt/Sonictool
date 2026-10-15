package com.apex.booster.gfxpro.engine

import android.content.Context
import android.os.Build
import android.os.PowerManager
import androidx.annotation.RequiresApi
import androidx.metrics.performance.JankStats
import java.io.File

class PerformanceEngine(private val context: Context) {

    private val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
    private var jankStats: JankStats? = null

    /**
     * Android 16/17 ADPF (Android Dynamic Performance Framework) Thermal Headroom Manager
     */
    fun getThermalStatus(): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val headroom = powerManager.getThermalHeadroom(10)
            return when {
                headroom < 0.7f -> "COOL (30-36°C)"
                headroom < 0.9f -> "OPTIMAL (37-40°C)"
                else -> "THERMAL WARNING (>42°C)"
            }
        }
        return "NORMAL"
    }

    /**
     * Tracks frame rendering jank and dropouts
     */
    fun setupJankStats(window: android.view.Window, onFrameReport: (Long, Boolean) -> Unit) {
        jankStats = JankStats.createAndTrack(window) { frameData ->
            onFrameReport(frameData.frameDurationUiNanos / 1_000_000, frameData.isJank)
        }
        jankStats?.isTrackingEnabled = true
    }

    /**
     * Cleans background processes and cached memory
     */
    fun releaseUnusedMemory(): Long {
        val runtime = Runtime.getRuntime()
        val beforeMem = runtime.totalMemory() - runtime.freeMemory()
        System.gc()
        val afterMem = runtime.totalMemory() - runtime.freeMemory()
        return (beforeMem - afterMem) / (1024 * 1024)
    }
}
