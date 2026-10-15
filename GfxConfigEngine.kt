package com.apex.booster.gfxpro.engine

import com.apex.booster.gfxpro.model.*
import java.io.File

class GfxConfigEngine {

    /**
     * Unreal Engine 4 / 5 (PUBG, BGMI, COD Mobile, Fortnite) Config File Patcher
     */
    fun applyGfxPatcher(gameConfigDirectory: File, config: GfxConfig): Boolean {
        return try {
            if (!gameConfigDirectory.exists()) {
                gameConfigDirectory.mkdirs()
            }

            val iniFile = File(gameConfigDirectory, "UserCustom.ini")
            val patchedContent = StringBuilder()

            patchedContent.append("[CustomDeviceProfile]\n")
            patchedContent.append("+CVars=r.PUBGDeviceFPSGroup=${config.targetFps.fpsValue}\n")
            patchedContent.append("+CVars=r.PUBGQualityLevel=${mapQualityToLevel(config.graphicsQuality)}\n")
            patchedContent.append("+CVars=r.MobileContentScaleFactor=${calculateScaleFactor(config.resolution)}\n")
            patchedContent.append("+CVars=r.MSAAQual=${config.msaa.value}\n")
            patchedContent.append("+CVars=r.DefaultFeature.Bloom=${if (config.graphicsQuality == GraphicsQuality.ULTRA_HD) 1 else 0}\n")
            patchedContent.append("+CVars=r.ShadowQuality=${if (config.shadowsEnabled) 2 else 0}\n")
            patchedContent.append("+CVars=r.RHICmdBypass=${if (config.hardwareAcceleration) 1 else 0}\n")
            patchedContent.append("+CVars=r.Vulkan.Enable=${if (config.api == GraphicsApi.VULKAN) 1 else 0}\n")
            patchedContent.append("+CVars=a.SoundQualityLevel=${if (config.ultraAudioEnabled) 2 else 1}\n")

            iniFile.writeText(patchedContent.toString())
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    private fun mapQualityToLevel(quality: GraphicsQuality): Int {
        return when (quality) {
            GraphicsQuality.SMOOTH -> 1
            GraphicsQuality.BALANCED -> 2
            GraphicsQuality.HD -> 3
            GraphicsQuality.HDR -> 4
            GraphicsQuality.ULTRA_HD -> 5
        }
    }

    private fun calculateScaleFactor(resolution: ResolutionOption): Float {
        return when (resolution) {
            ResolutionOption.RES_720P -> 0.75f
            ResolutionOption.RES_1080P -> 1.0f
            ResolutionOption.RES_2K -> 1.33f
            ResolutionOption.RES_4K -> 2.0f
            ResolutionOption.RES_IPAD_VIEW -> 1.15f
        }
    }
}
