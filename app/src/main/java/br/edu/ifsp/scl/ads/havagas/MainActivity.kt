package br.edu.ifsp.scl.ads.havagas

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import br.edu.ifsp.scl.ads.havagas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var adicionarCelularEstaAtivo: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(activityMainBinding.root)

        with (activityMainBinding) {
            adicionarCelularEfab.setOnClickListener {
                if (celularLl.visibility == View.GONE) {
                    celularLl.visibility = View.VISIBLE
                } else {
                    celularLl.visibility = View.GONE
                    celularEt.setText("")
                }
            }

            fundamentalCb.setOnClickListener {
                if (fundamentalLl.visibility == View.GONE)
                    fundamentalLl.visibility = View.VISIBLE
                else {
                    fundamentalLl.visibility = View.GONE
                    instituicaoFundamentalEt.setText("")
                }
            }

            medioCb.setOnClickListener {
                if (medioLl.visibility == View.GONE)
                    medioLl.visibility = View.VISIBLE
                else {
                    medioLl.visibility = View.GONE
                    instituicaoMedioEt.setText("")
                }
            }

            graduacaoCb.setOnClickListener {
                if (graduacaoLl.visibility == View.GONE)
                    graduacaoLl.visibility = View.VISIBLE
                else {
                    graduacaoLl.visibility = View.GONE
                    instituicaoGraduacaoEt.setText("")
                    conclusaoGraduacaoEt.setText("")
                }
            }

            especializacaoCb.setOnClickListener {
                if (especializacaoLl.visibility == View.GONE)
                    especializacaoLl.visibility = View.VISIBLE
                else {
                    especializacaoLl.visibility = View.GONE
                    instituicaoEspecializacaoEt.setText("")
                    conclusaoEspecializacaoEt.setText("")
                }
            }

            mestradoCb.setOnClickListener {
                if (mestradoLl.visibility == View.GONE)
                    mestradoLl.visibility = View.VISIBLE
                else {
                    mestradoLl.visibility = View.GONE
                    instituicaoMestradoEt.setText("")
                    conclusaoMestradoEt.setText("")
                    monografiaMestradoEt.setText("")
                    orientadorMestradoEt.setText("")
                }
            }

            doutoradoCb.setOnClickListener {
                if (doutoradoLl.visibility == View.GONE)
                    doutoradoLl.visibility = View.VISIBLE
                else {
                    doutoradoLl.visibility = View.GONE
                    instituicaoDoutoradoEt.setText("")
                    conclusaoDoutoradoEt.setText("")
                    monografiaDoutradoEt.setText("")
                    orientadorDoutoradoEt.setText("")
                }
            }

            salvarBt.setOnClickListener {
                val camposPreenchidos : String = retornaCamposPreenchidos()
                Toast.makeText(this@MainActivity, camposPreenchidos, Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun retornaCamposPreenchidos() : String {
        var texto : String
        val campos: MutableList<String> = mutableListOf()

        with (activityMainBinding) {
            if (nomeEt.text.isNotEmpty())
                campos.add("Nome: ${nomeEt.text}")
            if (emailEt.text.isNotEmpty())
                campos.add("Email: ${emailEt.text}")
            if (emailAtualizacaoSw.isChecked)
                campos.add("Receber atualização de oportunidades: Sim")
            if (telefoneEt.text.isNotEmpty())
                campos.add("Telefone: ${telefoneEt.text}")
            if (comercialRb.isChecked)
                campos.add("Tipo de telefone: ${comercialRb.text}")
            if (residencialRb.isChecked)
                campos.add("Tipo de telefone: ${residencialRb.text}")
            if (celularLl.isVisible) {
                if (celularEt.text.isNotEmpty())
                    campos.add("Celular: ${celularEt.text}")
            }
            val sexo: String
            if (masculinoRb.isChecked)
                sexo = masculinoRb.text.toString()
            else
                sexo = femininoRb.text.toString()
            campos.add("Sexo: ${sexo}")
            if (dataNascimentoEt.text.isNotEmpty())
                campos.add("Data de Nascimento: ${dataNascimentoEt.text}")

            var formacao : String = ""
            var informacoesFormacao : MutableList<String> = mutableListOf()
            if (fundamentalCb.isChecked) {
                formacao += "Fundamental"
                if (instituicaoFundamentalEt.text.isNotEmpty())
                    informacoesFormacao.add("Instituição: ${instituicaoFundamentalEt.text}")
                if (informacoesFormacao.isNotEmpty())
                    formacao += " (${informacoesFormacao.joinToString()})"
                formacao += "; "
                informacoesFormacao.clear()
            }

            if (medioCb.isChecked) {
                formacao += "Médio"
                if (instituicaoMedioEt.text.isNotEmpty())
                    formacao += "Instituição: ${instituicaoMedioEt.text}"

                if (informacoesFormacao.isNotEmpty())
                    formacao += " (${informacoesFormacao.joinToString()})"
                informacoesFormacao.clear()
                formacao += "; "
            }

            if (graduacaoCb.isChecked) {
                formacao += "Graduação"
                if (conclusaoGraduacaoEt.text.isNotEmpty())
                    informacoesFormacao.add("Ano de conclusão: ${conclusaoGraduacaoEt.text}")
                if (instituicaoGraduacaoEt.text.isNotEmpty())
                    informacoesFormacao.add("Instituição: ${instituicaoGraduacaoEt.text}")
                if (informacoesFormacao.isNotEmpty())
                    formacao += " (${informacoesFormacao.joinToString()})"
                formacao += "; "
                informacoesFormacao.clear()
            }

            if (especializacaoCb.isChecked) {
                formacao += "Especialização"
                if (conclusaoEspecializacaoEt.text.isNotEmpty())
                    informacoesFormacao.add("Ano de conclusão: ${conclusaoEspecializacaoEt.text}")
                if (instituicaoEspecializacaoEt.text.isNotEmpty())
                    informacoesFormacao.add("Instituição: ${instituicaoEspecializacaoEt.text}")
                if (informacoesFormacao.isNotEmpty())
                    formacao += " (${informacoesFormacao.joinToString()})"
                formacao += "; "
                informacoesFormacao.clear()
            }

            if (mestradoCb.isChecked) {
                formacao += "Mestrado"
                if (conclusaoMestradoEt.text.isNotEmpty())
                    informacoesFormacao.add("Ano de conclusão: ${conclusaoEspecializacaoEt.text}")
                if (instituicaoMestradoEt.text.isNotEmpty())
                    informacoesFormacao.add("Instituição: ${instituicaoEspecializacaoEt.text}")
                if (monografiaMestradoEt.text.isNotEmpty())
                    informacoesFormacao.add("Título de monografia: ${monografiaMestradoEt.text}")
                if (orientadorMestradoEt.text.isNotEmpty())
                    informacoesFormacao.add("Orientador: ${orientadorMestradoEt.text}")
                if (informacoesFormacao.isNotEmpty())
                    formacao += " (${informacoesFormacao.joinToString()})"
                formacao += "; "
                informacoesFormacao.clear()
            }

            if (doutoradoCb.isChecked) {
                formacao += "Doutorado"
                if (conclusaoDoutoradoEt.text.isNotEmpty())
                    informacoesFormacao.add("Ano de conclusão: ${conclusaoDoutoradoEt.text}")
                if (instituicaoMestradoEt.text.isNotEmpty())
                    informacoesFormacao.add("Instituição: ${instituicaoDoutoradoEt.text}")
                if (monografiaMestradoEt.text.isNotEmpty())
                    informacoesFormacao.add("Título de monografia: ${monografiaDoutradoEt.text}")
                if (orientadorMestradoEt.text.isNotEmpty())
                    informacoesFormacao.add("Orientador: ${orientadorDoutoradoEt.text}")
                if (informacoesFormacao.isNotEmpty())
                    formacao += " (${informacoesFormacao.joinToString()})"
                informacoesFormacao.clear()
            }

            if (formacao.isNotEmpty())
                campos.add("Formação: ${formacao}")
            texto = campos.joinToString()
        }

        return texto

    }

}