package com.apex.booster.gfxpro.engine

import android.os.IBinder
import rikka.shizuku.Shizuku
import java.io.BufferedReader
import java.io.InputStreamReader

object ShizukuShellService {

    fun isShizukuAvailable(): Boolean {
        return try {
            Shizuku.pingBinder() && Shizuku.checkSelfPermission() == android.content.pm.PackageManager.PERMISSION_GRANTED
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Executes shell commands as ADB System User without Root
     */
    fun executeShizukuCommand(cmd: String): String {
        if (!isShizukuAvailable()) return "Shizuku Service Not Connected"

        return try {
            val process = Shizuku.newProcess(arrayOf("sh", "-c", cmd), null, null)
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            val output = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                output.append(line).append("\n")
            }
            process.waitFor()
            output.toString().trim()
        } catch (e: Exception) {
            "Error: ${e.localizedMessage}"
        }
    }

    /**
     * Sets custom display resolution via ADB wm size
     */
    fun setDisplayResolution(width: Int, height: Int) {
        executeShizukuCommand("wm size ${width}x${height}")
    }

    /**
     * Resets display resolution
     */
    fun resetDisplayResolution() {
        executeShizukuCommand("wm size reset")
    }

    /**
     * Enables Game Mode Intervention for Target App
     */
    fun setGameModeIntervention(packageName: String, mode: Int) {
        executeShizukuCommand("cmd game mode $mode $packageName")
    }
}
