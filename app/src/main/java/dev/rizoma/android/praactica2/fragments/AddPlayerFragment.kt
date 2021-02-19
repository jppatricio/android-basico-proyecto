package dev.rizoma.android.praactica2.fragments

import android.app.Activity
import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Window
import android.widget.Toast
import dev.rizoma.android.praactica2.R
import dev.rizoma.android.praactica2.utils.PlayerReader
import dev.rizoma.android.praactica2.utils.PlayerReaderDBHelper
import kotlinx.android.synthetic.main.custom_dialog_add.*
import java.lang.Exception

class AddPlayerFragment(a : Context) : Dialog(a) {
    private var c: Context = a

    override fun onCreate(savedInstanceState: Bundle?) {

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_dialog_add)
        val dbHelper = PlayerReaderDBHelper(context)
        nameText.error  = "Campo obligatorio"
        lastNameEdit.error = "Campo obligatorio"
        ageTextEdit.error = "Campo obligatorio"
        nationalityTextEdit.error = "Campo obligatorio"
        teamTextEdit.error = "Campo obligatorio"
        goalsTextEdit.error = "Campo obligatorio"
        birthTextEdit.error = "Campo obligatorio"

        nameText.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s?.toString().isNullOrBlank()) {
                    nameText.error = "Campo obligatorio"
                } else nameText.error = null
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        lastNameEdit.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s?.toString().isNullOrBlank()) {
                    lastNameEdit.error = "Campo obligatorio"
                } else lastNameEdit.error = null
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        ageTextEdit.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s?.toString().isNullOrBlank()) {
                    ageTextEdit.error = "Campo obligatorio"
                } else ageTextEdit.error = null
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        nationalityTextEdit.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s?.toString().isNullOrBlank()) {
                    nationalityTextEdit.error = "Campo obligatorio"
                } else nationalityTextEdit.error = null
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        teamTextEdit.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s?.toString().isNullOrBlank()) {
                    teamTextEdit.error = "Campo obligatorio"
                } else teamTextEdit.error = null
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        goalsTextEdit.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s?.toString().isNullOrBlank()) {
                    goalsTextEdit.error = "Campo obligatorio"

                    try{
                        s?.toString()!!.toInt()
                    }
                    catch (e : Exception){
                        goalsTextEdit.error = "Debe ser un número entero"
                    }
                }
                else goalsTextEdit.error = null
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        birthTextEdit.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s?.toString().isNullOrBlank()) {
                    birthTextEdit.error = "Campo obligatorio"
                } else birthTextEdit.error = null
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        addButton.setOnClickListener {
            if(nameTextEdit.error.isNullOrEmpty() && lastNameEdit.error.isNullOrEmpty() && ageTextEdit.error.isNullOrEmpty() && nationalityTextEdit.error.isNullOrEmpty() && goalsTextEdit.error.isNullOrEmpty() && birthTextEdit.error.isNullOrEmpty()){
                //add to DB
                // Gets the data repository in write mode
                val db = dbHelper.writableDatabase

// Create a new map of values, where column names are the keys
                val values = ContentValues().apply {
                    put(PlayerReader.PlayerEntry.NAME, nameTextEdit.text.toString())
                    put(PlayerReader.PlayerEntry.LAST_NAME, lastNameText.text.toString())
                    put(PlayerReader.PlayerEntry.AGE, ageText.text.toString())
                    put(PlayerReader.PlayerEntry.NATIONALITY, nationalityText.text.toString())
                    put(PlayerReader.PlayerEntry.TEAM, teamText.text.toString())
                    put(PlayerReader.PlayerEntry.GOALS, goalsText.text.toString())
                    put(PlayerReader.PlayerEntry.BORN, birthText.text.toString())
                }

// Insert the new row, returning the primary key value of the new row
                db?.insert(PlayerReader.PlayerEntry.TABLE_NAME, null, values)
                Toast.makeText(c, "¡Guardado!", Toast.LENGTH_SHORT).show()
                dismiss()
            }
            else{
                Toast.makeText(c, "Favor de rellenar los campos adecuados", Toast.LENGTH_SHORT).show()
            }
        }

        buttonCancel.setOnClickListener {
            this.dismiss()
        }
    }

}