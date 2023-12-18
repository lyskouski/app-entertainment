package com.tercad.zabauka

class MacosPlatform : Platform {
    override val name: String = "macOS"
}

actual fun getPlatform(): Platform = MacosPlatform()
