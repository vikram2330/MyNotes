package com.vikram.mynotes.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vikram.mynotes.MyNotesApp
import com.vikram.mynotes.R
import com.vikram.mynotes.databinding.FragmentHomeBinding
import com.vikram.mynotes.ui.base.BaseFragment
import com.vikram.mynotes.util.setClickListener
import com.vikram.mynotes.util.setDivider


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    private val notesAdapter: NotesListAdapter by lazy { NotesListAdapter() }

    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun getLayoutRes(): Int = R.layout.fragment_home

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
        setListeners()
        viewModel.getAllNotes()
    }

    private fun initUI() {
        binding.rvRepository.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setDivider(R.drawable.divider_drawable)
            adapter = notesAdapter
        }

    }

    private fun setListeners(){
        viewModel.getNotesListLiveData().observe(this, Observer {
            binding.noteAvailable = it.isNotEmpty()
            notesAdapter.updateNotesList(it)
        })
        binding.btnAddNote.setClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)
        }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getAllNotes()
        }
        viewModel.getLoadingLiveData()
            .observe(this, Observer { loading ->
                if (loading != binding.swipeRefresh.isRefreshing) {
                    binding.swipeRefresh.isRefreshing = loading
                }
            })
    }

}