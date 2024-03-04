package com.example.solutionx

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var button: Button
    lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val locale = Locale.getDefault()
        val language = locale.language

        val typeface = if (language == "ar") {
            // Arabic font
            ResourcesCompat.getFont(applicationContext, R.font.tajwal)
        } else {
            // English font
            ResourcesCompat.getFont(applicationContext, R.font.galano_grotesque)
        }


        button = findViewById<Button>(R.id.changeLanguage).apply {
            typeface?.let { setTypeface(it) }
        }
        text = findViewById<TextView>(R.id.textView).apply {
            typeface?.let { setTypeface(it) }
        }



        val logger = Logger(this)
        logger.log("Activity created")

    }
}


