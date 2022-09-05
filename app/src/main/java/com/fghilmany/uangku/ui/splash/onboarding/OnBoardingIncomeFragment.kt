package com.fghilmany.uangku.ui.splash.onboarding

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.fghilmany.uangku.core.utils.InputFilterMinMax
import com.fghilmany.uangku.databinding.FragmentOnBoardingIncomeBinding
import com.fghilmany.uangku.ui.base.BaseFragment
import com.fghilmany.uangku.ui.splash.SplashMainActivity
import com.fghilmany.uangku.ui.splash.SplashMainViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import org.koin.android.viewmodel.ext.android.sharedViewModel
import java.text.SimpleDateFormat
import java.util.*


class OnBoardingIncomeFragment : BaseFragment() {

    private var _binding: FragmentOnBoardingIncomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SplashMainViewModel by sharedViewModel()

    private lateinit var instance: SplashMainActivity

    private var chooseDate = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOnBoardingIncomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        instance = ((activity as AppCompatActivity) as SplashMainActivity)

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
            etRegularIncome.globalChange()
            etRegularIncomeName.globalChange()
            etChooseDay.globalChange()
        }
    }

    private fun checkedHandler() {
        with(binding){
            cbHaveRegularIncome.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    fabNext.isEnabled = false
                    groupRegularIncome.visibility = View.VISIBLE
                }else{
                    fabNext.isEnabled = true
                    groupRegularIncome.visibility = View.GONE
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

    private fun clickHandler() {
        with(binding){
            fabNext.setOnClickListener {
                instance.binding.vp2Pager.currentItem = 1
                instance.binding.vp2Pager.isUserInputEnabled = false
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
                    if (cbAutoInput.isChecked && cbHaveRegularIncome.isChecked)
                        fabNext.isEnabled = etChooseDay.isValid == true && etRegularIncome.isValid == true && etRegularIncomeName.isValid == true
                    else if (cbAutoInput.isChecked)
                        fabNext.isEnabled = etChooseDay.isValid == true
                    else if (cbHaveRegularIncome.isChecked)
                        fabNext.isEnabled = etRegularIncome.isValid == true && etRegularIncomeName.isValid == true
                    else
                        fabNext.isEnabled = true
                }
            }

        })
    }

}