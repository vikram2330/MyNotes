package com.vikram.mynotes.ui.shownote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.vikram.domain.roomdatabase.models.Note
import com.vikram.mynotes.MyNotesApp
import com.vikram.mynotes.R
import com.vikram.mynotes.databinding.FragmentDisplayNoteBinding
import com.vikram.mynotes.ui.base.BaseFragment
import com.vikram.mynotes.util.AppConstants.DD_MM_YY__HH_MM
import com.vikram.mynotes.util.AppConstants.NOTE_ID
import com.vikram.mynotes.util.toDate

class DisplayNoteFragment : BaseFragment<DisplayNoteViewModel, FragmentDisplayNoteBinding>() {

    companion object {
        fun getBundle(noteId: Long): Bundle {
            return Bundle().apply {
                putLong(NOTE_ID, noteId)
            }
        }
    }

    override fun getViewModelClass(): Class<DisplayNoteViewModel> = DisplayNoteViewModel::class.java

    override fun getLayoutRes(): Int = R.layout.fragment_display_note

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        MyNotesApp.get().applicationComponent.inject(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initListeners()
        arguments?.let {
            viewModel.getNote(it.getLong(NOTE_ID, 0))
        }
    }

    private fun initListeners() {
        viewModel.getStateLiveData().observe(this, Observer {
            it.let { state ->
                when (state) {
                    is DisplayNoteFragmentState.Loading -> {
                        binding.progressCircular.visibility = View.VISIBLE
                    }
                    is DisplayNoteFragmentState.Success -> {
                        binding.progressCircular.visibility = View.GONE
                        loadData(state.note)
                    }
                    is DisplayNoteFragmentState.Error -> {
                        binding.progressCircular.visibility = View.GONE
                        Toast.makeText(
                            context,
                            state.exception.localizedMessage?.toString(),
                            Toast.LENGTH_LONG
                        )
                    }
                }
            }
        })
    }

    private fun loadData(note: Note) {
        binding.run {
            tvDate.text = getString(R.string.template_created_at,note.date.toDate(DD_MM_YY__HH_MM))
            tvTitle.text = note.title
            tvContent.text = note.content
        }
    }


}