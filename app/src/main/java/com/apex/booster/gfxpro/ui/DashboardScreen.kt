package com.apex.booster.gfxpro.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DashboardScreen(
    onNavigateGfx: () -> Unit,
    onStartOverlay: () -> Unit,
    onCleanMemory: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF090D16))
            .padding(16.dp)
    ) {
        Text(
            "APEX BOOST & GFX PRO",
            color = Color(0xFF00F0FF),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            "Android 16 & 17 Ultra Gaming Suite",
            color = Color.Gray,
            fontSize = 13.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFF131C2E)),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Cihaz Statüsü", color = Color.White, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("RAM Temizlik", color = Color.Gray)
                    Text("Optimal", color = Color(0xFF00FF66))
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Termal Headroom", color = Color.Gray)
                    Text("35.4°C (Cool)", color = Color(0xFF00F0FF))
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Shizuku Servisi", color = Color.Gray)
                    Text("Aktif (Non-Root Shell)", color = Color(0xFFA855F7))
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onNavigateGfx,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00F0FF)),
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("GFX TOOL AYARLARI (120/144 FPS)", color = Color.Black, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onStartOverlay,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E293B)),
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("CANLI OYUN HUD OVERLAY & NİŞANGAH BAŞLAT 🎯", color = Color.White, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onCleanMemory,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF22C55E)),
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("SÜPER RAM & CGROUP CLEANER 🚀", color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}
