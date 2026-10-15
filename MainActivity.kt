package com.apex.booster.gfxpro

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.apex.booster.gfxpro.engine.PerformanceEngine
import com.apex.booster.gfxpro.service.GamingOverlayService
import com.apex.booster.gfxpro.ui.DashboardScreen
import com.apex.booster.gfxpro.ui.GfxToolScreen

class MainActivity : ComponentActivity() {

    private lateinit var performanceEngine: PerformanceEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate()
        performanceEngine = PerformanceEngine(this)

        setContent {
            var currentScreen by remember { mutableStateOf("dashboard") }

            when (currentScreen) {
                "dashboard" -> DashboardScreen(
                    onNavigateGfx = { currentScreen = "gfx" },
                    onStartOverlay = {
                        startService(Intent(this, GamingOverlayService::class.java))
                        Toast.makeText(this, "HUD Overlay & Crosshair Aktif!", Toast.LENGTH_SHORT).show()
                    },
                    onCleanMemory = {
                        val freed = performanceEngine.releaseUnusedMemory()
                        Toast.makeText(this, "$freed MB RAM Temizlendi!", Toast.LENGTH_SHORT).show()
                    }
                )
                "gfx" -> GfxToolScreen(
                    onApplyGfx = { config ->
                        Toast.makeText(this, "GFX Ayarları Uygulandı: ${config.targetFps.label}", Toast.LENGTH_LONG).show()
                        currentScreen = "dashboard"
                    }
                )
            }
        }
    }
}
