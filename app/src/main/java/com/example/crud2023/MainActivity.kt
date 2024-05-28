package com.example.crud2023

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Importar a UploadActivity
import com.example.crud2023.UploadActivity

class MainActivity : AppCompatActivity() {

    private lateinit var botaoUpload: Button
    private lateinit var botaoRead: Button
    private lateinit var botaoUpdate: Button
    private lateinit var botaoDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        botaoUpload = findViewById(R.id.uploadButton)
        botaoUpload.setOnClickListener {
            val intent = Intent(this, UploadActivity::class.java)
            startActivity(intent)
        }
        // configurar botao read
        botaoRead = findViewById(R.id.btnRead)
        botaoRead.setOnClickListener {
            val intent = Intent(this, ReadActivity::class.java)
            startActivity(intent)
        }
        // configurar botao update
        botaoUpdate = findViewById(R.id.btnUpdate)
        botaoUpdate.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java)
            startActivity(intent)
        }
        // configurar botao delete
        botaoDelete = findViewById(R.id.btnDelete)
        botaoDelete.setOnClickListener {
            val intent = Intent(this, DeleteActivity::class.java)
            startActivity(intent)

        }
    }
}
