package com.rabobank.rabobankassignment.core.platform

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.rabobank.rabobankassignment.Loadable

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {

    protected var animationLoader: Loadable? = null
    private var _binding: VB? = null
    val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)

        return binding?.root
    }

    override fun onAttach(context: Context) {
        if (activity is Loadable) {
            animationLoader = activity as Loadable
        }

        super.onAttach(context)
    }

    override fun onDetach() {
        animationLoader = null

        super.onDetach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}