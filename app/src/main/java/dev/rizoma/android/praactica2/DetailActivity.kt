package dev.rizoma.android.praactica2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.rizoma.android.praactica2.models.Participant
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val b = intent.extras
        val data = b!!.getParcelable<Participant>("data")

        if (data != null) {
            nombreDetail.text = data.name
        apellidoDetail.text = data.lastName
        edadDetail.text = data.age.toString()
        nacionalidadDetail.text = data.nationality
        equipoDetail.text = data.equipoActual
        golesDetail.text = data.golesTotales.toString()
        nacimientoDetail.text = data.nacimiento

        }


    }
}