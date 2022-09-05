package com.fghilmany.uangku.ui.dashboard.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fghilmany.uangku.R
import com.fghilmany.uangku.core.utils.HOME_NUM_PAGES
import com.fghilmany.uangku.databinding.FragmentHomeBinding
import com.fghilmany.uangku.ui.base.BaseFragment
import com.fghilmany.uangku.ui.dashboard.home.detail.DetailFragment
import com.fghilmany.uangku.ui.dashboard.home.history.HistoryFragment
import com.fghilmany.uangku.ui.dashboard.home.list.ListFragment
import com.fghilmany.uangku.ui.dashboard.home.list.expenses.ExpensesFragment
import com.fghilmany.uangku.ui.dashboard.home.list.income.IncomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var fragmentId : Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mOnNavigationItemSelectedListener =
            NavigationBarView.OnItemSelectedListener { item ->
                fragmentId = item.itemId
                when (item.itemId) {
                    R.id.navigation_list -> {
                        fragment(ListFragment())
                        return@OnItemSelectedListener true
                    }
                    R.id.navigation_detail -> {
                        fragment(DetailFragment())
                        return@OnItemSelectedListener true
                    }
                    R.id.navigation_history -> {
                        fragment(HistoryFragment())
                        return@OnItemSelectedListener true
                    }
                }
                false
            }

        binding.navBottom.setOnItemSelectedListener(mOnNavigationItemSelectedListener)
        binding.navBottom.selectedItemId = fragmentId?:R.id.navigation_list
    }

    fun fragment(fragment: Fragment){
        val mFragmentManager = childFragmentManager
        mFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment_activity_main, fragment, fragment::class.java.simpleName)
            .addToBackStack(fragment::class.java.simpleName)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}