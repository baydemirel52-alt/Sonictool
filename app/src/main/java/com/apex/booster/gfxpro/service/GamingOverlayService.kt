package com.apex.booster.gfxpro.service

import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.WindowManager
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner

class GamingOverlayService : LifecycleService(), SavedStateRegistryOwner {

    private lateinit var windowManager: WindowManager
    private var hudOverlayView: ComposeView? = null
    private var crosshairOverlayView: ComposeView? = null
    private val savedStateController = SavedStateRegistryController.create(this)

    override val savedStateRegistry: SavedStateRegistry
        get() = savedStateController.savedStateRegistry

    override fun onCreate() {
        super.onCreate()
        savedStateController.performRestore(null)
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        showHudOverlay()
        showCrosshairOverlay()
    }

    private fun showHudOverlay() {
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.END
            x = 32
            y = 120
        }

        hudOverlayView = ComposeView(this).apply {
            setViewTreeLifecycleOwner(this@GamingOverlayService)
            setViewTreeSavedStateRegistryOwner(this@GamingOverlayService)
            setContent {
                ApexHudWidget(fps = 120, temp = "36.2°C", ping = "22ms", ram = "3.8GB")
            }
        }

        windowManager.addView(hudOverlayView, params)
    }

    private fun showCrosshairOverlay() {
        val params = WindowManager.LayoutParams(
            120, 120,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.CENTER
        }

        crosshairOverlayView = ComposeView(this).apply {
            setViewTreeLifecycleOwner(this@GamingOverlayService)
            setViewTreeSavedStateRegistryOwner(this@GamingOverlayService)
            setContent {
                ApexCrosshairWidget(color = Color(0xFF00F0FF), sizeDp = 12)
            }
        }

        windowManager.addView(crosshairOverlayView, params)
    }

    override fun onDestroy() {
        super.onDestroy()
        hudOverlayView?.let { windowManager.removeView(it) }
        crosshairOverlayView?.let { windowManager.removeView(it) }
    }
}

@Composable
fun ApexHudWidget(fps: Int, temp: String, ping: String, ram: String) {
    Box(
        modifier = Modifier
            .background(Color(0xEE090D16), RoundedCornerShape(12.dp))
            .border(1.dp, Color(0xFF00F0FF), RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("⚡ $fps FPS", color = Color(0xFF00FF66), fontSize = 12.sp)
            Text("🔥 $temp", color = Color.White, fontSize = 12.sp)
            Text("📡 $ping", color = Color(0xFF00F0FF), fontSize = 12.sp)
            Text("💾 $ram", color = Color(0xFFA855F7), fontSize = 12.sp)
        }
    }
}

@Composable
fun ApexCrosshairWidget(color: Color, sizeDp: Int) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .size(sizeDp.dp)
                .background(color, CircleShape)
                .border(1.5.dp, Color.Black, CircleShape)
        )
    }
}
