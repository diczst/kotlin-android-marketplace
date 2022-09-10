package com.neonusa.marketplace.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.neonusa.marketplace.adapter.CategoryAdapter
import com.neonusa.marketplace.adapter.ProductTerbaruAdapter
import com.neonusa.marketplace.adapter.ProductTerlarisAdapter
import com.neonusa.marketplace.adapter.SliderAdapter
import com.neonusa.marketplace.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val adapterCategory = CategoryAdapter()
    private val adapterSlider = SliderAdapter()

    private val adapterProductTerlaris = ProductTerlarisAdapter()
    private val adapterProductTerbaru = ProductTerbaruAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupAdapter()
        setData()
        return root
    }

    private fun setupAdapter() {
        binding.rvCategory.adapter = adapterCategory
        binding.rvSlider.adapter = adapterSlider
        binding.rvProductTerlaris.adapter = adapterProductTerlaris
        binding.rvProductTerbaru.adapter = adapterProductTerbaru
    }

    private fun setData() {
        viewModel.listCategory.observe(requireActivity()) {
            adapterCategory.addItems(it)
        }

        viewModel.listSlider.observe(requireActivity()) {
            adapterSlider.addItems(it)
        }

        viewModel.listProduct.observe(requireActivity()) {
            adapterProductTerlaris.addItems(it)
            adapterProductTerbaru.addItems(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}