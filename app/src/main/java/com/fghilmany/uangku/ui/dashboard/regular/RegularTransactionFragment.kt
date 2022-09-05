package com.fghilmany.uangku.ui.dashboard.regular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fghilmany.uangku.databinding.FragmentRegularTransactionBinding
import com.fghilmany.uangku.ui.base.BaseFragment

class RegularTransactionFragment : BaseFragment() {

    private var _binding: FragmentRegularTransactionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegularTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}