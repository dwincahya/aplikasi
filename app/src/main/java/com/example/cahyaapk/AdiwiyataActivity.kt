package com.example.cahyaapk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class AdiwiyataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adiwiyata)

        val adiwiyataInfoTextView: TextView = findViewById(R.id.adiwiyata_info)

        val adiwiyataContent = """
            Pengertian Adiwiyata:

            Adiwiyata adalah program dari Kementerian Lingkungan Hidup yang bertujuan untuk menciptakan kesadaran dan tanggung jawab di kalangan warga sekolah terhadap lingkungan hidup yang bersih, hijau, dan sehat. Program ini bertujuan untuk mewujudkan sekolah peduli dan berbudaya lingkungan.

            Kegiatan yang dilakukan saat Adiwiyata:

            1. Menanam pohon dan merawat taman di lingkungan sekolah.
            2. Melakukan pemilahan sampah organik dan anorganik.
            3. Mengurangi penggunaan plastik di lingkungan sekolah.
            4. Mengadakan lomba kebersihan antar kelas.
            5. Sosialisasi mengenai pentingnya menjaga kebersihan dan lingkungan.
            6. Pengolahan sampah menjadi kompos atau barang daur ulang.
        """.trimIndent()

        // Set the text to the TextView
        adiwiyataInfoTextView.text = adiwiyataContent
    }
}
