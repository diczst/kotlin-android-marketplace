package com.neonusa.marketplace.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.neonusa.marketplace.core.data.source.local.DummyData
import com.neonusa.marketplace.core.data.source.model.Category
import com.neonusa.marketplace.core.data.source.model.Product
import com.neonusa.marketplace.core.data.source.model.Slider

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    val listCategory: LiveData<List<Category>> = MutableLiveData<List<Category>>().apply {
        value = DummyData.listCategory
    }

    val listSlider: LiveData<List<Slider>> = MutableLiveData<List<Slider>>().apply {
        value = DummyData.listSlider
    }

    val listProduct: LiveData<List<Product>> = MutableLiveData<List<Product>>().apply {
        value = DummyData.listProduct
    }
}