package br.edu.ifsp.scl.ads.havagas

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.isVisible
import br.edu.ifsp.scl.ads.havagas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

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
                    anoFormacaoFundamentalEt.setText("")
                }
            }

            medioCb.setOnClickListener {
                if (medioLl.visibility == View.GONE)
                    medioLl.visibility = View.VISIBLE
                else {
                    medioLl.visibility = View.GONE
                    anoFormacaoMedioEt.setText("")
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

            limparBt.setOnClickListener {
                nomeEt.setText("")
                emailEt.setText("")
                emailAtualizacaoSw.isChecked = false
                telefoneRg.clearCheck()
                telefoneEt.setText("")
                sexoRg.check(masculinoRb.id)
                dataNascimentoEt.setText("")
                celularEt.setText("")
                celularLl.visibility = View.GONE
                if (fundamentalCb.isChecked) {
                    fundamentalCb.isChecked = false
                    fundamentalLl.visibility = View.GONE
                    anoFormacaoFundamentalEt.setText("")
                }
                if (medioCb.isChecked) {
                    medioCb.isChecked = false
                    medioLl.visibility = View.GONE
                    anoFormacaoMedioEt.setText("")
                }
                if (graduacaoCb.isChecked){
                    graduacaoCb.isChecked = false
                    graduacaoLl.visibility = View.GONE
                    instituicaoGraduacaoEt.setText("")
                    conclusaoGraduacaoEt.setText("")
                }
                if (especializacaoCb.isChecked){
                    especializacaoCb.isChecked = false
                    especializacaoLl.visibility = View.GONE
                    instituicaoEspecializacaoEt.setText("")
                    conclusaoEspecializacaoEt.setText("")
                }
                if (mestradoCb.isChecked){
                    mestradoCb.isChecked = false
                    mestradoLl.visibility = View.GONE
                    instituicaoMestradoEt.setText("")
                    conclusaoMestradoEt.setText("")
                    monografiaMestradoEt.setText("")
                    orientadorMestradoEt.setText("")
                }
                if (doutoradoCb.isChecked){
                    doutoradoCb.isChecked = false
                    doutoradoLl.visibility = View.GONE
                    instituicaoDoutoradoEt.setText("")
                    conclusaoDoutoradoEt.setText("")
                    monografiaDoutradoEt.setText("")
                    orientadorDoutoradoEt.setText("")
                }
                vagasInteresseEt.setText("")
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
            if (telefoneRg.checkedRadioButtonId != -1) {
                campos.add("Tipo de telefone: " +
                        "${findViewById<RadioButton>(telefoneRg.checkedRadioButtonId).text}")
            }
            if (celularLl.isVisible) {
                if (celularEt.text.isNotEmpty())
                    campos.add("Celular: ${celularEt.text}")
            }

            campos.add("Sexo: ${findViewById<RadioButton>(sexoRg.checkedRadioButtonId).text}")

            if (dataNascimentoEt.text.isNotEmpty())
                campos.add("Data de Nascimento: ${dataNascimentoEt.text}")

            var formacao : String = ""
            var informacoesFormacao : MutableList<String> = mutableListOf()
            if (fundamentalCb.isChecked) {
                formacao += "Fundamental"
                if (anoFormacaoFundamentalEt.text.isNotEmpty())
                    informacoesFormacao.add("Ano de formatura: ${anoFormacaoFundamentalEt.text}")
                if (informacoesFormacao.isNotEmpty())
                    formacao += " (${informacoesFormacao.joinToString()})"
                formacao += "; "
                informacoesFormacao.clear()
            }

            if (medioCb.isChecked) {
                formacao += "Médio"
                if (anoFormacaoMedioEt.text.isNotEmpty())
                    informacoesFormacao.add("Ano de formatura: ${anoFormacaoMedioEt.text}")

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
                    informacoesFormacao.add("Ano de conclusão: ${conclusaoMestradoEt.text}")
                if (instituicaoMestradoEt.text.isNotEmpty())
                    informacoesFormacao.add("Instituição: ${instituicaoMestradoEt.text}")
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
                if (instituicaoDoutoradoEt.text.isNotEmpty())
                    informacoesFormacao.add("Instituição: ${instituicaoDoutoradoEt.text}")
                if (monografiaDoutradoEt.text.isNotEmpty())
                    informacoesFormacao.add("Título de monografia: ${monografiaDoutradoEt.text}")
                if (orientadorDoutoradoEt.text.isNotEmpty())
                    informacoesFormacao.add("Orientador: ${orientadorDoutoradoEt.text}")
                if (informacoesFormacao.isNotEmpty())
                    formacao += " (${informacoesFormacao.joinToString()})"
                formacao += "; "
                informacoesFormacao.clear()
            }

            if (formacao.isNotEmpty()) {
                formacao = formacao.substring(0, formacao.lastIndexOf(';'))
                campos.add("Formação: $formacao")
            }

            if (vagasInteresseEt.text.isNotEmpty())
                campos.add("Vagas de interesse: ${vagasInteresseEt.text}")

            texto = campos.joinToString()
        }
        return texto
    }

}