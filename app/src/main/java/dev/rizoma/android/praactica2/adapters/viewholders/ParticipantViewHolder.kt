package dev.rizoma.android.praactica2.adapters.viewholders

import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.rizoma.android.praactica2.R
import dev.rizoma.android.praactica2.listeners.ParticipantListener
import dev.rizoma.android.praactica2.models.Participant
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderScriptBlur

class ParticipantViewHolder(val itemVieew: View) : RecyclerView.ViewHolder(itemVieew) {

    private var participantListener:ParticipantListener? = null
    private var part:Participant? = null

    private var nameTextView: TextView = itemVieew.findViewById(R.id.nameTextView)
    private var participantAgeTextView: TextView = itemVieew.findViewById(R.id.participantAgeTextView)
    private var lastnameTextView: TextView = itemVieew.findViewById(R.id.lastnameTextView)
    private var nationalityTextView: TextView = itemVieew.findViewById(R.id.nationalityTextView)
    private var shareButton: Button = itemVieew.findViewById(R.id.shareButton)
    public var blurView: BlurView = itemVieew.findViewById(R.id.blurView)

    fun bindParticipant(part: Participant) {
        this.part=part
        nameTextView.text = this.part?.name
        participantAgeTextView.text = this.part?.age.toString()
        lastnameTextView.text = this.part?.lastName
        nationalityTextView.text = this.part?.nationality

        itemVieew.setOnClickListener { view ->
            participantListener?.onClickPart(this.part!!)
        }
        shareButton.setOnClickListener { view ->
            participantListener?.onSharePart(this.part!!)
        }


    }

    fun setBookListener(listener: ParticipantListener?){
        this.participantListener=listener
    }

}