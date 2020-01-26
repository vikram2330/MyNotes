package com.vikram.mynotes.ui.addnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vikram.domain.roomdatabase.models.Note
import com.vikram.mynotes.ui.base.BaseViewModel
import com.vikram.mynotes.usecases.AddNoteUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddNoteViewModel @Inject constructor(private val addNoteUseCase: AddNoteUseCase) :
    BaseViewModel() {

    private val stateLiveData: MutableLiveData<AddNoteState> = MutableLiveData()
    fun getStateLiveDate(): LiveData<AddNoteState> = stateLiveData

    fun saveNote(title: String, content: String) {
        stateLiveData.value = AddNoteState.Loading
        viewModelScope.launch {
            val note = Note(title, content, System.currentTimeMillis())
            val noteId = addNoteUseCase.perform(note)
            stateLiveData.postValue(AddNoteState.Successfull(noteId))
        }
    }
}

sealed class AddNoteState {
    object Loading : AddNoteState()
    class Successfull(noteid: Long) : AddNoteState()
}