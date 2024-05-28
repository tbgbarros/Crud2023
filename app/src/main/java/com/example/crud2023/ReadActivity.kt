package com.example.crud2023

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReadActivity : AppCompatActivity() {

    private lateinit var editTelefone:EditText
    private lateinit var campo1:TextView
    private lateinit var campo2:TextView
    private lateinit var readBotao:Button

    private lateinit var databaseReference: DatabaseReference
    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_read)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editTelefone = findViewById(R.id.editCampoDelete)
        campo1 = findViewById(R.id.campo1)
        campo2 = findViewById(R.id.campo2)
        readBotao = findViewById(R.id.botaoRead)

        readBotao.setOnClickListener {
            val editPesquisa: String = editTelefone.text.toString()
            if (editPesquisa.isNotEmpty()) {
                readData(editPesquisa)
            } else {
                Toast.makeText(this, "Digite um telefone", Toast.LENGTH_SHORT).show()
            }
        }
    }
        private fun readData(telefone:String){
            databaseReference = FirebaseDatabase.getInstance().getReference("Usuarios")
            databaseReference.child(telefone).get().addOnSuccessListener {
                if(it.exists()){
                    val nome = it.child("name").value
                    val email = it.child("email").value

                    Toast.makeText(this, "Dados lidos com sucesso", Toast.LENGTH_SHORT).show()
                    editTelefone.text.clear()
                    campo1.text = nome.toString()
                    campo2.text = email.toString()
                }else{
                    Toast.makeText(this, "Dados não encontrados", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener{
                Toast.makeText(this, "Erro ao ler dados do Banco", Toast.LENGTH_SHORT).show()
            }
        }
}