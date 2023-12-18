package com.tercad.zabauka

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform