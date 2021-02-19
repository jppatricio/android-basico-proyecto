package dev.rizoma.android.praactica2.listeners

import dev.rizoma.android.praactica2.models.Participant

interface ParticipantListener {

    fun onClickPart(participant: Participant)

    fun onSharePart(participant: Participant)
}