package com.fghilmany.uangku.ui.splash.onboarding

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.fghilmany.uangku.core.utils.InputFilterMinMax
import com.fghilmany.uangku.core.utils.PreferenceProvider
import com.fghilmany.uangku.databinding.FragmentOnBoardingExpenseBinding
import com.fghilmany.uangku.ui.base.BaseFragment
import com.fghilmany.uangku.ui.dashboard.DashboardActivity
import com.fghilmany.uangku.ui.splash.SplashMainActivity
import com.fghilmany.uangku.ui.splash.SplashMainViewModel
import com.google.android.material.textfield.TextInputEditText
import org.koin.android.viewmodel.ext.android.sharedViewModel

class OnBoardingExpenseFragment : BaseFragment() {

    private var _binding: FragmentOnBoardingExpenseBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SplashMainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOnBoardingExpenseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        clickHandler()
        checkedHandler()
        textChangeHandler()
    }

    private fun initData() {
        binding.etChooseDay.filters = arrayOf<InputFilter>(InputFilterMinMax(1, 31))

    }

    private fun textChangeHandler() {
        with(binding){
            etRegularExpense.globalChange()
            etRegularExpenseName.globalChange()
            etChooseDay.globalChange()
        }
    }

    private fun clickHandler() {
        with(binding){
            fabNext.setOnClickListener {
                PreferenceProvider(requireContext()).setFirstOpen(false)
                Intent(requireActivity(), DashboardActivity::class.java)
                    .apply {
                        startActivity(this)
                    }

            }
            fabPrev.setOnClickListener {
                val instance = ((activity as AppCompatActivity) as SplashMainActivity)
                instance.binding.vp2Pager.currentItem = 1
            }
        }
    }

    private fun checkedHandler() {
        with(binding){
            cbHaveRegularExpense.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    fabNext.isEnabled = false
                    groupRegularExpense.visibility = View.VISIBLE
                }else{
                    fabNext.isEnabled = true
                    groupChooseDate.visibility = View.GONE
                }
            }

            cbAutoInput.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    fabNext.isEnabled = false
                    groupChooseDate.visibility = View.VISIBLE
                }else{
                    fabNext.isEnabled = true
                    groupChooseDate.visibility = View.GONE
                }
            }
        }
    }

    private fun TextInputEditText.globalChange() {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                with(binding) {
                    if (cbAutoInput.isChecked && cbHaveRegularExpense.isChecked)
                        fabNext.isEnabled = etChooseDay.isValid == true && etRegularExpense.isValid == true && etRegularExpenseName.isValid == true
                    else if (cbAutoInput.isChecked)
                        fabNext.isEnabled = etChooseDay.isValid == true
                    else if (cbHaveRegularExpense.isChecked)
                        fabNext.isEnabled = etRegularExpense.isValid == true && etRegularExpenseName.isValid == true
                    else
                        fabNext.isEnabled = false
                }
            }

        })
    }
}