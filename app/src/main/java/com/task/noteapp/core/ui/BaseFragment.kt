package com.task.noteapp.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment() {

    @Inject
    lateinit var viewModel: VM

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    @LayoutRes
    abstract fun getContentViewId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater,
            getContentViewId(),
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    abstract fun initialize()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}