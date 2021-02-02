package com.sychev.movieapp.presentation.ui.person

sealed class PersonEvent() {

    class GetPersonEvent(val personId: Int): PersonEvent()

}