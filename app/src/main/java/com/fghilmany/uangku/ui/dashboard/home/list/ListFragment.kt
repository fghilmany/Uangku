package com.fghilmany.uangku.ui.dashboard.home.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fghilmany.uangku.R
import com.fghilmany.uangku.core.utils.HOME_NUM_PAGES
import com.fghilmany.uangku.databinding.FragmentListBinding
import com.fghilmany.uangku.ui.base.BaseFragment
import com.fghilmany.uangku.ui.dashboard.home.list.expenses.ExpensesFragment
import com.fghilmany.uangku.ui.dashboard.home.list.income.IncomeFragment
import com.google.android.material.tabs.TabLayoutMediator

class ListFragment : BaseFragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagerAdapter = ScreenSlidePagerAdapter(requireActivity())
        with(binding){
            vp2Pager.adapter = pagerAdapter
            TabLayoutMediator(tabLayout, vp2Pager){ tab, position ->
                when(position){
                    0 -> tab.text = resources.getString(R.string.expenses)
                    1 -> tab.text = resources.getString(R.string.income)
                }
            }. attach()
        }
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = HOME_NUM_PAGES

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> {
                    ExpensesFragment()
                }
                1 -> {
                    IncomeFragment()
                }
                else -> ExpensesFragment()
            }
        }
    }

}