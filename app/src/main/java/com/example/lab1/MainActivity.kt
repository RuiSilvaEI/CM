package com.example.lab1


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentAT1: AT1
    private lateinit var fragmentAT2: AT2
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var buttonMain: Button
    private lateinit var imageViewSeat: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicialize o fragmento AT1
        fragmentAT1 = AT1()

        // Inicialize o fragmento AT2
        fragmentAT2 = AT2()

        // Inicialize os botões
        button1 = findViewById<Button>(R.id.button)
        button2 = findViewById<Button>(R.id.button2)
        buttonMain = findViewById<Button>(R.id.buttonMain)

        // Inicialize a ImageView
        imageViewSeat = findViewById<ImageView>(R.id.imageViewSeat)

        // Esconde o botão AT2 e o botão Main inicialmente
        button2.visibility = View.GONE
        buttonMain.visibility = View.GONE

        // Configura o listener de clique para o primeiro botão (AT1)
        button1.setOnClickListener {
            // Esconde a ImageView
            imageViewSeat.visibility = View.GONE

            // Substitui o conteúdo do contêiner de fragmentos pelo fragmento AT1
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragmentAT1)
                .commit()

            // Esconde o botão AT1 e mostra o botão AT2
            button1.visibility = View.GONE
            button2.visibility = View.VISIBLE
        }

        // Configura o listener de clique para o segundo botão (AT2)
        button2.setOnClickListener {
            // Substitui o conteúdo do contêiner de fragmentos pelo fragmento AT2
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragmentAT2)
                .commit()

            // Mostra a ImageView e esconde o botão AT2
            imageViewSeat.visibility = View.GONE
            button2.visibility = View.GONE
            buttonMain.visibility = View.VISIBLE
        }

        // Configura o listener de clique para o botão Main
        buttonMain.setOnClickListener {
            // Crie uma Intent para iniciar a primeira atividade do seu aplicativo
            val intent = Intent(this, MainActivity::class.java)

            // Limpa o back stack, garantindo que a PrimeiraActivity seja a única atividade na pilha
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            // Inicia a atividade
            startActivity(intent)
        }
    }
}
