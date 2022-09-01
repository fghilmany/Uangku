package com.fghilmany.uangku.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fghilmany.uangku.R
import com.fghilmany.uangku.core.data.Resource
import com.fghilmany.uangku.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.email.value = "email"
        viewModel.email.observe(this, {
            Timber.d(it)
        })
        viewModel.getEmailOnline()?.observe(this, {
            when(it){
                is Resource.Loading -> {}
                is Resource.Success -> {}
                is Resource.Error -> {}
            }
        })
    }
}