package com.example.pagerpoc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.pagerpoc.databinding.FragmentHomeFragemtBinding
import kotlin.math.abs

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeFragemtBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeFragemtBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragmentAdapter()
        initTabAdapter()
    }

    private fun initTabAdapter() {
        binding.vpSlider.adapter = SliderAdapter()

        binding.vpSlider.clipToPadding = false
        binding.vpSlider.clipChildren = false
        binding.vpSlider.offscreenPageLimit = 3
        binding.vpSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER


        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(30))
            addTransformer { page, position ->
                val range = 1 - abs(position)
                page.scaleY = 0.9f + range * 0.1f
            }
        }
        binding.vpSlider.setPageTransformer(compositePageTransformer)
        binding.vpSlider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.vpFragment.setCurrentItem(position,false)
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                binding.vpFragment.alpha = 1f - positionOffset
            }
        })
    }

    private fun initFragmentAdapter() {
        binding.vpFragment.adapter = PagerFragmentAdapter(this)
        binding.vpFragment.clipToPadding = false
        binding.vpFragment.clipChildren = false
        binding.vpFragment.offscreenPageLimit = 3
    }


}