package com.tercad.zabauka

class MsiPlatform : Platform {
    override val name: String = "Windows"
}

actual fun getPlatform(): Platform = MsiPlatform()
