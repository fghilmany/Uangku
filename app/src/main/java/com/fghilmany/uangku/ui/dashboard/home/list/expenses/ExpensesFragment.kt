package com.fghilmany.uangku.ui.dashboard.home.list.expenses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fghilmany.uangku.core.utils.EXPENSES_TYPE_LIST
import com.fghilmany.uangku.databinding.FragmentExpensesBinding
import com.fghilmany.uangku.ui.base.BaseFragment
import com.google.android.material.chip.Chip
import java.util.*

class ExpensesFragment : BaseFragment() {

    private var _binding: FragmentExpensesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentExpensesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        initChip()
    }

    private fun initChip() {
        EXPENSES_TYPE_LIST.forEach { item ->
            val chip = Chip(requireContext())
            chip.text = item.replace("_", " ")
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
            binding.cgExpenseType.addView(chip)
        }
    }


}