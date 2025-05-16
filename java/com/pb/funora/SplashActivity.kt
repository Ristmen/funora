package com.pb.funora

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.pb.funora.auth.ui.AuthActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val spbLogo = findViewById<TextView>(R.id.spb_logo)

        // Animasyon: Fade In
        val fadeIn = ObjectAnimator.ofFloat(spbLogo, "alpha", 0f, 1f).apply {
            duration = 1000
        }

        fadeIn.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                navigateNext()
            }
        })

        fadeIn.start()
    }

    private fun navigateNext() {
        val user = FirebaseAuth.getInstance().currentUser
        val nextActivity = if (user != null) MainActivity::class.java else AuthActivity::class.java
        startActivity(Intent(this, nextActivity))
        finish()
    }
}
