package com.example.crud2023

import android.annotation.SuppressLint
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

class DeleteActivity : AppCompatActivity() {

    private lateinit var deleteBotao: Button
    private lateinit var editDelete: EditText

    private lateinit var databaseReference: DatabaseReference


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_delete)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        deleteBotao = findViewById(R.id.botaoDelete)
        editDelete = findViewById(R.id.editCampoDelete)

        deleteBotao.setOnClickListener {
            val telefone = editDelete.text.toString()
            if (telefone.isNotEmpty()) {
                deleteDados(telefone)
            } else {
                Toast.makeText(this, "Digite o telefone", Toast.LENGTH_SHORT).show()
            }

        }
    }
    private fun deleteDados(telefone: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Usuarios")
        databaseReference.child(telefone).removeValue().addOnSuccessListener {
            editDelete.text.clear()
            Toast.makeText(this, "Dados deletados com sucesso", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Erro ao deletar os dados", Toast.LENGTH_SHORT).show()
        }
    }
}