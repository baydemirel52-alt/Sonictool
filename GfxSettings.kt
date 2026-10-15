package com.apex.booster.gfxpro.model

enum class ResolutionOption(val label: String, val width: Int, val height: Int) {
    RES_720P("720p (HD)", 1280, 720),
    RES_1080P("1080p (FHD)", 1920, 1080),
    RES_2K("2K (QHD)", 2560, 1440),
    RES_4K("4K (UHD)", 3840, 2160),
    RES_IPAD_VIEW("iPad View (4:3 Aspect)", 1920, 1440)
}

enum class FpsOption(val label: String, val fpsValue: Int) {
    FPS_60("60 FPS (Ultra)", 60),
    FPS_90("90 FPS (Extreme)", 90),
    FPS_120("120 FPS (Extreme+ / Apex)", 120),
    FPS_144("144 FPS (Pro Display)", 144)
}

enum class GraphicsQuality(val label: String, val configValue: String) {
    SMOOTH("Smooth (Best Performance)", "Smooth"),
    BALANCED("Balanced", "Balanced"),
    HD("HD Graphics", "HD"),
    HDR("HDR (High Dynamic Range)", "HDR"),
    ULTRA_HD("Ultra HD (Max Visuals)", "UltraHD")
}

enum class MsaaOption(val label: String, val value: Int) {
    OFF("Disabled", 0),
    X2("2x MSAA", 2),
    X4("4x MSAA (High Quality)", 4)
}

enum class GraphicsApi(val label: String, val driverName: String) {
    VULKAN("Vulkan API (Recommended for Android 16/17)", "Vulkan"),
    OPENGL_ES3("OpenGL ES 3.2", "OpenGLES")
}

enum class ColorStyle(val label: String, val styleId: Int) {
    CLASSIC("Classic", 1),
    COLORFUL("Colorful (Vibrant)", 2),
    REALISTIC("Realistic", 3),
    SOFT("Soft", 4),
    MOVIE("Movie", 5)
}

data class GfxConfig(
    val resolution: ResolutionOption = ResolutionOption.RES_1080P,
    val targetFps: FpsOption = FpsOption.FPS_120,
    val graphicsQuality: GraphicsQuality = GraphicsQuality.SMOOTH,
    val msaa: MsaaOption = MsaaOption.OFF,
    val api: GraphicsApi = GraphicsApi.VULKAN,
    val colorStyle: ColorStyle = ColorStyle.COLORFUL,
    val shadowsEnabled: Boolean = false,
    val ultraAudioEnabled: Boolean = true,
    val zeroLagMode: Boolean = true,
    val memoryBoost: Boolean = true,
    val hardwareAcceleration: Boolean = true
)
