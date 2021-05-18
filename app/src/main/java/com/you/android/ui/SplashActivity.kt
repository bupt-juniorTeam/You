package com.you.android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.you.android.R

/**
 * 闪屏页面，应用程序首次启动入口。
 *
 * @author pipixia
 * @since 2021/5/18
 */

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
}