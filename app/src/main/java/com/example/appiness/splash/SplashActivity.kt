package com.example.appiness.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.appiness.MainActivity
import com.example.appiness.R


/**
 * splash activity for the app this will appear only for 3 seconds by using handler.
 */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler = Handler()
        handler.postDelayed(
            object : Runnable {
                override fun run() {
                    startHomePage()
                }
            },
            3000
        )
    }

    /**
     * This method is sued to open the main activity
     */
    private fun startHomePage() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}