package com.example.pizza.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.pizza.adapters.PizzaAdapter
import com.example.pizza.adapters.ViewPagerAdapter.HorizontalMarginItemDecoration
import com.example.pizza.adapters.ViewPagerAdapter.ViewPager2Adapter
import com.example.pizza.databinding.FeedFragmentBinding
import com.example.pizza.util.Util
import com.example.pizza.viewModel.PizzaViewModel
import java.lang.Math.abs

class FeedFragment : Fragment() {

    private val viewModel: PizzaViewModel by viewModels(ownerProducer = ::requireParentFragment)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FeedFragmentBinding.inflate(layoutInflater, container, false).also { binding ->

        val adapter = PizzaAdapter()
        binding.recyclerView.adapter = adapter
        val recyclerView: RecyclerView = binding.recyclerView
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        viewModel.data.observe(viewLifecycleOwner) { feedModel ->
            adapter.submitList(feedModel.pizzas)
        }

        val viewPager2 = binding.bannerViewPager
        val viewPager2Adapter = ViewPager2Adapter(requireContext())
        viewPager2.adapter = viewPager2Adapter

        viewPager2.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
            })

        viewPager2.offscreenPageLimit = 1
        val nextItemVisiblePx = resources.getDimension(com.example.pizza.R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx = resources.getDimension(com.example.pizza.R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25f * abs(position))
        }
        viewPager2.setPageTransformer(pageTransformer)

        val itemDecoration = HorizontalMarginItemDecoration(
            requireContext(),
            com.example.pizza.R.dimen.viewpager_current_item_horizontal_margin
        )
        viewPager2.addItemDecoration(itemDecoration)

        for (i in 0 until binding.tabLayout.tabCount) {
            val tab = (binding.tabLayout.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(Util.dpToPx(8), 0, Util.dpToPx(8), 16)
            tab.requestLayout()
        }
    }.root
}
