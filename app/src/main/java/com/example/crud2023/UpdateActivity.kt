package com.example.crud2023

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

class UpdateActivity : AppCompatActivity() {
    private lateinit var editUpdateTelefone: EditText
    private lateinit var editUpdateNome: EditText
    private lateinit var editUpdateEmail: EditText
    private lateinit var editBotaoAtualizar: Button

    private lateinit var databaseReference: DatabaseReference

    @Suppress("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        editUpdateTelefone = findViewById(R.id.editUpdateTelefone)
        editUpdateNome = findViewById(R.id.editUpdateNome)
        editUpdateEmail = findViewById(R.id.editUpdateEmail)
        editBotaoAtualizar = findViewById(R.id.botaoAtualizar)

        editBotaoAtualizar.setOnClickListener {
            val updateTelefone = editUpdateTelefone.text.toString()
            val updateNome = editUpdateNome.text.toString()
            val updateEmail = editUpdateEmail.text.toString()

            if (updateTelefone.isNotEmpty() && updateNome.isNotEmpty() && updateEmail.isNotEmpty()) {
                updateData(updateTelefone, updateNome, updateEmail)
            }
        }

    }
    private fun updateData(telefone: String, nome: String, email: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("users")
        val user = mapOf(
            "telefone" to telefone,
            "nome" to nome,
            "email" to email
        )

        databaseReference.child(telefone).updateChildren(user).addOnSuccessListener {
            editUpdateTelefone.text.clear()
            editUpdateNome.text.clear()
            editUpdateEmail.text.clear()
            editUpdateTelefone.requestFocus()

        Toast.makeText(this, "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Erro ao atualizar os dados", Toast.LENGTH_SHORT).show()
        }
    }


}