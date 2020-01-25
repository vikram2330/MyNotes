package com.vikram.mynotes.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vikram.domain.roomdatabase.models.Note
import com.vikram.mynotes.ui.base.BaseViewModel
import com.vikram.mynotes.usecases.AddNoteUseCase
import com.vikram.mynotes.usecases.GetSavedNotesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val getSavedNotesUseCase: GetSavedNotesUseCase
) : BaseViewModel() {
    private val notesListLiveData : MutableLiveData<List<Note>> = MutableLiveData()
    private val loadingLiveData : MutableLiveData<Boolean> = MutableLiveData()

    fun getNotesListLiveData():LiveData<List<Note>> = notesListLiveData

    fun getAllNotes(){
        loadingLiveData.value = true
        viewModelScope.launch(Dispatchers.Default) {
           notesListLiveData.postValue(getSavedNotesUseCase.perform())
            loadingLiveData.postValue(false)
        }
    }

    fun getLoadingLiveData(): LiveData<Boolean> = loadingLiveData
}

