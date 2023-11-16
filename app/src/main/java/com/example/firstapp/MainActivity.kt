package com.example.firstapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var buttonLlamada: Button
    private lateinit var buttonAlarma: Button
    private lateinit var buttonUrl: Button
    private lateinit var buttonCorreo: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.constraint_activity)

        buttonLlamada = findViewById(R.id.btn_llamada)
        buttonAlarma = findViewById(R.id.btn_alarma)
        buttonUrl = findViewById(R.id.btn_url)
        buttonCorreo = findViewById(R.id.btn_correo)
        initEvent()
    }

    private fun initEvent() {
        buttonLlamada.setOnClickListener {
            val intentLlamada = Intent(this, SecondActivity::class.java)
            startActivity(intentLlamada)
        }
        buttonAlarma.setOnClickListener {
            createAlarm("Alarma", 21, 21)
        }
        buttonUrl.setOnClickListener {
            crearWebsite()
        }
        buttonCorreo.setOnClickListener {
            crearCorreo()
        }
    }

    private fun crearCorreo() {
        val correo = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, "josemagomez53@gmail.com")
            putExtra(Intent.EXTRA_SUBJECT, "")
        }
        if (correo.resolveActivity(packageManager) != null) {
            startActivity(correo)
        }
    }

    private fun crearWebsite() {
        val url = "https://developer.android.com/kotlin?hl=es-419"
        val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(urlIntent)
    }

    private fun createAlarm(message: String, hour: Int, minutes: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minutes)
        }
        startActivity(intent)
        /*if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "Error, no puedo ejecutar la alarma", Toast.LENGTH_LONG).show()
        }*/
    }
}