package com.task.noteapp.core.ui

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.task.noteapp.R


typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(private val inflate: Inflate<VB>) :
    Fragment() {

    protected abstract val viewModel: VM

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    protected abstract val state: UiState

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    protected open fun initialize() {
        handleLoading()
        handleFailure()
    }

    protected fun handleFailure() {
        view?.let { itView ->
            if (state.error.isNotEmpty()) {
                val value = TypedValue()
                context?.theme?.resolveAttribute(R.attr.colorError, value, true)
                Snackbar
                    .make(itView, state.error, Snackbar.LENGTH_LONG)
                    .setBackgroundTint(value.data)
                    .show()
                state.error = ""
            }
        }
    }

    protected fun handleLoading() {
        val progressBar = ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal)
        val constraintLayout =
            binding.root as ConstraintLayout
        progressBar.layoutParams = ConstraintLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        progressBar.isIndeterminate = true

        if (state.isLoading) {
            constraintLayout.addView(progressBar)
        } else {
            constraintLayout.removeView(progressBar)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}