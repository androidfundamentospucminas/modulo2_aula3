package com.walker.modulo2aula3androidfundamentos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val campoDeTexto = findViewById<EditText>(R.id.campoDeTexto)
        val botaoProcurar = findViewById<Button>(R.id.botaoProcurar)
        val barraDeProgresso = findViewById<ProgressBar>(R.id.barraProgresso)
        val containerDaPesquisa = findViewById<ScrollView>(R.id.containerDaPesquisa)
        val imagemDoResultado = findViewById<ImageView>(R.id.imagemDoResultado)
        val tituloDoResultado = findViewById<TextView>(R.id.tituloDoResultado)
        val descricaoDoResultado = findViewById<TextView>(R.id.descricaoDoResultado)

        botaoProcurar.setOnClickListener {
            lifecycleScope.launch {
                val valorPesquisado = campoDeTexto.text

                Toast.makeText(
                    this@MainActivity,
                    "Valor do campo de texto: ${valorPesquisado}",
                    Toast.LENGTH_SHORT).show()

                // Iniciando a pesquisa
                campoDeTexto.isEnabled = false
                barraDeProgresso.isVisible = true
                botaoProcurar.isVisible = false
                containerDaPesquisa.isVisible = false
                tituloDoResultado.text = ""
                descricaoDoResultado.text = ""
                imagemDoResultado.setImageDrawable(ContextCompat.getDrawable(this@MainActivity, R.mipmap.ic_launcher))

                // Pesquisando e alocando o resultado
                if (valorPesquisado.toString() == "lagarto") {
                        val result = consultarLagarto()
                        containerDaPesquisa.isVisible = true
                        tituloDoResultado.text = result.titulo
                        descricaoDoResultado.text = result.descricao
                        result.imagem?.let {
                            imagemDoResultado.setImageDrawable(it)
                        }
                }

                // Volta os campos ao normal
                campoDeTexto.isEnabled = true
                barraDeProgresso.isVisible = false
                botaoProcurar.isVisible = true
            }
        }
    }

    suspend fun consultarLagarto(): InsetoModel {
        delay(1500)
        return InsetoModel(
            "Inseto Lagarto",
            "Uma lagarta é a fase larval dos insetos da ordem Lepidoptera, que inclui borboletas e mariposas. Elas são conhecidas por sua aparência rastejante e corpo alongado, geralmente com várias pernas segmentadas. As lagartas são uma parte crucial do ciclo de vida das borboletas e mariposas, pois é durante essa fase que ocorre o crescimento e desenvolvimento antes da metamorfose. As lagartas variam muito em aparência, tamanho e cores. Algumas são pequenas e discretas, enquanto outras são grandes, coloridas e chamativas, muitas vezes com padrões e pelos protuberantes. Suas cores e padrões podem ser adaptativos, fornecendo camuflagem ou advertindo predadores de sua toxicidade. As lagartas têm a capacidade de se alimentar intensamente para obter energia e nutrientes necessários para seu crescimento rápido. Elas possuem mandíbulas fortes que lhes permitem mastigar e comer folhas, flores e outros materiais vegetais. Algumas lagartas são especializadas em se alimentar de plantas específicas, enquanto outras têm uma dieta mais ampla. À medida que uma lagarta cresce, ela passa por várias mudas, descartando sua pele externa e revelando uma nova pele que lhe permite continuar a crescer. Após atingir um estágio de desenvolvimento completo, as lagartas passam por uma incrível transformação conhecida como metamorfose. Elas formam um casulo ou crisálida, onde ocorrem mudanças internas surpreendentes. Dentro do casulo, a lagarta sofre uma reorganização completa de seu corpo, transformando-se em uma borboleta ou mariposa adulta. A metamorfose é um processo complexo em que a lagarta se dissolve parcialmente em um líquido nutritivo e reconstrói seu corpo, emergindo como uma criatura completamente diferente. As asas se formam e endurecem, e o sistema digestivo e outros órgãos são modificados para atender às necessidades do estágio adulto. As lagartas são fascinantes em sua diversidade e no papel vital que desempenham no ciclo de vida dos insetos alados. Embora muitas vezes sejam vistos como simples criaturas rastejantes, elas são uma maravilhosa manifestação da natureza e do poder da transformação. Observar uma lagarta se transformar em uma bela borboleta é um lembrete inspirador da incrível diversidade e potencial de vida em nosso planeta." ,
            ContextCompat.getDrawable(this, R.mipmap.lagarto)
        )
    }
}