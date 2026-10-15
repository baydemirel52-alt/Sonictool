package com.apex.booster.gfxpro.engine

enum class DnsServer(val label: String, val primary: String, val secondary: String) {
    CLOUDFLARE_WARP("Cloudflare Gaming (1.1.1.1)", "1.1.1.1", "1.0.0.1"),
    QUAD9_SECURITY("Quad9 Low Latency", "9.9.9.9", "149.112.112.112"),
    GOOGLE_PUBLIC("Google DNS", "8.8.8.8", "8.8.4.4"),
    ADGUARD_GAMING("AdGuard DNS", "94.140.14.14", "94.140.15.15")
}

class GamingDnsOptimizer {

    fun applyGamingDns(dns: DnsServer): String {
        return ShizukuShellService.executeShizukuCommand(
            "settings put global private_dns_specifier ${dns.primary}"
        )
    }

    fun measurePing(host: String = "1.1.1.1"): Int {
        return try {
            val startTime = System.currentTimeMillis()
            val process = Runtime.getRuntime().exec("ping -c 1 -w 1 $host")
            val exitCode = process.waitFor()
            val endTime = System.currentTimeMillis()
            if (exitCode == 0) (endTime - startTime).toInt() else 999
        } catch (e: Exception) {
            999
        }
    }
}
