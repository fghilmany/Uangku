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
import com.fghilmany.uangku.core.utils.InputFilterMinMax
import com.fghilmany.uangku.databinding.FragmentOnBoardingSavingsBinding
import com.fghilmany.uangku.ui.base.BaseFragment
import com.fghilmany.uangku.ui.splash.SplashMainActivity
import com.fghilmany.uangku.ui.splash.SplashMainViewModel
import com.google.android.material.textfield.TextInputEditText
import org.koin.android.viewmodel.ext.android.sharedViewModel


class OnBoardingSavingsFragment : BaseFragment() {

    private var _binding: FragmentOnBoardingSavingsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SplashMainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOnBoardingSavingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        clickHandler()
        checkedHandler()
        textChangeHandler()
    }

    private fun clickHandler() {
        val instance = ((activity as AppCompatActivity) as SplashMainActivity)
        with(binding){
            fabNext.setOnClickListener {
                instance.binding.vp2Pager.currentItem = 2
            }
            fabPrev.setOnClickListener {
                instance.binding.vp2Pager.currentItem = 0
            }
        }
    }

    private fun initData() {
        binding.etChooseDay.filters = arrayOf<InputFilter>(InputFilterMinMax(1, 31))

    }

    private fun textChangeHandler() {
        with(binding){
            etRegularSaving.globalChange()
            etRegularSavingName.globalChange()
            etChooseDay.globalChange()
        }
    }

    private fun checkedHandler() {
        with(binding){
            cbHaveRegularSaving.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    fabNext.isEnabled = false
                    groupRegularSaving.visibility = View.VISIBLE
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
                    if (cbAutoInput.isChecked && cbHaveRegularSaving.isChecked)
                        fabNext.isEnabled = etChooseDay.isValid == true && etRegularSaving.isValid == true && etRegularSavingName.isValid == true
                    else if (cbAutoInput.isChecked)
                        fabNext.isEnabled = etChooseDay.isValid == true
                    else if (cbHaveRegularSaving.isChecked)
                        fabNext.isEnabled = etRegularSaving.isValid == true && etRegularSavingName.isValid == true
                    else
                        fabNext.isEnabled = true
                }
            }

        })
    }

}