package br.edu.ifsp.scl.ads.havagas

import android.annotation.SuppressLint
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
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

            if (savedInstanceState != null) {
                nomeEt.setText(savedInstanceState.getString("nome"))
                emailEt.setText(savedInstanceState.getString("email"))
                emailAtualizacaoSw.isChecked =
                    savedInstanceState.getBoolean("receberAtualizacoesOportunidades") == true
                telefoneEt.setText(savedInstanceState.getString("telefone"))
                if (savedInstanceState.getString("celular")?.isNotEmpty() == true) {
                    celularLl.visibility = View.VISIBLE
                    celularEt.setText(savedInstanceState.getString("celular"))
                }
                sexoRg.check(savedInstanceState.getInt("sexo"))
                dataNascimentoEt.setText(savedInstanceState.getString("dataNascimento"))
                formacaoSp.setSelection(savedInstanceState.getInt("formacao"))
                if (formacaoSp.selectedItemPosition == 0 || formacaoSp.selectedItemPosition == 1) {
                    fundamentalMedioLl.visibility = View.VISIBLE
                    anoFormacaoFundamentalMedioEt.setText(savedInstanceState.getString("anoFormacaoFundamentalMedio"))
                } else if (formacaoSp.selectedItemPosition == 2 || formacaoSp.selectedItemPosition == 3) {
                    graduacaoEspecializacaoLl.visibility = View.VISIBLE
                    conclusaoGraduacaoEspecializacaoEt.setText(savedInstanceState.getString("anoConclusaoGraduacaoEspecializacao"))
                    instituicaoGraduacaoEspecializacaoEt.setText(savedInstanceState.getString("instituicaoGraduacaoEspecializacao"))
                } else {
                    mestradoDoutoradoLl.visibility = View.VISIBLE
                    conclusaoMestradoDoutoradoEt.setText(savedInstanceState.getString("anoConclusaoMestradoDoutorado"))
                    instituicaoMestradoDoutoradoEt.setText(savedInstanceState.getString("instituicaoMestradoDoutorado"))
                    monografiaMestradoDoutoradoEt.setText(savedInstanceState.getString("tituloMonografia"))
                    orientadorMestradoDoutoradoEt.setText(savedInstanceState.getString("orientador"))
                }
            }

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

                    if (position == 0 || position == 1) {
                        fundamentalMedioLl.visibility = View.VISIBLE
                        graduacaoEspecializacaoLl.visibility = View.GONE
                        mestradoDoutoradoLl.visibility = View.GONE

                        conclusaoGraduacaoEspecializacaoEt.setText("")
                        instituicaoGraduacaoEspecializacaoEt.setText("")
                        instituicaoMestradoDoutoradoEt.setText("")
                        conclusaoMestradoDoutoradoEt.setText("")
                        monografiaMestradoDoutoradoEt.setText("")
                        orientadorMestradoDoutoradoEt.setText("")

                    } else if (position == 2 || position == 3) {
                        graduacaoEspecializacaoLl.visibility = View.VISIBLE
                        fundamentalMedioLl.visibility = View.GONE
                        mestradoDoutoradoLl.visibility = View.GONE

                        anoFormacaoFundamentalMedioEt.setText("")
                        instituicaoMestradoDoutoradoEt.setText("")
                        conclusaoMestradoDoutoradoEt.setText("")
                        monografiaMestradoDoutoradoEt.setText("")
                        orientadorMestradoDoutoradoEt.setText("")
                    } else {
                        mestradoDoutoradoLl.visibility = View.VISIBLE
                        fundamentalMedioLl.visibility = View.GONE
                        graduacaoEspecializacaoLl.visibility = View.GONE

                        anoFormacaoFundamentalMedioEt.setText("")
                        conclusaoGraduacaoEspecializacaoEt.setText("")
                        instituicaoGraduacaoEspecializacaoEt.setText("")
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
                telefoneRg.check(comercialRb.id)
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

            if (vagasInteresseEt.text.isNotEmpty())
                campos.add("Vagas de interesse: ${vagasInteresseEt.text}")

            texto = campos.joinToString()
        }
        return texto
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        with (activityMainBinding) {
            outState.putString("nome", nomeEt.text.toString())
            outState.putString("email", emailEt.text.toString())
            outState.putBoolean("receberAtualizacoesOportunidades", emailAtualizacaoSw.isChecked)
            outState.putInt("tipoTelefone", telefoneRg.checkedRadioButtonId)
            outState.putString("telefone", telefoneEt.text.toString())
            outState.putString("celular", celularEt.text.toString())
            outState.putInt("sexo", sexoRg.checkedRadioButtonId)
            outState.putString("dataNascimento", dataNascimentoEt.text.toString())
            outState.putInt("formacao", formacaoSp.selectedItemId.toInt())
            if (formacaoSp.selectedItemPosition == 0 || formacaoSp.selectedItemPosition == 1) {
                outState.putString("anoFormacaoFundamentalMedio",
                    anoFormacaoFundamentalMedioEt.text.toString())
            } else if (formacaoSp.selectedItemPosition == 2 || formacaoSp.selectedItemPosition == 3) {
                outState.putString("anoConclusaoGraduacaoEspecializacao",
                    conclusaoGraduacaoEspecializacaoEt.text.toString())
                outState.putString("instituicaoGraduacaoEspecializacao",
                    instituicaoGraduacaoEspecializacaoEt.text.toString())
            } else {
                outState.putString("anoConclusaoMestradoDoutorado",
                      conclusaoMestradoDoutoradoEt.text.toString())
                outState.putString("instituicaoMestradoDoutorado",
                      instituicaoMestradoDoutoradoEt.text.toString())
                outState.putString("tituloMonografia",
                    monografiaMestradoDoutoradoEt.text.toString())
                outState.putString("orientador", orientadorMestradoDoutoradoEt.text.toString())
            }
            outState.putString("vagasInteresse", vagasInteresseEt.text.toString())
        }
    }

}