package com.you.android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.you.android.R
import kotlinx.coroutines.Job

/**
 * 闪屏页面，应用程序首次启动入口。
 *
 * @author pipixia
 * @since 2021/5/18
 */

class SplashActivity : AppCompatActivity() {

    private val job by lazy { Job() }

    private  val splashDuration =3000L

    /*
    view的闪屏效果
    */
    private val alphaAnimation by lazy{
        AlphaAnimation(0.5f,1.0f).apply {
            duration=splashDuration
            fillAfter=true
        }
    }

    /*
   view的缩放效果
   */

    private val scaleAnimation by lazy {
        ScaleAnimation(1f, 1.05f, 1f, 1.05f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f).apply {
            duration = splashDuration
            fillAfter = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }


    override fun onDestroy(){
        super.onDestroy()
        job.cancel()
    }
}