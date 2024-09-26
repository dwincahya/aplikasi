package com.example.cahyaapk

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonGoToNext: ImageView = findViewById(R.id.buttonGoToNext)
        buttonGoToNext.setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
        val buttonGoToProfile: ImageView = findViewById(R.id.buttonGoToMaps)
        buttonGoToProfile.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://maps.app.goo.gl/dYZNxqkmn51i7Nh18")
            startActivity(intent)
        }
        val buttonScanQR: ImageView = findViewById(R.id.buttonGoToQr)
        buttonScanQR.setOnClickListener {
            val intent = Intent(this, ScanActivity::class.java)
            startActivity(intent)  // Mulai ScanActivity
        }
        val buttonGoToAdiwiyata: ImageView = findViewById(R.id.buttonGoToAdiwiyata)
        buttonGoToAdiwiyata.setOnClickListener {
            val intent = Intent(this, AdiwiyataActivity::class.java)
            startActivity(intent)
        }

    }
}