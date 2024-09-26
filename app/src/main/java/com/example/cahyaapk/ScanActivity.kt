package com.example.cahyaapk

import android.content.Intent
import android.os.Bundle
import android.text.util.Linkify
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import android.widget.Toast

class ScanActivity : AppCompatActivity() {

    private lateinit var scanResultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        scanResultTextView = findViewById(R.id.scan_result)

        startQRScanner()
    }

    private fun startQRScanner() {
        val intentIntegrator = IntentIntegrator(this)
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        intentIntegrator.setPrompt("Scan a QR code")
        intentIntegrator.setCameraId(0)
        intentIntegrator.setBeepEnabled(true)
        intentIntegrator.setOrientationLocked(true)
        intentIntegrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result: IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Scan cancelled", Toast.LENGTH_LONG).show()
                scanResultTextView.text = "Scan dibatalkan"
            } else {
                val scannedText = result.contents

                if (android.util.Patterns.WEB_URL.matcher(scannedText).matches()) {
                    scanResultTextView.text = scannedText
                    Linkify.addLinks(scanResultTextView, Linkify.WEB_URLS)
                } else {
                    scanResultTextView.text = "Hasil Scan: $scannedText"
                }

                Toast.makeText(this, "Scanned: $scannedText", Toast.LENGTH_LONG).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
