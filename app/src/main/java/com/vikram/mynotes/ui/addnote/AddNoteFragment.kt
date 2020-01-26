package com.vikram.mynotes.ui.addnote

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.vikram.mynotes.MyNotesApp
import com.vikram.mynotes.R
import com.vikram.mynotes.databinding.FragmentAddNoteBinding
import com.vikram.mynotes.ui.base.BaseFragment
import com.vikram.mynotes.ui.shownote.DisplayNoteFragment
import com.vikram.mynotes.util.setClickListener
import kotlinx.android.synthetic.main.fragment_add_note.*

class AddNoteFragment : BaseFragment<AddNoteViewModel, FragmentAddNoteBinding>() {

    override fun getViewModelClass(): Class<AddNoteViewModel> = AddNoteViewModel::class.java

    override fun getLayoutRes(): Int = R.layout.fragment_add_note

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        MyNotesApp.get().applicationComponent.inject(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        binding.run {
            etTitle.addTextChangedListener(textWatcher)
            etContent.addTextChangedListener(textWatcher)
        }
        initListeners()
    }

    private fun initListeners() {
        viewModel.getStateLiveDate().observe(this, Observer {
            it?.let {state ->
                when(state){
                    is AddNoteState.Loading -> { binding.progressCircular.visibility = View.VISIBLE}
                    is AddNoteState.Successful -> {
                        binding.progressCircular.visibility = View.GONE
                        onNoteSaved(state.noteid)
                    }
                }
            }
        })
        binding.btnSave.setClickListener {
            viewModel.saveNote(title = binding.etTitle.text.toString(),content = binding.etContent.text.toString())
        }
    }

    private fun onNoteSaved(id:Long){
       val data = DisplayNoteFragment.getBundle(id)
        findNavController().navigate(R.id.action_addNoteFragment_to_displayNoteFragment,data)
    }
    private val textWatcher = object: TextWatcher{
        override fun afterTextChanged(p0: Editable?) {
            validateEmptyTexts()
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    }

    private fun validateEmptyTexts(){
        btn_save.isEnabled = when {
            binding.etContent.text.isNullOrEmpty() || binding.etTitle.text.isNullOrEmpty() -> false
            else -> true
        }
    }
}