package com.apex.booster.gfxpro.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apex.booster.gfxpro.model.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GfxToolScreen(onApplyGfx: (GfxConfig) -> Unit) {
    var config by remember { mutableStateOf(GfxConfig()) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF090D16))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                "ADVANCED GFX TOOL ENGINE",
                color = Color(0xFF00F0FF),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Sıfır gecikme, 120/144 FPS kilit açıcı ve iPad görünümü.",
                color = Color.Gray,
                fontSize = 13.sp
            )
        }

        item {
            GfxCardSection("Çözünürlük & Ekran Görünümü") {
                ResolutionOption.values().forEach { res ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(res.label, color = Color.White)
                        RadioButton(
                            selected = config.resolution == res,
                            onClick = { config = config.copy(resolution = res) }
                        )
                    }
                }
            }
        }

        item {
            GfxCardSection("Kare Hızı (FPS Unlocker)") {
                FpsOption.values().forEach { fps ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(fps.label, color = Color(0xFF00FF66), fontWeight = FontWeight.Bold)
                        RadioButton(
                            selected = config.targetFps == fps,
                            onClick = { config = config.copy(targetFps = fps) }
                        )
                    }
                }
            }
        }

        item {
            GfxCardSection("Grafik Sürücüsü & API") {
                GraphicsApi.values().forEach { api ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(api.label, color = Color.White)
                        RadioButton(
                            selected = config.api == api,
                            onClick = { config = config.copy(api = api) }
                        )
                    }
                }
            }
        }

        item {
            Button(
                onClick = { onApplyGfx(config) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00F0FF)),
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("GFX AYARLARINI OYUNA UYGULA ⚡", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun GfxCardSection(title: String, content: @Composable ColumnScope.() -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF131C2E)),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, color = Color(0xFF00F0FF), fontWeight = FontWeight.Bold, fontSize = 15.sp)
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    }
}
