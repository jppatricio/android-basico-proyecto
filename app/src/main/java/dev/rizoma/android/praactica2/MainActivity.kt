package dev.rizoma.android.praactica2

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dev.rizoma.android.praactica2.adapters.ParticipantAdapter
import dev.rizoma.android.praactica2.fragments.AddPlayerFragment
import dev.rizoma.android.praactica2.listeners.ParticipantListener
import dev.rizoma.android.praactica2.models.Participant
import dev.rizoma.android.praactica2.utils.PlayerReader
import dev.rizoma.android.praactica2.utils.PlayerReaderDBHelper
import java.util.logging.Logger


class MainActivity : AppCompatActivity(), ParticipantListener {
    public val participants = arrayListOf<Participant>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = PlayerReaderDBHelper(this)
        val l = Logger.getLogger("TEST")


        val partAdapter = ParticipantAdapter(participants)
        val participantRecycler = findViewById<RecyclerView>(R.id.participantRecyvler)
        partAdapter.setPartListener(this)
        participantRecycler.adapter = partAdapter
        val layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        participantRecycler.layoutManager = layoutManager

        val db = dbHelper.readableDatabase

        val cursor = db.query(
            PlayerReader.PlayerEntry.TABLE_NAME,   // The table to query
            null,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null               // The sort order
        )

        with(cursor) {
            while (moveToNext()) {
                l.info("INFO")
                val part = Participant(
                    getInt(getColumnIndexOrThrow(PlayerReader.PlayerEntry._ID)),
                    getString(getColumnIndexOrThrow(PlayerReader.PlayerEntry.NAME)),
                    getString(getColumnIndexOrThrow(PlayerReader.PlayerEntry.LAST_NAME)),
                    getInt(getColumnIndexOrThrow(PlayerReader.PlayerEntry.AGE)),
                    getString(getColumnIndexOrThrow(PlayerReader.PlayerEntry.NATIONALITY)),
                    getString(getColumnIndexOrThrow(PlayerReader.PlayerEntry.TEAM)),
                    getInt(getColumnIndexOrThrow(PlayerReader.PlayerEntry.GOALS)),
                    getString(getColumnIndexOrThrow(PlayerReader.PlayerEntry.BORN))
                )

                participants.add(part)
            }
        }

    }

    override fun onClickPart(participant: Participant) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("data", participant)
        }
        startActivity(intent)
    }

    override fun onSharePart(participant: Participant) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("data", participant)
        }
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id: Int = item.itemId
        if (id == R.id.action_user) {
            val cargandopop = AddPlayerFragment(this)
            cargandopop.setOnDismissListener(DialogInterface.OnDismissListener {
                // reload
//                val dbHelper = PlayerReaderDBHelper(this).readableDatabase
//                val cursor = dbHelper.rawQuery(PlayerReader.SQL_GET_LATEST_ENTRY, null)
//                with(cursor) {
//                    if (moveToFirst()) {
//                        val part = Participant(
//                            getInt(getColumnIndexOrThrow(PlayerReader.PlayerEntry._ID)),
//                            getString(getColumnIndexOrThrow(PlayerReader.PlayerEntry.NAME)),
//                            getString(getColumnIndexOrThrow(PlayerReader.PlayerEntry.LAST_NAME)),
//                            getInt(getColumnIndexOrThrow(PlayerReader.PlayerEntry.AGE)),
//                            getString(getColumnIndexOrThrow(PlayerReader.PlayerEntry.NATIONALITY)),
//                            getString(getColumnIndexOrThrow(PlayerReader.PlayerEntry.TEAM)),
//                            getInt(getColumnIndexOrThrow(PlayerReader.PlayerEntry.GOALS)),
//                            getString(getColumnIndexOrThrow(PlayerReader.PlayerEntry.BORN))
//                        )
//                        participants.add(part)
//                    }
//                }
//
//                cursor.close();

                finish();
                startActivity(intent);
            })
            cargandopop.window!!.setBackgroundDrawable(
                ColorDrawable(
                    Color.TRANSPARENT
                )
            ) //Para tener la transparencia

            cargandopop.setCanceledOnTouchOutside(true)
            cargandopop.show()
            return true
        } else return super.onOptionsItemSelected(item)
    }

}