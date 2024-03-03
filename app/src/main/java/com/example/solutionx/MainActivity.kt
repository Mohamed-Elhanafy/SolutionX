package com.example.solutionx

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var button:Button
    lateinit var text : TextView

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


      /*  ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

    }
}