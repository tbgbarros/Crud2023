package com.example.crud2023

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {

    private lateinit var editNome: EditText
    private lateinit var editTelefone: EditText
    private lateinit var editEmail: EditText
    private lateinit var botaoUpload: Button
    private lateinit var databaseReference: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_upload)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editNome = findViewById(R.id.editUpdateNome)
        editTelefone = findViewById(R.id.editUpdateTelefone)
        editEmail = findViewById(R.id.editUpdateEmail)
        botaoUpload = findViewById(R.id.btnSalvar)

        botaoUpload.setOnClickListener {
            val nome = editNome.text.toString()
            val telefone = editTelefone.text.toString()
            val email = editEmail.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("Usuarios")
            val users = UserData(nome, telefone, email)
            databaseReference.child(telefone).setValue(users).addOnSuccessListener {
                editNome.text.clear()
                editTelefone.text.clear()
                editEmail.text.clear()

                Toast.makeText(this, "Dados enviados com sucesso!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, "Erro ao enviar dados!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
