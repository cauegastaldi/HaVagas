package br.edu.ifsp.scl.ads.havagas

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.get
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

            formacaoSp.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    anoFormacaoFundamentalMedioEt.setText("")
                    conclusaoGraduacaoEspecializacaoEt.setText("")
                    instituicaoGraduacaoEspecializacaoEt.setText("")
                    instituicaoMestradoDoutoradoEt.setText("")
                    conclusaoMestradoDoutoradoEt.setText("")
                    monografiaMestradoDoutoradoEt.setText("")
                    orientadorMestradoDoutoradoEt.setText("")
                    if (position == 0 || position == 1) {
                        fundamentalMedioLl.visibility = View.VISIBLE
                        graduacaoEspecializacaoLl.visibility = View.GONE
                        mestradoDoutoradoLl.visibility = View.GONE
                    } else if (position == 2 || position == 3) {
                        graduacaoEspecializacaoLl.visibility = View.VISIBLE
                        fundamentalMedioLl.visibility = View.GONE
                        mestradoDoutoradoLl.visibility = View.GONE
                    } else {
                        mestradoDoutoradoLl.visibility = View.VISIBLE
                        fundamentalMedioLl.visibility = View.GONE
                        graduacaoEspecializacaoLl.visibility = View.GONE
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
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
                formacaoSp.setSelection(0)
                fundamentalMedioLl.visibility = View.VISIBLE
                if (formacaoSp.selectedItemPosition == 0 || formacaoSp.selectedItemPosition == 1) {
                    anoFormacaoFundamentalMedioEt.setText("")
                } else if (formacaoSp.selectedItemPosition == 2 || formacaoSp.selectedItemPosition == 3) {
                    graduacaoEspecializacaoLl.visibility = View.GONE
                    conclusaoGraduacaoEspecializacaoEt.setText("")
                    instituicaoGraduacaoEspecializacaoEt.setText("")
                } else {
                    mestradoDoutoradoLl.visibility = View.GONE
                    instituicaoMestradoDoutoradoEt.setText("")
                    conclusaoMestradoDoutoradoEt.setText("")
                    monografiaMestradoDoutoradoEt.setText("")
                    orientadorMestradoDoutoradoEt.setText("")
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
            formacao += formacaoSp.selectedItem.toString()
            if (formacaoSp.selectedItemPosition == 0 || formacaoSp.selectedItemPosition == 1) {
                if (anoFormacaoFundamentalMedioEt.text.isNotEmpty())
                    informacoesFormacao.add("Ano de formação: ${anoFormacaoFundamentalMedioEt.text}")
            } else if (formacaoSp.selectedItemPosition == 2 || formacaoSp.selectedItemPosition == 3) {
                if (conclusaoGraduacaoEspecializacaoEt.text.isNotEmpty())
                    informacoesFormacao.add("Ano de conclusão: ${conclusaoGraduacaoEspecializacaoEt.text}")
                if (instituicaoGraduacaoEspecializacaoEt.text.isNotEmpty())
                    informacoesFormacao.add("Instituição: ${instituicaoGraduacaoEspecializacaoEt.text}")
            } else {
                if (conclusaoMestradoDoutoradoEt.text.isNotEmpty())
                    informacoesFormacao.add("Ano de conclusão: ${conclusaoMestradoDoutoradoEt.text}")
                if (instituicaoMestradoDoutoradoEt.text.isNotEmpty())
                    informacoesFormacao.add("Instituição: ${instituicaoMestradoDoutoradoEt.text}")
                if (monografiaMestradoDoutoradoEt.text.isNotEmpty())
                    informacoesFormacao.add("Título de monografia: ${monografiaMestradoDoutoradoEt.text}")
                if (orientadorMestradoDoutoradoEt.text.isNotEmpty())
                    informacoesFormacao.add("Orientador: ${orientadorMestradoDoutoradoEt.text}")
            }

            if (informacoesFormacao.isNotEmpty()) {
                formacao += " (${informacoesFormacao.joinToString()})"
                informacoesFormacao.clear()
            }

            campos.add("Formação: $formacao")
            /*
            if (formacao.isNotEmpty()) {
                formacao = formacao.substring(0, formacao.lastIndexOf(';'))
                campos.add("Formação: $formacao")
            }*/

            if (vagasInteresseEt.text.isNotEmpty())
                campos.add("Vagas de interesse: ${vagasInteresseEt.text}")

            texto = campos.joinToString()
        }
        return texto
    }

}