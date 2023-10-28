package com.example.firstapp

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class SecondActivity : AppCompatActivity() {
    private lateinit var buttonLlamada: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        buttonLlamada = findViewById(R.id.btn_explicito)
        initEvent()
    }

    private fun initEvent() {
        buttonLlamada.setOnClickListener {
            pedirPermisos()
        }
    }

    private fun call() {
        val llamadaIntent = Intent(Intent.ACTION_CALL).apply {
            data = Uri.parse("tel: 634892831")
        }
        if (llamadaIntent.resolveActivity(packageManager) != null) {
            startActivity(llamadaIntent)
        } else {
            Toast.makeText(this, "Error al iniciar la llamada", Toast.LENGTH_LONG).show()
        }
    }

    private fun permissionPhone(): Boolean = ContextCompat.checkSelfPermission(
        this, android.Manifest.permission.CALL_PHONE
    ) == PackageManager.PERMISSION_GRANTED

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            call()
        } else {
            Toast.makeText(this, "Necesitas habilitar los permisos", Toast.LENGTH_LONG).show()
        }
    }

    private fun pedirPermisos() {
        if (permissionPhone()) {
            call()
        } else {
            requestPermissionLauncher.launch(android.Manifest.permission.CALL_PHONE)
        }
    }
}