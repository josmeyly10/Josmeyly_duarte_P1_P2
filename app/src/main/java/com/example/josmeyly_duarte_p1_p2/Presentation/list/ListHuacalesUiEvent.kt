package com.example.josmeyly_duarte_p1_p2.Presentation.list


    sealed interface ListHuacalesUiEvent {
        data object Load : com.example.josmeyly_duarte_p1_p2.Presentation.list.ListHuacalesUiEvent
        data class Delete(val id: Int) :
            com.example.josmeyly_duarte_p1_p2.Presentation.list.ListHuacalesUiEvent
        data object CreateNew : com.example.josmeyly_duarte_p1_p2.Presentation.list.ListHuacalesUiEvent
        data class Edit(val id: Int) :
            com.example.josmeyly_duarte_p1_p2.Presentation.list.ListHuacalesUiEvent
        data class ShowMessage(val message: String) :
            com.example.josmeyly_duarte_p1_p2.Presentation.list.ListHuacalesUiEvent

    }