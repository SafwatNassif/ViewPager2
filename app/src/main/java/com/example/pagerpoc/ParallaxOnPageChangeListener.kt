package com.example.pagerpoc

import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import java.util.concurrent.atomic.AtomicReference


class ParallaxOnPageChangeListener(
    /**
     * the viewpager that is being scrolled
     */
    private val viewPager: ViewPager,
    /**
     * the viewpager that should be synced
     */
    private val viewPager2: ViewPager, private val masterRef: AtomicReference<ViewPager>
) : OnPageChangeListener {

    private var lastRemainder = 0f
    private var mLastPos = -1

    override fun onPageScrollStateChanged(state: Int) {
        val currentMaster: ViewPager = masterRef.get()
        if (currentMaster === viewPager2) return
        when (state) {
            ViewPager.SCROLL_STATE_DRAGGING -> if (currentMaster == null) masterRef.set(
                viewPager
            )
            ViewPager.SCROLL_STATE_SETTLING -> if (mLastPos != viewPager2.currentItem) viewPager2.setCurrentItem(
                viewPager.currentItem, false
            )
            ViewPager.SCROLL_STATE_IDLE -> {
                masterRef.set(null)
                viewPager2.setCurrentItem(viewPager.currentItem, false)
                mLastPos = -1
            }
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        if (masterRef.get() === viewPager2) return
        if (mLastPos == -1) mLastPos = position
        val diffFactor = viewPager2.width.toFloat() / viewPager.width
        val scrollTo = viewPager.scrollX * diffFactor + lastRemainder
        val scrollToInt = if (scrollTo < 0) Math.ceil(scrollTo.toDouble()).toInt() else Math.floor(
            scrollTo.toDouble()
        )
            .toInt()
        lastRemainder = scrollToInt - scrollTo
        if (mLastPos != viewPager.currentItem) viewPager2.setCurrentItem(
            viewPager.currentItem,
            false
        )
        viewPager2.scrollTo(scrollToInt, 0)
    }

    override fun onPageSelected(position: Int) {}

}