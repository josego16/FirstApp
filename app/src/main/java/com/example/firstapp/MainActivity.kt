package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var buttonLlamada: ImageButton
    private lateinit var buttonAlarma: ImageButton
    private lateinit var buttonUrl: ImageButton
    private lateinit var buttonImcCalc: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonLlamada = findViewById(R.id.btn_llamada)
        buttonAlarma = findViewById(R.id.btn_alarma)
        buttonUrl = findViewById(R.id.btn_url)
        buttonImcCalc = findViewById(R.id.btn_calcImc)
        initEvent()
    }

    private fun initEvent() {
        buttonLlamada.setOnClickListener {
            val intentLlamada = Intent(this, SecondActivity::class.java)
            startActivity(intentLlamada)
        }
        buttonAlarma.setOnClickListener {
            crearAlarma("Alarma", 21, 21)
        }
    }

    private fun crearAlarma(message: String, hour: Int, minutes: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minutes)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}