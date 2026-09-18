package br.ulbra.atividades

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    // Placar
    var vitorias = 0
    var derrotas = 0
    var empates = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    // Jogador escolheu pedra
    fun escolherPedra(view: View) {
        jogar("pedra")
    }

    // Jogador escolheu papel
    fun escolherPapel(view: View) {
        jogar("papel")
    }

    // Jogador escolheu tesoura
    fun escolherTesoura(view: View) {
        jogar("tesoura")
    }

    // Realiza a partida
    fun jogar(escolhaJogador: String) {

        // Opções possíveis
        val opcoes = arrayOf("pedra", "papel", "tesoura")

        // Escolha aleatória do computador
        val escolhaOponente = opcoes[Random.nextInt(opcoes.size)]

        // Mostra a imagem escolhida pelo oponente
        val imgOponente = findViewById<ImageView>(R.id.imgOponente)

        when (escolhaOponente) {

            "pedra" -> imgOponente.setImageResource(R.drawable.pedra)

            "papel" -> imgOponente.setImageResource(R.drawable.papel)

            "tesoura" -> imgOponente.setImageResource(R.drawable.tesoura)
        }

        // Verifica quem ganhou
        val resultado = verificarGanhador(
            escolhaJogador,
            escolhaOponente
        )

        // TextView do resultado
        val txtResultado = findViewById<TextView>(R.id.txtResultado)

        when (resultado) {

            "vitoria" -> {
                vitorias++
                txtResultado.text = "Você ganhou! 🏆"
            }

            "derrota" -> {
                derrotas++
                txtResultado.text = "Você perdeu!"
            }

            "empate" -> {
                empates++
                txtResultado.text = "Empate!"
            }
        }

        // Atualiza o placar
        atualizarPlacar()
    }

    // Verifica o vencedor
    fun verificarGanhador(
        jogador: String,
        oponente: String
    ): String {

        // Empate
        if (jogador.equals(oponente)) {
            return "empate"
        }

        // Vitória do jogador
        if (
            (jogador.equals("pedra") && oponente.equals("tesoura")) ||
            (jogador.equals("papel") && oponente.equals("pedra")) ||
            (jogador.equals("tesoura") && oponente.equals("papel"))
        ) {
            return "vitoria"
        }

        // Caso contrário, derrota
        return "derrota"
    }

    // Atualiza o placar na tela
    fun atualizarPlacar() {

        val txtVitorias = findViewById<TextView>(R.id.txtVitorias)
        val txtDerrotas = findViewById<TextView>(R.id.txtDerrotas)
        val txtEmpates = findViewById<TextView>(R.id.txtEmpates)

        txtVitorias.text = "Vitórias: $vitorias"
        txtDerrotas.text = "Derrotas: $derrotas"
        txtEmpates.text = "Empates: $empates"
    }
}