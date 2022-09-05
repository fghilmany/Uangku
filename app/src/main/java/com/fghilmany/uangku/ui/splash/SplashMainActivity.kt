package com.fghilmany.uangku.ui.splash

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.fghilmany.uangku.R
import com.fghilmany.uangku.core.utils.ON_BOARDING_NUM_PAGES
import com.fghilmany.uangku.core.utils.PreferenceProvider
import com.fghilmany.uangku.databinding.ActivitySplashBinding
import com.fghilmany.uangku.ui.base.BaseActivity
import com.fghilmany.uangku.ui.dashboard.DashboardActivity
import com.fghilmany.uangku.ui.splash.onboarding.OnBoardingExpenseFragment
import com.fghilmany.uangku.ui.splash.onboarding.OnBoardingIncomeFragment
import com.fghilmany.uangku.ui.splash.onboarding.OnBoardingSavingsFragment
import org.koin.android.viewmodel.ext.android.viewModel

class SplashMainActivity : BaseActivity() {

    lateinit var binding: ActivitySplashBinding

    private val viewModel: SplashMainViewModel by viewModel()

    private var onBoardingPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            updateCircleMarker(binding, position)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.apply {
            val pagerAdapter = ScreenSlidePagerAdapter(this@SplashMainActivity)
            vp2Pager.adapter = pagerAdapter
            vp2Pager.registerOnPageChangeCallback(onBoardingPageChangeCallback)

        }

        viewModel.liveData.observe(this){
            when(it){
                is SplashState.StartSplash -> playAnimation()
                is SplashState.FinishSplash -> {
                    if (PreferenceProvider(this).isFirstOpen()){
                        with(binding){
                            hideSplash()
                            groupOnboarding.visibility = View.VISIBLE

                            vp2Pager.currentItem = 0
                            vp2Pager.isUserInputEnabled = false

                        }
                    }else{
                        Intent(this, DashboardActivity::class.java)
                            .apply {
                                startActivity(this)
                            }
                    }
                }
            }
        }
    }

    private fun playAnimation() {
        with(binding) {
            val logo = ObjectAnimator.ofFloat(ivLogo, View.ALPHA, 1f).setDuration(800)
            val logoDesc = ObjectAnimator.ofFloat(ivLogoDesc, View.ALPHA, 1f).setDuration(800)

            AnimatorSet().apply {
                playSequentially(logo, logoDesc)
                start()
            }
        }
    }

    private fun hideSplash(){
        with(binding) {
            val logo = ObjectAnimator.ofFloat(ivLogo, View.ALPHA, 0f).setDuration(300)
            val logoDesc = ObjectAnimator.ofFloat(ivLogoDesc, View.ALPHA, 0f).setDuration(300)
            val bg = ObjectAnimator.ofFloat(viewBg, View.ALPHA, 0f).setDuration(300)

            AnimatorSet().apply {
                playSequentially(logo, logoDesc, bg)
                start()
            }
        }
    }

    override fun onDestroy() {
        binding.vp2Pager.unregisterOnPageChangeCallback(onBoardingPageChangeCallback)
        super.onDestroy()
    }

    override fun onBackPressed() {
        if (binding.vp2Pager.currentItem == 0) {
            super.onBackPressed()
        } else {
            binding.vp2Pager.currentItem = binding.vp2Pager.currentItem - 1
        }
    }

    private fun updateCircleMarker(binding: ActivitySplashBinding, position: Int) {
        when (position) {
            0 -> {
                binding.ivFirstCircle.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.comp_view_slider_blue))
                binding.ivSecondCircle.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.comp_view_circle_gray))
                binding.ivThirdCircle.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.comp_view_circle_gray))
            }
            1 -> {
                binding.ivSecondCircle.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.comp_view_slider_blue))
                binding.ivFirstCircle.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.comp_view_circle_gray))
                binding.ivThirdCircle.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.comp_view_circle_gray))
            }
            2 -> {
                binding.ivThirdCircle.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.comp_view_slider_blue))
                binding.ivSecondCircle.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.comp_view_circle_gray))
                binding.ivFirstCircle.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.comp_view_circle_gray))
            }
        }
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = ON_BOARDING_NUM_PAGES

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> {
                    OnBoardingIncomeFragment()
                }
                1 -> {
                    OnBoardingSavingsFragment()
                }
                2 -> {
                    OnBoardingExpenseFragment()
                }
                else -> OnBoardingIncomeFragment()
            }
        }
    }
}