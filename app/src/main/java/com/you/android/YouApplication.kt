package com.you.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

// 方便全局得到context
class YouApplication: Application() {
    companion object {
        // 一些其他的全局配置也可以写在这
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}