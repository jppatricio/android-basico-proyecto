package dev.rizoma.android.praactica2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.recyclerview.widget.RecyclerView
import dev.rizoma.android.praactica2.R
import dev.rizoma.android.praactica2.adapters.viewholders.ParticipantViewHolder
import dev.rizoma.android.praactica2.listeners.ParticipantListener
import dev.rizoma.android.praactica2.models.Participant
import eightbitlab.com.blurview.RenderScriptBlur
import kotlinx.android.synthetic.main.item_participant.view.*


class ParticipantAdapter(val participants: List<Participant>) : RecyclerView.Adapter<ParticipantViewHolder>() {

    private var partListener: ParticipantListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipantViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_participant, parent, false)

//        itemView.blurView.setupWith(parent)
//            .setBlurAlgorithm(RenderScriptBlur(parent.context))
//            .setBlurRadius(23f)
//            .setBlurAutoUpdate(true)

        val partViewHolder = ParticipantViewHolder(itemView)
        partViewHolder.setBookListener(partListener)
        val rootView = parent


        return partViewHolder
    }



    override fun onBindViewHolder(holder: ParticipantViewHolder, position: Int) {
        val rootView = holder.itemView.rootView
        holder.blurView.setupWith(rootView as ViewGroup)
            .setFrameClearDrawable(rootView.background)
            .setBlurAlgorithm(RenderScriptBlur(rootView.context))
            .setBlurRadius(23f)
            .setBlurAutoUpdate(true)

        holder.blurView.outlineProvider = ViewOutlineProvider.BACKGROUND
        holder.blurView.clipToOutline = true

        holder.bindParticipant(participants[position])
    }

    override fun getItemCount(): Int {
        return participants.size
    }

    fun setPartListener(listener: ParticipantListener?){
        this.partListener=listener
    }
}