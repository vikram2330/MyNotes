package com.vikram.mynotes.ui.shownote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vikram.domain.roomdatabase.models.Note
import com.vikram.mynotes.ui.base.BaseViewModel
import com.vikram.mynotes.usecases.GetNoteByIdUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DisplayNoteViewModel @Inject constructor(private val useCase: GetNoteByIdUseCase) :
    BaseViewModel() {
    private val stateMutableLiveData: MutableLiveData<DisplayNoteFragmentState> = MutableLiveData()
    fun getStateLiveData(): LiveData<DisplayNoteFragmentState> = stateMutableLiveData

    fun getNote(id: Long) {
        stateMutableLiveData.value = DisplayNoteFragmentState.Loading
        viewModelScope.launch {
            useCase.perform(id)?.let {
                stateMutableLiveData.postValue(DisplayNoteFragmentState.Success(it))
            }
                ?: stateMutableLiveData.postValue(DisplayNoteFragmentState.Error(Exception("No note saved locally with given Id")))
        }

    }

}

sealed class DisplayNoteFragmentState {
    object Loading : DisplayNoteFragmentState()
    class Success(val note: Note) : DisplayNoteFragmentState()
    class Error(val exception: Exception) : DisplayNoteFragmentState()
}