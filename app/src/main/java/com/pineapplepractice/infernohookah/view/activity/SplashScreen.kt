package com.pineapplepractice.infernohookah.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.databinding.ActivitySplashScreenBinding
import java.lang.Thread.sleep
import java.util.concurrent.Executors

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Executors.newSingleThreadExecutor().execute {
            val startDelay = 2000L
            sleep(startDelay)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}